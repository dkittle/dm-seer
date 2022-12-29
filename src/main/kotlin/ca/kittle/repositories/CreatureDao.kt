package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import ca.kittle.util.TraitParser.parseTraits
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*

object CreatureDao {
    private val logger = KotlinLogging.logger {}

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
        val withSubSpecies = core.copy(subSpecies = MonsterSubTypes.select { MonsterSubTypes.creatureId eq id }.let { MonsterSubTypeDO.wrapRows(it) }
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
        val withAvatarUrls = withLanguages.copy(avatarUrl = CreatureAvatars.select { CreatureAvatars.creatureId eq id }.let { CreatureAvatarDO.wrapRows(it) }
            .map { it.avatar })
        val withEnvironments = withAvatarUrls.copy(environments = CreatureEnvironments.select { CreatureEnvironments.creatureId eq id }.let { CreatureEnvironmentDO.wrapRows(it) }
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
                            it.uses, listOf())
                    3 ->
                        SpellsPerDayTrait(it.trait, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            listOf())
                    4 ->
                        SpellSlotsTrait(it.trait, it.description,
                            it.resetType?.let { i -> ResetTypes.getResetTypeById(i) },
                            listOf())
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
        return@dbQuery withTraits
    }

    private fun getCreatureSpells(id: Int, featureName: String): List<SpellUses> =
        CreatureSpells.select {(CreatureSpells.creatureId eq id) and (CreatureSpells.featureName eq featureName)}
            .let {i -> CreatureSpellDO.wrapRows(i) }
            .map{ i-> SpellUses.create(i.order, i.uses, i.spells)}

    private fun doToCreature(cr: CreatureDO, str: Int, dex: Int, con: Int, int: Int, wis: Int, cha: Int): Creature = Creature(
        cr.id.value,
        cr.species,
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
        Sizes.getSizeById(cr.swarmSize ?: 0).label,
        CreatureTypes.getCreatureTypeById(cr.swarmType ?: 0)?.label,
        cr.armorClass,
        cr.armor,
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
        listOf(),
        "",
        "",
        listOf(),
        cr.createdOn,
        cr.updatedOn)

    suspend fun cacheCreatureFromDdb(cr: ca.kittle.models.integrations.creature.Creature, userAccountId: Int): Int = dbQuery {
        val crId = Creatures.innerJoin(CreatureOrigins)
            .slice(Creatures.columns) // only select the Actors columns
            .select { CreatureOrigins.originId eq cr.id }
            .mapNotNull { it[Creatures.id].value }.singleOrNull()
            ?: Creatures.insertAndGetId {
                it[species] = cr.name
                it[size] = Sizes.getSizeById(cr.sizeId).label
                it[alignment] = Alignments.getAlignmentById(cr.alignmentId)?.label ?: Alignment.UNALIGNED.label
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
                it[swarmName] = cr.swarm?.name
                it[swarmSize] = if (cr.swarm?.sizeId != 99) cr.swarm?.sizeId else null
                it[swarmType] = cr.swarm?.typeId
                it[lairDescription] = cr.lairDescription ?: ""
                it[official] = false
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
        if (!cr.avatarUrl.isBlank()) CreatureAvatars.insert {
            it[avatar] = cr.avatarUrl
            it[creatureId] = crId
        }
        if (!cr.largeAvatarUrl.isNullOrBlank()) CreatureAvatars.insert {
            it[avatar] = cr.largeAvatarUrl
            it[creatureId] = crId
        }
        if (!cr.basicAvatarUrl.isNullOrBlank()) CreatureAvatars.insert {
            it[avatar] = cr.basicAvatarUrl
            it[creatureId] = crId
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
                        it[trait] = s.name
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
                        it[trait] = s.name
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
                            it[featureName] = s.name
                            it[creatureId] = crId
                        }
                    }
                }
                is SpellsPerDayTrait -> {
                    val traitType = 3
                    CreatureTraits.insert {
                        it[trait] = s.name
                        it[type] = traitType
                        it[description] = s.description
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    var ord = 1
                    s.spells.forEach {spell->
                        CreatureSpells.insert {
                            it[order] = spell.order
                            it[uses] = spell.uses
                            it[spells] = spell.spells.toString().replace("*", "").trim()
                            it[featureName] = s.name
                            it[creatureId] = crId
                        }
                    }
                }
                is SpellSlotsTrait -> {
                    val traitType = 4
                    CreatureTraits.insert {
                        it[trait] = s.name
                        it[type] = traitType
                        it[description] = s.description
                        it[resetType] = s.resets?.id
                        it[creatureId] = crId
                    }
                    var ord = 1
                    s.spells.forEach {spell->
                        CreatureSpells.insert {
                            it[order] = spell.order
                            it[uses] = spell.uses
                            it[spells] = spell.spells.toString().replace("*", "").trim()
                            it[featureName] = s.name
                            it[creatureId] = crId
                        }
                    }
                }
            }
        }
        return@dbQuery crId
    }


    // actions (actions|bonus|reactions|mythic|legendary)


    suspend fun creatureOrigin(newCreatureId: Int, ddbCreatureUrl: String, vttId: Int) = Database.dbQuery {
        CreatureOrigins.select { CreatureOrigins.creatureId eq newCreatureId }
            .mapNotNull { it }
            .singleOrNull()
            ?: CreatureOrigins.insert {
                it[originId] = vttId
                it[originUrl] = ddbCreatureUrl
                it[creatureId] = newCreatureId
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
