package ca.kittle.integrations.mapping

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
                val i = mu.groupCount()
                "${mu.group(1)}/thumbnails/${mu.group(2)}/${mu.group(3)}/1000/1000/${mu.group(6)}.${mu.group(7)}"
            } else v
        }
    }

//    fun createCreature(ddb: ca.kittle.models.integrations.creature.Creature): Creature {
//        return Creature(
//            0,
//            ddb.name,
//            ddb.subTypes.map { sub ->
//                CreatureType.getCreatureSubTypeById(sub)
//            },
//            Size.getSizeById(ddb.sizeId)?.size ?: "unknown",
//            Alignment.getAlignmentById(ddb.alignmentId)?.name ?: "unknown",
//            ddb.stats[0].value,
//            ddb.stats[1].value,
//            ddb.stats[2].value,
//            ddb.stats[3].value,
//            ddb.stats[4].value,
//            ddb.stats[5].value,
//            Health(hitPoints = ddb.averageHitPoints, maximumHitPoints = ddb.averageHitPoints),
//            ChallengeRating.getChallengeRatingById(ddb.challengeRatingId)?.name ?: "0",
//            ddb.armorClass,
//            ddb.armorClassDescription.trim(),
//            Dice(ddb.hitPointDice.diceCount,
//                ddb.hitPointDice.diceValue,
//                ddb.hitPointDice.diceMultiplier,
//                ddb.hitPointDice.fixedValue,
//                ddb.hitPointDice.diceString),
//            ddb.movements.map {move ->
//                    "${Movement.getMovementById(move.movementId)} ${move.speed} ft."
//                },
//            ddb.senses.map { sense ->
//                    "${Sense.getSenseById(sense.senseId)} ${sense.notes}"
//                },
//            ddb.savingThrows.map { save ->
//                    Abilities.getAbilityById(save.statId)?.name ?: "unknown"
//                },
//            ddb.skills.map {skill ->
//                    Skills.getSkillById(skill.skillId) ?: Skill.UNKNOWN
//                },
//            ddb.languages.map {language ->
//                    "${Language.getLanguageById(language.languageId)} ${language.notes}".trim()
//                },
//            CreatureType.getCreatureTypeById(ddb.typeId),
//            ddb.passivePerception,
//            ddb.isHomebrew,
//            ddb.sources.map {source ->
//                source.pageNumber?.let {
//                    CreatureReference(0, source.sourceId, it, ddb.url) } ?:
//                    CreatureReference(0, source.sourceId, 0, ddb.url)
//                },
//            ddb.isLegacy,
//            ddb.isLegendary,
//            ddb.isMythic,
//            ddb.hasLair,
//            listOf(fixCreatureUrl(ddb.avatarUrl), fixCreatureUrl(ddb.largeAvatarUrl), fixCreatureUrl(ddb.basicAvatarUrl ?: "")).filter { u -> u.isNotBlank() },
//            ddb.environments.map {id ->
//                    Environment.getEnvironmentById(id)
//                },
//            ddb.damageAdjustments.map {damageType ->
//                    DamageAdjustment.getAdjustmentById(damageType)
//                }.filter { adjustment ->
//                    adjustment?.adjustment?.equals(Adjustment.IMMUNITY) ?: false
//                }.map { adjustment ->
//                    adjustment?.damageType ?: DamageType.UNKNOWN
//                },
//            ddb.damageAdjustments.map {damageType ->
//                    DamageAdjustment.getAdjustmentById(damageType)
//                }.filter { adjustment ->
//                    adjustment?.adjustment?.equals(Adjustment.RESISTANCE) ?: false
//                }.map { adjustment ->
//                    adjustment?.damageType ?: DamageType.UNKNOWN
//                },
//            ddb.damageAdjustments.map {damageType ->
//                    DamageAdjustment.getAdjustmentById(damageType)
//                }.filter { adjustment ->
//                    adjustment?.adjustment?.equals(Adjustment.VULNERABLE) ?: false
//                }.map { adjustment ->
//                    adjustment?.damageType ?: DamageType.UNKNOWN
//                },
//            ddb.conditionImmunities.map { id ->
//                    Conditions.getConditionById(id)
//                },
//            TraitParser.parseTraits(ddb.specialTraitsDescription),
//            ActionParser.parseActions(ddb.actionsDescription),
//            ActionParser.parseActions(ddb.bonusActionsDescription ?: ""),
//            ActionParser.parseActions(ddb.reactionsDescription),
//            ActionParser.parseActions(ddb.mythicActionsDescription ?: ""),
//            ActionParser.parseActions(ddb.legendaryActionsDescription),
//            ActionParser.parseDescription(ddb.mythicActionsDescription ?: ""),
//            ActionParser.parseDescription(ddb.legendaryActionsDescription)
//        )
//    }


}
