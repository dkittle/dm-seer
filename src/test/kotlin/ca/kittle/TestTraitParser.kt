package ca.kittle

import ca.kittle.models.SpellCastingTrait
import ca.kittle.models.Trait
import ca.kittle.util.TraitParser
import kotlin.test.Test
import kotlin.test.assertEquals

class TestTraitParser {

    @Test
    fun TestCouatlTraits() {
        val input = """
            <p><em><strong>Innate Spellcasting.</strong></em> The couatl's spellcasting ability is Charisma (spell save DC 14). It can innately cast the following spells, requiring only verbal components:</p>\n<p>At will: <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/detect-evil-and-good\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2064-tooltip\" data-tooltip-json-href=\"/spells/2064/tooltip-json\">detect evil and good</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/detect-magic\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2065-tooltip\" data-tooltip-json-href=\"/spells/2065/tooltip-json\">detect magic</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/detect-thoughts\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2067-tooltip\" data-tooltip-json-href=\"/spells/2067/tooltip-json\">detect thoughts</a></p>\n<p>3/day each: <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/bless\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2016-tooltip\" data-tooltip-json-href=\"/spells/2016/tooltip-json\">bless</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/create-food-and-water\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2052-tooltip\" data-tooltip-json-href=\"/spells/2052/tooltip-json\">create food and water</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/cure-wounds\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2056-tooltip\" data-tooltip-json-href=\"/spells/2056/tooltip-json\">cure wounds</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/lesser-restoration\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2164-tooltip\" data-tooltip-json-href=\"/spells/2164/tooltip-json\">lesser restoration</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/protection-from-poison\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2222-tooltip\" data-tooltip-json-href=\"/spells/2222/tooltip-json\">protection from poison</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/sanctuary\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2237-tooltip\" data-tooltip-json-href=\"/spells/2237/tooltip-json\">sanctuary</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/shield\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2247-tooltip\" data-tooltip-json-href=\"/spells/2247/tooltip-json\">shield</a></p>\n<p>1/day each: <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/dream\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2079-tooltip\" data-tooltip-json-href=\"/spells/2079/tooltip-json\">dream</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/greater-restoration\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2129-tooltip\" data-tooltip-json-href=\"/spells/2129/tooltip-json\">greater restoration</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/scrying\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2239-tooltip\" data-tooltip-json-href=\"/spells/2239/tooltip-json\">scrying</a></p>\n<p><em><strong>Magic Weapons.</strong></em> The couatl's weapon attacks are magical.</p>\n<p><em><strong>Shielded Mind.</strong></em> The couatl is immune to scrying and to any effect that would sense its emotions, read its thoughts, or detect its location.</p>
        """.trimIndent()

        val traits = TraitParser.parseTraits(input)
        assertEquals(3, traits.size)
        assertEquals(true, traits[0] is SpellCastingTrait)
        assertEquals(true, traits[1] is Trait)
        assertEquals(true, traits[2] is Trait)
    }

    @Test
    fun TestSolarTraits() {
        val input = """
            <p><em><strong>Angelic Weapons.</strong></em> The solar's weapon attacks are magical. When the solar hits with any weapon, the weapon deals an extra <span data-dicenotation=\"6d8\" data-rolltype=\"damage\" data-rollaction=\"Angelic Weapons\" data-rolldamagetype=\"radiant\">6d8</span> radiant damage (included in the attack).</p>\n<p><em><strong>Divine Awareness.</strong></em> The solar knows if it hears a lie.</p>\n<p><em><strong>Innate Spellcasting.</strong></em> The solar's spellcasting ability is Charisma (spell save DC 25). It can innately cast the following spells, requiring no material components:</p>\n<p>At will: <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/detect-evil-and-good\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2064-tooltip\" data-tooltip-json-href=\"/spells/2064/tooltip-json\">detect evil and good</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/invisibility\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2159-tooltip\" data-tooltip-json-href=\"/spells/2159/tooltip-json\">invisibility</a> (self only)</p>\n<p>3/day each: <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/blade-barrier\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2015-tooltip\" data-tooltip-json-href=\"/spells/2015/tooltip-json\">blade barrier</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/dispel-evil-and-good\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2071-tooltip\" data-tooltip-json-href=\"/spells/2071/tooltip-json\">dispel evil and good</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/resurrection\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2232-tooltip\" data-tooltip-json-href=\"/spells/2232/tooltip-json\">resurrection</a></p>\n<p>1/day each: <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/commune\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2033-tooltip\" data-tooltip-json-href=\"/spells/2033/tooltip-json\">commune</a>, <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/control-weather\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2050-tooltip\" data-tooltip-json-href=\"/spells/2050/tooltip-json\">control weather</a></p>\n<p><em><strong>Magic Resistance.</strong></em> The solar has advantage on saving throws against spells and other magical effects.</p>",
              """.trimIndent()
        val traits = TraitParser.parseTraits(input)
        assertEquals(4, traits.size)
        assertEquals(true, traits[0] is Trait)
        assertEquals(true, traits[1] is Trait)
        assertEquals(true, traits[2] is SpellCastingTrait)
        assertEquals(true, traits[3] is Trait)
    }
}
