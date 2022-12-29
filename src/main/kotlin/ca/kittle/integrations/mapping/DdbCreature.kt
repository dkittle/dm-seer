package ca.kittle.integrations.mapping

import ca.kittle.integrations.Database
import ca.kittle.models.*
import ca.kittle.util.ActionParser
import ca.kittle.util.TraitParser
import mu.KotlinLogging
import java.util.regex.Pattern

class DdbCreature {
    companion object {
        private val logger = KotlinLogging.logger {}
        fun fixCreatureUrl(input: String): String {
            val v = input.replace(".com.com", ".com")
            val mu = Pattern.compile("(.+?)/thumbnails/(\\d*)/(\\d*)/(\\d*)/(\\d*)/(\\d*)\\.(jpg|png|jpeg|webp|gif)").matcher(v)
            return if(mu.find()) {
                "${mu.group(1)}/thumbnails/${mu.group(2)}/${mu.group(3)}/1000/1000/${mu.group(6)}.${mu.group(7)}"
            } else v
        }
    }

    fun createCreature(ddb: ca.kittle.models.integrations.creature.Creature): Creature {

        return Creature(
            0,
            ddb.name,
            ddb.subTypes.map { sub ->
                CreatureSubTypes.getCreatureSubTypeById(sub)?.label ?: "unknown"
            },
            Sizes.getSizeById(ddb.sizeId)?.label ?: "unknown",
            Alignments.getAlignmentById(ddb.alignmentId)?.label ?: "unknown",
            ddb.stats[0].value,
            ddb.stats[1].value,
            ddb.stats[2].value,
            ddb.stats[3].value,
            ddb.stats[4].value,
            ddb.stats[5].value,
            ddb.averageHitPoints,
            Dice(ddb.hitPointDice.diceCount,
                ddb.hitPointDice.diceValue,
                ddb.hitPointDice.diceMultiplier,
                ddb.hitPointDice.fixedValue,
                ddb.hitPointDice.diceString),
            ChallengeRatings.getChallengeRatingById(ddb.challengeRatingId)?.name ?: "0",
            ddb.swarm?.name,
            Sizes.getSizeById(ddb.swarm?.sizeId ?: 0).label,
            CreatureTypes.getCreatureTypeById(ddb.swarm?.typeId ?: -1)?.label,
            ddb.armorClass,
            ddb.armorClassDescription.trim(),
            ddb.movements.map {move ->
                    CreatureSpeed(Movements.getMovementById(move.movementId), move.speed, move.notes ?: "")
                },
            ddb.senses.map { sense ->
                    "${Senses.getSenseById(sense.senseId)?.label ?: "unknown"} ${sense.notes}"
                },
            ddb.savingThrows.map { save ->
                    Abilities.getAbilityById(save.statId)?.name ?: "unknown"
                },
            ddb.skills.map {skill ->
                    CreatureSkill(Skills.getSkillById(skill.skillId).label, skill.value)
                },
            ddb.languages.map {language ->
                    "${Languages.getLanguageById(language.languageId)?.label ?: "unknown"} ${language.notes}".trim()
                },
            CreatureTypes.getCreatureTypeById(ddb.typeId)?.label ?: "unknown",
            ddb.passivePerception,
            ddb.isHomebrew,
            ddb.sources.map {
                SourceReference(Sources.getSourceById(it.sourceId).description, it.pageNumber ?: 0)
                },
            ddb.isLegacy,
            ddb.isLegendary,
            ddb.isMythic,
            ddb.hasLair,
            listOf(fixCreatureUrl(ddb.avatarUrl), fixCreatureUrl(ddb.largeAvatarUrl ?: ""), fixCreatureUrl(ddb.basicAvatarUrl ?: "")).filter { u -> u.isNotBlank() },
            ddb.environments.map {id ->
                    Environments.getEnvironmentById(id)?.label ?: "unknown"
                },
            ddb.damageAdjustments.map {damageType ->
                    DamageAdjustments.getDamageAdjustmentById(damageType)
                }.filter {
                    it?.type?.equals(DamageAdjustmentType.IMMUNITY) ?: false
                }.map {
                    it?.damage ?: DamageType.UNKNOWN
                },
            ddb.damageAdjustments.map {damageType ->
                    DamageAdjustments.getDamageAdjustmentById(damageType)
                }.filter {
                    it?.type?.equals(DamageAdjustmentType.RESISTANCE) ?: false
                }.map {
                    it?.damage ?: DamageType.UNKNOWN
                },
            ddb.damageAdjustments.map {damageType ->
                    DamageAdjustments.getDamageAdjustmentById(damageType)
                }.filter {
                    it?.type?.equals(DamageAdjustmentType.VULNERABLE) ?: false
                }.map {
                    it?.damage ?: DamageType.UNKNOWN
                },
            ddb.conditionImmunities.map { id ->
                    Conditions.getConditionById(id)
                },
            TraitParser.parseTraits(ddb.specialTraitsDescription),
            ActionParser.parseActions(ddb.actionsDescription),
            ActionParser.parseActions(ddb.bonusActionsDescription ?: ""),
            ActionParser.parseActions(ddb.reactionsDescription),
            ActionParser.parseActions(ddb.mythicActionsDescription ?: ""),
            ActionParser.parseActions(ddb.legendaryActionsDescription),
            ActionParser.parseDescription(ddb.mythicActionsDescription ?: ""),
            ActionParser.parseDescription(ddb.legendaryActionsDescription),
            ddb.tags,
            Database.now,
            Database.now
        )
    }


}
