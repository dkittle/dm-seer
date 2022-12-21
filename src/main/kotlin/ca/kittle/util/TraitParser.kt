package ca.kittle.util

import ca.kittle.models.*
import mu.KotlinLogging
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.stripHtml(): String {
    val regex = "<[^>]*>".toRegex()
    return regex.replace(this, "").trim()
}

fun String.stripNewLine(): String {
    val regex = "\n".toRegex()
    return regex.replace(this, "").trim()
}

class TraitParser {

    companion object {
        private val logger = KotlinLogging.logger {}
        fun parseTraits(t: String): List<BaseTrait> {
            val traitString = t.stripNewLine()
            val p = Pattern.compile("<strong>(.+?)</strong>")
            val pd = Pattern.compile("</strong></em>(.+?)<strong>")
            val m = p.matcher(traitString)
            val md = pd.matcher("$traitString<strong>")
            var result = mutableListOf<BaseTrait>()
            while (m.find() && md.find()) {
                if (m.group(1).contains("Spellcasting"))
                    result.add(SpellCastingTrait(m.group(1), md.group(1).stripHtml(),
                        parseSpellCasting(md.group(1).stripNewLine())))
                else
                    result.add(parseNonSpell(m.group(1), md.group(1).stripHtml()))
            }
            return result
        }

        private fun parseNonSpell(name: String, description: String): BaseTrait {
            val rolls = ActionParser.findRolls(description)
            if (rolls.isNotEmpty())
                return RollableTrait(name, description, rolls)
            return Trait(name, description)
        }

        fun parseSpellCasting(description: String): List<BaseCreatureSpells> {
            return buildList<BaseCreatureSpells>() {
                var s = findAtWill(description)
                if (s.isNotEmpty()) add(PerDaySpells(10000, s))
                for (i in 1..3) {
                    s = findUsesPerDay(i, description)
                    if (s.isNotEmpty()) add(PerDaySpells(i, s))
                }
                s = findCantrips(description)
                if (s.isNotEmpty()) add(SpellSlotSpells(10000, s))
                for (i in 1..9) {
                    val levelSpells = findSpellSlotsPerDay(i, description)
                    if (levelSpells != null) add(SpellSlotSpells(levelSpells.slots, levelSpells.spells))
                }

            }
        }
        private fun findAtWill(input: String): List<String> {
            val mm = Pattern.compile("At will: (.+?)</p>").matcher(input)
            return extractSpells(mm)
        }

        fun findUsesPerDay(uses: Int, input: String): List<String> {
            val mm = Pattern.compile("$uses/day each: (.+?)</p>").matcher(input)
            return extractSpells(mm)
        }

        private fun findCantrips(input: String): List<String> {
            val mm = Pattern.compile("Cantrip.*?\\(at will\\): (.+?)</p>").matcher(input)
            return extractSpells(mm)
        }

        private fun findSpellSlotsPerDay(level: Int, input: String): SpellSlotResult? {
            val mm = Pattern.compile("$level[stndh]+? level \\((\\d+?) slot(s\\b|\\b)\\): (.+?</p>)").matcher(input)
            return if (mm.find() && mm.groupCount() == 3) {
                SpellSlotResult(mm.group(1).toInt(), ",\\s*".toRegex().split(mm.group(3).stripHtml()))
            } else null
        }

        private data class SpellSlotResult(val slots: Int, val spells: List<String>)

        private fun extractSpells(mm: Matcher?): List<String> {
            val spells = if (mm?.find() == true) mm.group(1) else ""
            if (spells.isNotBlank())
                return ",\\s*".toRegex().split(spells.stripHtml())
            return listOf()
        }

    }

}

