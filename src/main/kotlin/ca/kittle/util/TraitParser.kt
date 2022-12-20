package ca.kittle.util

import ca.kittle.models.Trait
import ca.kittle.models.BasicTrait
import ca.kittle.models.SpellCastingTrait
import mu.KotlinLogging
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
        fun parseTraits(t: String): List<BasicTrait> {
            val traitString = t.stripNewLine()
            val p = Pattern.compile("<strong>(.+?)</strong>")
            val pd = Pattern.compile("</strong></em>(.+?)<strong>")
            val m = p.matcher(traitString)
            val md = pd.matcher("$traitString<strong>")
            var result = mutableListOf<BasicTrait>()
            while (m.find() && md.find()) {
                if (m.group(1).equals("Innate Spellcasting."))
                    result.add(parseSpellCasting(m.group(1), md.group(1)))
                else
                    result.add(Trait(m.group(1), md.group(1).stripHtml()))
            }
            return result
        }
        
        private fun parseSpellCasting(name: String, description: String): BasicTrait {
            val md = Pattern.compile("(.+?)</p>").matcher(description)
            val desc = if (md.find()) md.group(1).stripHtml().trim() else ""
            val mm = Pattern.compile("At will: (.+?)</p>").matcher(description)
            val atWill = if (mm.find()) mm.group(1).stripHtml() else ""
            val m3 = Pattern.compile("3/day each: (.+?)</p>").matcher(description)
            val threePer = if (m3.find()) m3.group(1).stripHtml() else ""
            val m1 = Pattern.compile("1/day each: (.+?)</p>").matcher(description)
            val onePer = if (m1.find()) m1.group(1).stripHtml() else ""
            val regex = ",\\s*".toRegex()
            return SpellCastingTrait(name, desc,
                regex.split(atWill.stripHtml()),
                regex.split(threePer.stripHtml()),
                regex.split(onePer.stripHtml()))
        }
    }

}

