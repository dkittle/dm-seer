package ca.kittle.util

import ca.kittle.models.*
import ca.kittle.models.DamageTypes.Companion.isNonWeapon
import mu.KotlinLogging
import java.util.regex.Pattern

class ActionParser {

    companion object {
        private val logger = KotlinLogging.logger {}

        fun parseSingleActionText(a: String, activation: ActivationType = ActivationType.ACTION): List<BaseFeature> {
            val actionString = a.stripNewLine()
            val m = Pattern.compile("(.+?)[.]").matcher(actionString)
            val md = Pattern.compile("[.](.+)").matcher(actionString)
            if (m.find() && md.find()) {
                val name = "${m.group(1)}."
                val desc = md.group(1)
                if (desc.lowercase().contains("melee weapon attack") ||
                    desc.lowercase().contains("ranged weapon attack"))
                        return parseAttackAction(activation, name, desc)
                else if (desc.lowercase().contains("spellcasting"))
                    return listOf(parseSpellCasting(name, desc, ResetType.LONGREST, desc))
                else
                    return listOf(parseNonAttack(activation, name, desc))
            }
            return listOf()
        }


        fun parseActions(a: String, activation: ActivationType = ActivationType.ACTION): List<BaseFeature> {
            val actionString = a.stripNewLine()
            val m = Pattern.compile("<strong>(.+?)</strong>").matcher(actionString)
            val md = Pattern.compile("</strong>(.+?)<strong>").matcher("$actionString<strong>")
            val result = mutableListOf<BaseFeature>()
            while (m.find() && md.find()) {
                if (md.group(1).contains("Melee Weapon Attack") ||
                    md.group(1).contains("Ranged Weapon Attack"))
                    result.addAll(parseAttackAction(activation, m.group(1), md.group(1).stripHtml()))
                else if (m.group(1).contains("Spellcasting"))
                    result.add(parseSpellCasting(m.group(1), md.group(1).stripHtml(),
                        ResetType.LONGREST, md.group(1)))
                else
                    result.add(parseNonAttack(activation, m.group(1), md.group(1).stripHtml()))
            }
            return result
        }

        fun parseDescription(d: String): String {
            val m = Regex("(.+?)<strong>").find(d.stripNewLine())
            return m?.value?.stripHtml() ?: ""
        }

        private fun parseNonAttack(activation: ActivationType, name: String, description: String): BaseFeature {
            val rolls = (findRecharge(name) + findRolls(description))
            val p = Pattern.compile("\\((\\d+?)/(Day)").matcher(name)
            val uses =
                if (p.find())
                    Uses(p.group(1).toInt(), ResetTypes.getResetTypeByName(p.group(2)) ?: ResetType.NONE) else null
            if (rolls.isNotEmpty())
                return RollableFeature(name, description, uses?.resets, activation, uses?.uses, rolls)
            return Feature(name, description, uses?.resets, activation, uses?.uses)
        }

        private fun parseAttackAction(activation: ActivationType, name: String, description: String): List<BaseFeature> {
            val attack = AttackAction(
                name,
                description,
                null,
                activation,
                findToHitAdjustment(description),
                determineAttackType(description),
                findRangeOrReach(description),
                findTarget(description),
                findRolls(description)
            )
            val versatile = if (attack.rolls.size > 1 &&
                versatileWeapons.contains(name.removeSuffix(".").lowercase())) {
                attack.copy(name = "${name.removeSuffix(".")} 2-handed")
            }
            else
                return listOf<AttackAction>(attack)
            val oneHanded = attack.rolls.toMutableList()
            oneHanded.removeAt(1)
            val twoHanded = attack.rolls.toMutableList()
            twoHanded.removeAt(0)
            return buildList<AttackAction>() {
                add(attack.copy(rolls = oneHanded))
                add(versatile.copy(rolls = twoHanded))
            }
        }

        fun parseSpellCasting(name: String, description: String, resets: ResetType?, spells: String): SpellCastingFeature {
            val p = Pattern.compile("(.+?)</p>").matcher(spells)
            val shortDescription = if (p.find()) p.group(1).stripHtml().stripNewLine() else description.stripHtml().stripNewLine()
            if (!TraitParser.findAtWill(spells).isEmpty()) {
                return SpellsPerDayFeature(name, shortDescription, resets, ActivationType.ACTION, TraitParser.parseSpellUses(spells))
            }
            return SpellSlotsFeature(name, shortDescription, resets, ActivationType.ACTION, TraitParser.parseSpellUses(spells))
        }
        private fun findSave(input: String): RollSave? {
            val m = Pattern.compile("DC (\\d+?) (\\w+)").matcher(input)
            val condition = Condition.values().filter { input.lowercase().contains(it.name.lowercase()) }.singleOrNull()
            val save = if (m.find()) RollSave(m.group(1).toInt(),
                SavingThrows.getSavingThrowBySavingThrowName(m.group(2)).ability, condition) else null
            return save
//            return if (m.find()) RollSave(m.group(1).toInt(),
//                SavingThrows.getSavingThrowBySavingThrowName(m.group(2)).ability, condition) else null
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

        fun findRecharge(input: String): List<Roll> =
            if (input.lowercase().contains("recharge"))
                listOf(Roll(Dice(1, 6, 1, null, "1d6"), DamageType.UNKNOWN))
            else listOf()

        fun findRolls(input: String): List<Roll> {
            val mr = Regex("\\((.*?)\\) ([a-zA-Z]+)").findAll(input)
            val save = findSave(input)
            return mr.map { found ->
                if (isNonWeapon(DamageTypes.getDamageTypeByName(found.groupValues[2])))
                    Roll(Dice.parseRoll(found.groupValues[1]), DamageTypes.getDamageTypeByName(found.groupValues[2]), save?.condition, save?.saveDc, save?.saveAbility)
                else
                    Roll(Dice.parseRoll(found.groupValues[1]), DamageTypes.getDamageTypeByName(found.groupValues[2]))
            }.toList().filter { it.diceRoll.diceValue != 0 }
        }

    }

}
