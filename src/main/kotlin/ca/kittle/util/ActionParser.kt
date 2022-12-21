package ca.kittle.util

import ca.kittle.models.*
import mu.KotlinLogging
import java.util.regex.Pattern

class ActionParser {

    companion object {
        private val logger = KotlinLogging.logger {}
        fun parseActions(a: String): List<BaseAction> {
            val actionString = a.stripNewLine()
            val p = Pattern.compile("<strong>(.+?)</strong>")
            val pd = Pattern.compile("</strong>(.+?)<strong>")
            val m = p.matcher(actionString)
            val md = pd.matcher("$actionString<strong>")
            var result = mutableListOf<BaseAction>()
            while (m.find() && md.find()) {
                if (md.group(1).contains("Melee Weapon Attack") ||
                    md.group(1).contains("Ranged Weapon Attack"))
                    result.add(parseAttackAction(m.group(1), md.group(1).stripHtml()))
                else if (m.group(1).contains("Spellcasting"))
                    result.add(SpellCastingAction(m.group(1), md.group(1).stripHtml(),
                        TraitParser.parseSpellCasting(md.group(1))))
                else
                    result.add(parseNonAttack(m.group(1), md.group(1).stripHtml()))
            }
            for (ac in result) { logger.debug { ac } }
            return result
        }

        fun parseDescription(d: String): String {
            val m = Regex("(.+?)<strong>").find(d.stripNewLine())
            return m?.value?.stripHtml() ?: ""
        }

        private fun parseNonAttack(name: String, description: String): BaseAction {
            val rolls = findRolls(description)
            if (rolls.isNotEmpty())
                return RollableAction(name, description, rolls)
            return Action(name, description)
        }

        private fun parseAttackAction(name: String, description: String): BaseAction {
            return AttackAction(
                name,
                description,
                findToHitAdjustment(description),
                determineAttackType(description),
                findRangeOrReach(description),
                findTarget(description),
                findRolls(description)
            )
        }

        private fun findToHitAdjustment(input: String): Int {
            val mh = Pattern.compile("(\\+\\d+?) to hit").matcher(input)
            return if (mh.find()) mh.group(1).toInt() else 0
        }

        private fun determineAttackType(input: String): AttackType {
            return if (input.contains("Melee Weapon Attack")) AttackType.MELEE else AttackType.RANGED
        }

        private fun findRangeOrReach(input: String): String {
            val mr = Pattern.compile("(reach|range)(.+?ft.)").matcher(input)
            return if (mr.find()) "${mr.group(1)}${mr.group(2)}" else "unknown"
        }

        private fun findTarget(input: String): String {
            val mt = Pattern.compile(",([a-z A-Z]+?)(target|creature)(s\\b|\\b).").matcher(input)
            return if (mt.find()) {
                if (mt.groupCount() == 4)
                    "${mt.group(1)}${mt.group(2)}${mt.group(3)}".trim()
                else
                    "${mt.group(1)}${mt.group(2)}".trim()
            } else "unknown"
        }

        fun findRolls(input: String): List<Roll> {
            val mr = Regex("\\((.*?)\\) ([a-zA-Z]+)").findAll(input)
            return mr.map { found ->
                Roll(Dice.parseRoll(found.groupValues[1]), DamageTypes.getDamageTypeByName(found.groupValues[2]).label)
            }.toList().filter { it.diceRoll.diceValue != 0 }
        }

    }

}
