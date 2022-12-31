package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.integrations.DdbProxy
import ca.kittle.integrations.mapping.DdbCreature
import ca.kittle.models.*
import ca.kittle.util.ActionParser
import ca.kittle.util.ActionParser.Companion.parseActions
import ca.kittle.util.TraitParser.parseTraits
import ca.kittle.util.stripHtml
import ca.kittle.util.stripNewLine
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object CreatureDao {
    private val logger = KotlinLogging.logger {}

    /**
     * Find creatures based on search terms parsed in the companion service
     * terms are one or two words that are in the creature name
     * crs are a list of CRs in the form CR1/2, CR2, CR1/8 etc
     * environments and types are environment names or creature type names (like humanoid, dragon, etc)
     */
    suspend fun findCreatures(terms: List<String>, crs: List<String>, environments: List<String>,
                              types: List<String>, accountId: Int): List<CoreCreature> = dbQuery {
        val crIds = crs.map { ChallengeRatings.getChallengeRatingByLabel(it) }.mapNotNull { it?.id }
        val envIds = environments.map { Environments.getEnvironmentByName(it) }.mapNotNull { it?.id }
        val typeIds = types.map { CreatureTypes.getCreatureTypeByName(it) }.mapNotNull { it?.id }

        val query = Creatures.innerJoin(CreatureEnvironments)
            .slice(Creatures.id,Creatures.name,Creatures.challengeRating,Creatures.creatureType,
            Creatures.alignment,Creatures.size, Creatures.armorClass,Creatures.averageHitpoints,Creatures.swarmName,
            Creatures.srd,Creatures.isLegacy,Creatures.isLegendary,Creatures.isMythic, Creatures.avatarUrl)
            .selectAll()
        if (crIds.isNotEmpty())
                query.andWhere { Creatures.challengeRating inList crIds }
        if (types.isNotEmpty())
                query.andWhere { Creatures.creatureType.lowerCase() inList types }
        if (envIds.isNotEmpty())
            query.andWhere {CreatureEnvironments.environment inList envIds }
        if (terms.isNotEmpty()) {
            when (terms.size) {
                2 -> query.andWhere { Creatures.name.lowerCase() like terms[0] or (Creatures.name.lowerCase() like terms[1])}
                else -> query.andWhere { Creatures.name.lowerCase() like terms[0] }
            }
        }
        query.andWhere { Creatures.accountId eq accountId or (Creatures.srd eq true) }
        return@dbQuery query.withDistinct(true).orderBy(Creatures.name to SortOrder.ASC)
            .let { CreatureDO.wrapRows(it) }.map { doToCoreCreature(it) }
    }

    suspend fun getCreatureAvatars(id: Int, accountId: Int): CreatureAvatars? = dbQuery {
        val dto = Creatures.slice(Creatures.id,Creatures.avatarUrl, Creatures.largeAvatarUrl, Creatures.basicAvatarUrl)
            .select { Creatures.id eq id }.firstOrNull()?.let { CreatureDO.wrapRow(it) } ?: return@dbQuery null
        return@dbQuery CreatureAvatars(dto.id.value, dto.avatarUrl, dto.largeAvatarUrl, dto.basicAvatarUrl)
    }

    fun doToCoreCreature(dto: CreatureDO): CoreCreature = 
        CoreCreature(dto.id.value, dto.name, dto.size, dto.alignment, dto.averageHitpoints,
        ChallengeRatings.getChallengeRatingById(dto.challengeRating)?.label ?: "unknown",
        dto.avatarUrl, dto.swarmName, dto.armorClass, dto.creatureType, dto.srd, dto.isLegacy, dto.isLegendary,
        dto.isMythic)

    /**
     * Load a creature from database
     */
    suspend fun getCreature(id: Int): Creature? = dbQuery {
        val stats = CreatureStats.select { CreatureStats.creatureId eq id }.let { CreatureStatDO.wrapRows(it) }
        val record = Creatures.select { Creatures.id eq id }.firstOrNull()?.let { CreatureDO.wrapRow(it) }
        if (record == null)
            return@dbQuery null
        val core = doToCreature(record, stats.filter { it.stat == 1 }.map { it.value }.singleOrNull() ?: 0,
            stats.filter { it.stat == 2 }.map { it.value }.singleOrNull() ?: 0,
            stats.filter { it.stat == 3 }.map { it.value }.singleOrNull() ?: 0,
            stats.filter { it.stat == 4 }.map { it.value }.singleOrNull() ?: 0,
            stats.filter { it.stat == 5 }.map { it.value }.singleOrNull() ?: 0,
            stats.filter { it.stat == 6 }.map { it.value }.singleOrNull() ?: 0)
        val withSubSpecies = core.copy(subTypes = MonsterSubTypes.select { MonsterSubTypes.creatureId eq id }.let { MonsterSubTypeDO.wrapRows(it) }
            .map { CreatureSubTypes.getCreatureSubTypeById(it.subType)?.label ?: ""}.filter { it.isNotBlank() } )
        val withSpeeds = withSubSpecies.copy(speeds = CreatureMovements.select { CreatureMovements.creatureId eq id }.let { CreatureMovementDO.wrapRows(it) }
            .map { CreatureSpeed(Movements.getMovementById(it.movement), it.speed, it.notes) } )
        val withSenses = withSpeeds.copy(senses = CreatureSenses.select { CreatureSenses.creatureId eq id }.let { CreatureSenseDO.wrapRows(it) }
            .map { (Senses.getSenseById(it.sense)?.label + it.notes) ?: ""}.filter { it.isNotBlank() } )
        val withSaves = withSenses.copy(savingThrows = CreatureSaves.select { CreatureSaves.creatureId eq id }.let { CreatureSaveDO.wrapRows(it) }
            .map { (SavingThrows.getSavingThrowById(it.save)?.key) ?: ""}.filter { it.isNotBlank() } )
        val withSkills = withSaves.copy(skills = CreatureSkills.select { CreatureSkills.creatureId eq id }.let { CreatureSkillDO.wrapRows(it) }
            .map { CreatureSkill(Skills.getSkillById(it.skill).label, it.value) })
        val withSources = withSkills.copy(sources = CreatureSources.select { CreatureSources.creatureId eq id }.let { CreatureSourceDO.wrapRows(it) }
            .map { SourceReference(Sources.getSourceById(it.sourceBook).description, it.pageNumber) })
        val withLanguages = withSources.copy(languages = CreatureLanguages.select { CreatureLanguages.creatureId eq id }.let { CreatureLanguageDO.wrapRows(it) }
            .map { "${Languages.getLanguageById(it.language)} ${it.notes}".trim() })
        val withEnvironments = withLanguages.copy(environments = CreatureEnvironments.select { CreatureEnvironments.creatureId eq id }.let { CreatureEnvironmentDO.wrapRows(it) }
            .map { Environments.getEnvironmentById(it.environment)?.label ?: "unknown" })
        val withImmunities = withEnvironments.copy(immunities = CreatureImmunities.select { CreatureImmunities.creatureId eq id }.let { CreatureImmunityDO.wrapRows(it) }
            .map { DamageTypes.getDamageTypeById(it.immunity) })
        val withResistances = withImmunities.copy(resistances = CreatureResistances.select { CreatureResistances.creatureId eq id }.let { CreatureResistanceDO.wrapRows(it) }
            .map { DamageTypes.getDamageTypeById(it.resistance) })
        val withVulnerabilities = withResistances.copy(vulnerabilities = CreatureVulnerabilities.select { CreatureVulnerabilities.creatureId eq id }.let { CreatureVulnerabilityDO.wrapRows(it) }
            .map { DamageTypes.getDamageTypeById(it.vulnerability) })
        val withConditionImmunities = withVulnerabilities.copy(conditionImmunities = CreatureConditionImmunities.select { CreatureConditionImmunities.creatureId eq id }.let { CreatureConditionImmunityDO.wrapRows(it) }
            .map {Conditions.getConditionById(it.condition) })
        val traits = CreatureTraits.select { CreatureTraits.creatureId eq id}.let { CreatureTraitDO.wrapRows(it) }
            .map {
                when(it.type) {
                    2 ->
                        RollableTrait(it.trait, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            it.activationType?.let { i -> ActivationTypes.getActivationTypeById(i)},
                            it.uses, getCreatureRolls(id, it.trait))
                    3 ->
                        SpellsPerDayTrait(it.trait, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            getCreatureSpells(id, it.trait))
                    4 ->
                        SpellSlotsTrait(it.trait, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            getCreatureSpells(id, it.trait))
                    else ->
                        Trait(it.trait, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            it.activationType?.let { i -> ActivationTypes.getActivationTypeById(i)},
                            it.uses)
                }
            }
        val withTraits = withConditionImmunities.copy(traits = traits.map {
            when(it) {
                is RollableTrait -> it.copy(rolls =
                    CreatureRolls.select {(CreatureRolls.creatureId eq id) and (CreatureRolls.featureName eq it.name)}
                        .let { i -> CreatureRollDO.wrapRows(i) }
                        .map { i-> Roll(Dice.parseRoll(i.roll),
                            DamageTypes.getDamageTypeByName(i.damageType),
                            i.condition?.let { i2 -> Conditions.getConditionByName(i2) }, i.saveDc, i.saveAbility)})
                is SpellsPerDayTrait -> it.copy(spells = getCreatureSpells(id, it.name))
                is SpellSlotsTrait -> it.copy(spells = getCreatureSpells(id, it.name))
                else -> it
            }
        })
        val features = CreatureFeatures.select { CreatureFeatures.creatureId eq id}.let { CreatureFeatureDO.wrapRows(it) }
            .map {
                when(it.type) {
                    2 ->
                        RollableFeature(it.feature, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            ActivationTypes.getActivationTypeById(it.activationType),
                            it.uses, getCreatureRolls(id, it.feature))
                    3 ->
                        getAttackAction(id, it.feature).copy(name = it.feature, description = it.description,
                            resets = it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            activationType = ActivationTypes.getActivationTypeById(it.activationType),
                            rolls = getCreatureRolls(id, it.feature))
                    4 ->
                        SpellsPerDayFeature(it.feature, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            ActivationType.ACTION,
                            getCreatureSpells(id, it.feature))
                    5 ->
                        SpellSlotsFeature(it.feature, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            ActivationType.ACTION,
                            getCreatureSpells(id, it.feature))
                    else ->
                        Feature(it.feature, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                             ActivationTypes.getActivationTypeById(it.activationType),
                            it.uses)
                }
            }
        val withActions = withTraits.copy(actions = features.filter { it.activationType == ActivationType.ACTION})
        val withBonus = withActions.copy(bonusActions = features.filter { it.activationType == ActivationType.BONUSACTION})
        val withReaction = withBonus.copy(reactions = features.filter { it.activationType == ActivationType.REACTION})
        val withMythic = withReaction.copy(mythicActions = features.filter { it.activationType == ActivationType.MYTHIC})
        val withLegendary = withMythic.copy(legendaryActions = features.filter { it.activationType == ActivationType.LEGENDARY})
        return@dbQuery withLegendary
    }

    private fun getAttackAction(id: Int, name: String): AttackAction {
        return CreatureAttacks.select{(CreatureAttacks.creatureId eq id) and (CreatureAttacks.featureName eq name)}
            .let {i -> CreatureAttackDO.wrapRows(i) }
            .map{ AttackAction("", "", null, ActivationType.ACTION, it.toHit,
                AttackTypes.getAttackTypeById(it.type) ?: AttackType.MELEE, it.range, it.target, listOf())}
            .first()
    }
    private fun getCreatureRolls(id: Int, featureName: String): List<Roll> =
        CreatureRolls.select {(CreatureRolls.creatureId eq id) and (CreatureRolls.featureName eq featureName)}
            .let {i -> CreatureRollDO.wrapRows(i) }
            .map{ i-> Roll(Dice.parseRoll(i.roll), DamageTypes.getDamageTypeByName(i.damageType),
                i.condition?.let { Conditions.getConditionByName(it) }, i.saveDc, i.saveAbility) }

    private fun getCreatureSpells(id: Int, featureName: String): List<SpellUses> =
        CreatureSpells.select {(CreatureSpells.creatureId eq id) and (CreatureSpells.featureName eq featureName)}
            .let {i -> CreatureSpellDO.wrapRows(i) }
            .map{ i-> SpellUses.create(i.order, i.uses, i.spells)}

    private fun doToCreature(cr: CreatureDO, str: Int, dex: Int, con: Int, int: Int, wis: Int, cha: Int): Creature = Creature(
        cr.id.value,
        cr.name,
        listOf(),
        cr.size,
        cr.alignment,
        str,
        dex,
        con,
        int,
        wis,
        cha,
        cr.averageHitpoints,
        Dice.parseRoll(cr.hitpointDice),
        ChallengeRatings.getChallengeRatingById(cr.challengeRating)?.label ?: "unknown",
        cr.swarmName,
        cr.swarmSize?.let { Sizes.getSizeById(it).label },
        CreatureTypes.getCreatureTypeById(cr.swarmType ?: 0)?.label,
        cr.armorClass,
        cr.armor,
        cr.avatarUrl,
        cr.largeAvatarUrl,
        cr.basicAvatarUrl,
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        cr.creatureType,
        cr.passivePerception,
        cr.isHomebrew,
        listOf(),
        cr.isLegacy,
        cr.isLegendary,
        cr.isMythic,
        cr.hasLair,
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        listOf(),
        cr.mythicDescription,
        cr.legendaryDescription,
        listOf(),
        cr.createdOn,
        cr.updatedOn,
        cr.official,
        cr.srd)

    suspend fun getCachedCreatureId(vttId: Int): Int? = dbQuery {
        return@dbQuery CreatureOrigins.select { CreatureOrigins.originId eq vttId }
            .map { it[CreatureOrigins.creatureId].value }.singleOrNull()
    }

    suspend fun cacheCreatureFromDdb(cr: ca.kittle.models.integrations.creature.Creature, userAccountId: Int): Int = dbQuery {
        GlobalScope.launch {
            cacheAvatars(cr)
        }
        val crId = Creatures.innerJoin(CreatureOrigins)
            .slice(Creatures.columns) // only select the Actors columns
            .select { CreatureOrigins.originId eq cr.id }
            .mapNotNull { it[Creatures.id].value }.singleOrNull()
            ?: Creatures.insertAndGetId {
                it[name] = cr.name
                it[size] = Sizes.getSizeById(cr.sizeId).label
                it[alignment] = cr.alignmentId?.let { it1 -> Alignments.getAlignmentById(it1)?.label } ?: Alignment.UNALIGNED.label
                it[averageHitpoints] = cr.averageHitPoints
                it[hitpointDice] = cr.hitPointDice.diceString
                it[challengeRating] = ChallengeRatings.getChallengeRatingById(cr.challengeRatingId)?.id ?: 0
                it[armorClass] = cr.armorClass
                it[armor] = cr.armorClassDescription.trim().replace("(", "").replace(")", "")
                it[creatureType] = CreatureTypes.getCreatureTypeById(cr.typeId)?.label ?: ""
                it[passivePerception] = cr.passivePerception
                it[isHomebrew] = cr.isHomebrew
                it[isLegacy] = cr.isLegacy
                it[isLegendary] = cr.isLegendary
                it[isMythic] = cr.isMythic
                it[hasLair] = cr.hasLair
                it[avatarUrl] = cr.avatarUrl
                it[largeAvatarUrl] = cr.largeAvatarUrl
                it[basicAvatarUrl] = cr.basicAvatarUrl
                it[swarmName] = cr.swarm?.name
                it[swarmSize] = if (cr.swarm?.sizeId != 99) cr.swarm?.sizeId else null
                it[swarmType] = cr.swarm?.typeId
                it[lairDescription] = cr.lairDescription.stripHtml().stripNewLine() ?: ""
                it[official] = true
                it[srd] = false
                it[mythicDescription] = ActionParser.parseDescription(cr.mythicActionsDescription ?: "")
                it[legendaryDescription] = ActionParser.parseDescription(cr.legendaryActionsDescription ?: "")
                it[createdOn] = Database.now
                it[updatedOn] = Database.now
                it[accountId] = userAccountId
            }.value
        if (CreatureOrigins.select { CreatureOrigins.creatureId eq crId }
            .mapNotNull { it }.singleOrNull() != null)
            return@dbQuery crId
        CreatureOrigins.insert {
            it[originId] = cr.id
            it[originUrl] = cr.url
            it[creatureId] = crId
        }
        cr.subTypes.forEach { t ->
            MonsterSubTypes.insert {
                it[subType] = t
                it[creatureId] = crId
            }
        }
        cr.stats.forEach { s ->
            CreatureStats.insert {
                it[stat] = s.statId
                it[value] = s.value
                it[creatureId] = crId
            }
        }
        cr.senses.forEach { s ->
            CreatureSenses.insert {
                it[sense] = s.senseId
                it[notes] = s.notes
                it[creatureId] = crId
            }
        }
        cr.savingThrows.forEach { s ->
            CreatureSaves.insert {
                it[save] = s.statId
                it[modifier] = s.bonusModifier ?: 0
                it[creatureId] = crId
            }
        }
        cr.skills.forEach { s->
            CreatureSkills.insert {
                it[skill] = s.skillId
                it[value] = s.value
                it[creatureId] = crId
            }
        }
        cr.movements.forEach { s->
            CreatureMovements.insert {
                it[movement] = s.movementId
                it[speed] = s.speed
                it[notes] = s.notes ?: ""
                it[creatureId] = crId
            }
        }
        cr.languages.forEach { s->
            CreatureLanguages.insert {
                it[language] = s.languageId
                it[notes] = s.notes
                it[creatureId] = crId
            }
        }
        cr.environments.forEach { s->
            CreatureEnvironments.insert {
                it[environment] = s
                it[creatureId] = crId
            }
        }
        cr.sources.forEach { s->
            CreatureSources.insert {
                it[sourceBook] = s.sourceId
                it[pageNumber] = s.pageNumber ?: 0
                it[creatureId] = crId
            }
        }
        cr.tags.forEach { t->
            CreatureTags.insert {
                it[tag] = t
                it[creatureId] = crId
            }
        }
        cr.damageAdjustments.forEach { d->
            val da = DamageAdjustments.getDamageAdjustmentById(d)
            when (da?.type) {
                DamageAdjustmentType.IMMUNITY -> {
                    CreatureImmunities.insert {
                        it[immunity] = da.damage.id
                        it[creatureId] = crId
                    }
                }
                DamageAdjustmentType.RESISTANCE -> {
                    CreatureResistances.insert {
                        it[resistance] = da.damage.id
                        it[creatureId] = crId
                    }
                }
                DamageAdjustmentType.VULNERABLE -> {
                    CreatureVulnerabilities.insert {
                        it[vulnerability] = da.damage.id
                        it[creatureId] = crId
                    }
                }
                else -> {}
            }
        }
        cr.conditionImmunities.forEach { s->
            CreatureConditionImmunities.insert {
                it[condition] = s
                it[creatureId] = crId
            }
        }
        val traits = parseTraits(cr.specialTraitsDescription)
        traits.filter { it is Trait || it is RollableTrait }
            .filter { it.description.lowercase().contains("per day") ||
            it.description.lowercase().contains("long rest")}
            .map { it.resets = ResetType.LONGREST}
        traits.filter { it is Trait || it is RollableTrait }
            .filter { it.description.lowercase().contains("short rest")}
            .map { it.resets = ResetType.SHORTREST}
        traits.forEach { s->
            when (s) {
                is Trait -> {
                    CreatureTraits.insert {
                        it[trait] = s.name.stripHtml().stripNewLine()
                        it[type] = 1
                        it[description] = s.description
                        it[activationType] = s.activationType?.id
                        it[uses] = s.uses
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                }
                is RollableTrait -> {
                    CreatureTraits.insert {
                        it[trait] = s.name.stripHtml().stripNewLine()
                        it[type] = 2
                        it[description] = s.description
                        it[activationType] = s.activationType?.id
                        it[uses] = s.uses
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    s.rolls.forEach {r->
                        CreatureRolls.insert {
                            it[roll] = r.diceRoll.diceString ?: ""
                            it[damageType] = r.type.label.lowercase()
                            it[condition] = r.condition?.name
                            it[saveDc] = r.saveDc
                            it[saveAbility] = r.saveAbility
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
                is SpellsPerDayTrait -> {
                    val traitType = 3
                    CreatureTraits.insert {
                        it[trait] = s.name.stripHtml().stripNewLine()
                        it[type] = traitType
                        it[description] = s.description
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    s.spells.forEach {spell->
                        CreatureSpells.insert {
                            it[order] = spell.order
                            it[uses] = spell.uses
                            it[spells] = spell.spells.toString().replace("*", "").trim()
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
                is SpellSlotsTrait -> {
                    val traitType = 4
                    CreatureTraits.insert {
                        it[trait] = s.name.stripHtml().stripNewLine()
                        it[type] = traitType
                        it[description] = s.description
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    s.spells.forEach {spell->
                        CreatureSpells.insert {
                            it[order] = spell.order
                            it[uses] = spell.uses
                            it[spells] = spell.spells.toString().replace("*", "").trim()
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
            }
        }
        val actions = (parseActions(cr.actionsDescription, ActivationType.ACTION) +
                parseActions(cr.bonusActionsDescription ?: "", ActivationType.BONUSACTION) +
                parseActions(cr.reactionsDescription ?: "", ActivationType.REACTION) +
                parseActions(cr.mythicActionsDescription ?: "", ActivationType.MYTHIC) +
                parseActions(cr.legendaryActionsDescription, ActivationType.LEGENDARY))
        actions.filter { it is Feature || it is RollableFeature }
            .filter { it.description.lowercase().contains("per day") ||
                    it.description.lowercase().contains("long rest")}
            .map { it.resets = ResetType.LONGREST}
        actions.filter { it is Feature || it is RollableFeature }
            .filter { it.description.lowercase().contains("short rest")}
            .map { it.resets = ResetType.SHORTREST}
        actions.forEach { s->
            when (s) {
                is Feature -> {
                    CreatureFeatures.insert {
                        it[feature] = s.name.stripHtml().stripNewLine()
                        it[type] = 1
                        it[description] = s.description
                        it[activationType] = s.activationType.id
                        it[uses] = s.uses
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                }
                is RollableFeature -> {
                    CreatureFeatures.insert {
                        it[feature] = s.name.stripHtml().stripNewLine()
                        it[type] = 2
                        it[description] = s.description
                        it[activationType] = s.activationType.id
                        it[uses] = s.uses
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    s.rolls.forEach {r->
                        CreatureRolls.insert {
                            it[roll] = r.diceRoll.diceString ?: ""
                            it[damageType] = r.type.label.lowercase()
                            it[condition] = r.condition?.name
                            it[saveDc] = r.saveDc
                            it[saveAbility] = r.saveAbility
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
                is AttackAction -> {
                    CreatureFeatures.insert {
                        it[feature] = s.name.stripHtml().stripNewLine()
                        it[type] = 3
                        it[description] = s.description
                        it[activationType] = s.activationType.id
                        it[uses] = null
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    CreatureAttacks.insert {
                        it[type] = s.attackType.id
                        it[toHit] = s.toHit
                        it[range] = s.range
                        it[target] = s.target
                        it[featureName] = s.name.stripHtml().stripNewLine()
                        it[creatureId] = crId
                    }
                    s.rolls.forEach {r->
                        CreatureRolls.insert {
                            it[roll] = r.diceRoll.diceString ?: ""
                            it[damageType] = r.type.label.lowercase()
                            it[condition] = r.condition?.name
                            it[saveDc] = r.saveDc
                            it[saveAbility] = r.saveAbility
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
                is SpellsPerDayFeature -> {
                    CreatureFeatures.insert {
                        it[feature] = s.name.stripHtml().stripNewLine()
                        it[type] = 4
                        it[description] = s.description
                        it[activationType] = s.activationType.id
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    s.spells.forEach {spell->
                        CreatureSpells.insert {
                            it[order] = spell.order
                            it[uses] = spell.uses
                            it[spells] = spell.spells.toString().replace("*", "").trim()
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
                is SpellSlotsFeature -> {
                    CreatureFeatures.insert {
                        it[feature] = s.name.stripHtml().stripNewLine()
                        it[type] = 5
                        it[description] = s.description
                        it[activationType] = s.activationType.id
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    s.spells.forEach {spell->
                        CreatureSpells.insert {
                            it[order] = spell.order
                            it[uses] = spell.uses
                            it[spells] = spell.spells.toString().replace("*", "").trim()
                            it[featureName] = s.name.stripHtml().stripNewLine()
                            it[creatureId] = crId
                        }
                    }
                }
            }
        }
        return@dbQuery crId
    }

    suspend fun cacheAvatars(creature: ca.kittle.models.integrations.creature.Creature) {
        val url = DdbCreature.fixCreatureUrl(creature.avatarUrl)
        val large = DdbCreature.fixCreatureUrl(creature.largeAvatarUrl ?: "")
        val basic = DdbCreature.fixCreatureUrl(creature.basicAvatarUrl ?: "")
        val name = creature.name
        val folder = "./avatars/${name.take(1).lowercase()}"
        if (Files.exists(Paths.get("$folder/$name-${Url(url).pathSegments.last()}"))) {
            logger.debug { "File $folder/$name-${Url(url).pathSegments.last()} already downloaded."}
            return
        }
        logger.debug { "Caching creature avatars for $name" }
        if (url.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing avatar for $name in $folder/" }
            val u = Url(url)
            val file = File("$folder/$name-${u.pathSegments.last()}")
            client.get(url).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing avatar from $url" }
            client.close()
        }
        if (large.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing large avatar for $name in $folder/" }
            val u = Url(large)
            val file = File("$folder/$name-large-${u.pathSegments.last()}")
            client.get(large).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing large avatar from $large" }
            client.close()
        }
        if (basic.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing basic avatar for $name in $folder/" }
            val u = Url(basic)
            val file = File("$folder/$name-basic-${u.pathSegments.last()}")
            client.get(basic).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing basic avatar from $basic" }
            client.close()
        }
    }


//    fun createCreature(c: Creature, origin: String, originId: Int): Int? {
//        var exists = 0
//        db.connect().use { conn ->
//            conn.prepareStatement(CREATURE_ORIGIN).use { stmt ->
//                stmt.setString(1, origin)
//                stmt.setInt(2, originId)
//                stmt.executeQuery().use { rs ->
//                    if (rs.next())
//                      exists = rs.getInt("creature_id")
//                }
//            }
//        }
//        if (exists != 0) {
//            logger.debug { "Creature already imported from $origin $originId" }
//            return exists
//        }
//        logger.debug { "Storing a creature" }
//        var newId = 0
//        db.connect().use { conn ->
//            var returnId = arrayOf("id")
//            conn.prepareStatement(NEW_CREATURE_SQL, returnId).use { stmt ->
//                stmt.setString(1, c.species)
//                stmt.setString(2, c.subSpecies.toString())
//                stmt.setString(3, c.size)
//                stmt.setString(4, c.alignment)
//                stmt.setInt(5, c.str)
//                stmt.setInt(6, c.dex)
//                stmt.setInt(7, c.con)
//                stmt.setInt(8, c.int)
//                stmt.setInt(9, c.wis)
//                stmt.setInt(10, c.cha)
//                stmt.setInt(11, c.averageHitPoints)
//                stmt.setString(12, c.hpDice.diceString)
//                stmt.setString(13, c.challengeRating)
//                stmt.setString(14, c.swarm?.name)
//                stmt.setInt(15, c.swarm?.sizeId ?: 0)
//                stmt.setInt(16, c.ac)
//                stmt.setString(17, c.armor)
//                stmt.setString(18, c.speeds.toString())
//                stmt.setString(19, c.senses.toString())
//                stmt.setString(20, c.savingThrows.toString())
//                stmt.setString(21, c.skills.toString())
//                stmt.setString(22, c.languages.toString())
//                stmt.setString(23, c.creatureType)
//                stmt.setInt(24, c.passivePerception)
//                stmt.setString(25, c.isHomebrew.toString())
//                stmt.setString(26, c.isLegacy.toString())
//                stmt.setString(27, c.isLegendary.toString())
//                stmt.setString(28, c.isMythic.toString())
//                stmt.setString(29, c.hasLair.toString())
//                val rows = stmt.executeUpdate()
//                val rs = stmt.generatedKeys
//                if (rs.next())
//                    newId = rs.getInt(1)
//                if (rows != 1)
//                    logger.warn { "Creature ${c.species} could not be saved" }
//                else
//                    logger.debug { "Create ${c.species} saved as new id $newId." }
//            }
//        }
//        db.connect().use { conn ->
//            conn.prepareStatement(NEW_CREATURE_ORIGIN).use { stmt ->
//                stmt.setInt(1, newId)
//                stmt.setString(2, origin)
//                stmt.setInt(3, originId)
//                val rows = stmt.executeUpdate()
//                if (rows != 1)
//                    logger.warn { "Creature origin $newId/$originId could not be saved" }
//            }
//        }
//        db.connect().use { conn ->
//            for (s in c.sources) {
//                conn.prepareStatement(NEW_CREATURE_SOURCES).use { stmt ->
//                    stmt.setInt(1, newId)
//                    stmt.setInt(2, s.sourceId)
//                    stmt.setInt(3, s.pageNumber)
//                    val rows = stmt.executeUpdate()
//                    if (rows != 1)
//                        logger.warn { "Creature source ${s.sourceId} could not be saved" }
//                }
//            }
//        }
//        db.connect().use { conn ->
//            for (url in c.avatarUrl) {
//                conn.prepareStatement(NEW_CREATURE_AVATARS).use { stmt ->
//                    stmt.setInt(1, newId)
//                    stmt.setString(2, url)
//                    val rows = stmt.executeUpdate()
//                    if (rows != 1)
//                        logger.warn { "Creature avatar $url could not be saved" }
//                }
//            }
//        }
//        return newId
//    }
//
//
//
//    companion object {
//        private val logger = KotlinLogging.logger {}
//
//        val CREATURE_ORIGIN = "SELECT creature_id FROM creature_origins WHERE origin=? AND origin_id=?"
//
////        val NEW_CREATURE_SQL = "INSERT INTO creatures (species, subSpecies, size, alignment, str, dex, con, int, wis, cha, averageHitPoints, hpDice, challengeRating, swarm, ac, armor, speeds, senses, savingThrows, skills, languages, creatureType, passivePerception, isHomebrew, sources, isLegacy, isLegendary, isMythic, hasLair, avatarUrl, environments, immunities, resistances, vulnerabilities, conditionImmunities, traits, actions, bonusActions, reactions, mythicActions, legendaryActions, mythicDescription, legendaryDescription, tags)".trimIndent()
//        val NEW_CREATURE_SQL = ("INSERT INTO creatures (species, subSpecies, size, alignment, strength, dexterity, constitution, intelligence, wisdom, charisma, averageHitPoints, hitDiceString, challenge_rating, swarmName, swarmSize, armorClass, armor, speeds, senses, savingThrows, skills, languages, creatureType, passivePerception, isHomebrew, isLegacy, isLegendary, isMythic, hasLair)" +
//        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)").trimIndent()
//
//        val NEW_CREATURE_ORIGIN = "INSERT INTO creature_origins (creature_id, origin, origin_id) VALUES (?, ?, ?)"
//
//        val NEW_CREATURE_SOURCES = "INSERT INTO creature_sources (creature_id, source_id, page_number) VALUES (?, ?, ?)"
//
//        val NEW_CREATURE_AVATARS = "INSERT INTO creature_avatars (creature_id, url) VALUES (?, ?)"
//
//    }
}
