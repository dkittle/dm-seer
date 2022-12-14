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
    val regr = "\r".toRegex()
    val wip = regr.replace(this, " ").trim()
    val regn = "\n".toRegex()
    return regn.replace(wip, " ").trim()
}

fun String.isPositiveInteger(): Boolean {
    return this.all { char -> char.isDigit() }
}

object TraitParser {

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
                result.add(
                    parseSpellCasting(
                        m.group(1), md.group(1).stripHtml(),
                        ResetType.LONGREST, md.group(1)))
            else
                result.add(parseNonSpell(m.group(1), md.group(1).stripHtml()))
        }
        return result
    }

    private fun parseNonSpell(name: String, description: String): BaseTrait {
        val rolls = ActionParser.findRolls(description)
        val p = Pattern.compile("\\((\\d+?)/(Day)").matcher(name)
        val uses =
            if (p.find())
                Uses(p.group(1).toInt(), ResetTypes.getResetTypeByName(p.group(2)) ?: ResetType.NONE) else null
        if (rolls.isNotEmpty())
            return RollableTrait(name, description.stripHtml().stripNewLine(), uses?.resets, null, uses?.uses, rolls)
        return Trait(name, description.stripHtml().stripNewLine(), uses?.resets, null, uses?.uses)
    }

    fun parseSpellCasting(name: String, description: String, resets: ResetType?, spells: String): SpellCastingTrait {
        val p = Pattern.compile("(.+?)</p>").matcher(spells)
        val shortDescription = if (p.find()) p.group(1).stripHtml().stripNewLine() else description.stripHtml().stripNewLine()
        if (!findAtWill(spells).isEmpty()) {
            return SpellsPerDayTrait(name, shortDescription, resets, parseSpellUses(spells))
        }
        return SpellSlotsTrait(name, shortDescription, resets, parseSpellUses(spells))
    }

    fun parseSpellUses(description: String): List<SpellUses> {
        return buildList<SpellUses> {
            var s = findAtWill(description)
            if (s.isNotEmpty()) add(SpellUses(0, 10000, s))
            for (i in 5 downTo 1) {
                s = findUsesPerDay(i, description)
                if (s.isNotEmpty()) add(SpellUses(this.size, i, s))
            }
            s = findCantrips(description)
            if (s.isNotEmpty()) add(SpellUses(0, 10000, s))
            for (i in 1..9) {
                val levelSpells = findSpellSlotsPerDay(i, description)
                if (levelSpells != null) add(SpellUses(i, levelSpells.slots, levelSpells.spells))
            }

        }
    }

    fun findAtWill(input: String): List<String> {
        val mm = Pattern.compile("At will: (.+?)</p>").matcher(input)
        return extractSpells(mm)
    }

    fun findUsesPerDay(uses: Int, input: String): List<String> {
        val mm = Pattern.compile("$uses/day.*?: (.+?)</p>").matcher(input)
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

