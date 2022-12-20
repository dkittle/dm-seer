package ca.kittle

import ca.kittle.models.Action
import ca.kittle.models.AttackAction
import ca.kittle.models.RollableAction
import ca.kittle.models.SpellCastingTrait
import ca.kittle.util.ActionParser
import kotlin.test.Test
import kotlin.test.assertEquals

class TestActionParser {

//    @Test
//    fun TestCouatlActions() {
//        val input = """
//            <p><em><strong>Bite.</strong> Melee Weapon Attack:</em> <span data-dicenotation=\"1d20+8\" data-rolltype=\"to hit\" data-rollaction=\"Bite\">+8</span> to hit, reach 5 ft., one creature. <em>Hit:</em> 8 <span data-dicenotation=\"1d6+5\" data-rolltype=\"damage\" data-rollaction=\"Bite\" data-rolldamagetype=\"piercing\">(1d6 + 5)</span> piercing damage, and the target must succeed on a DC 13 Constitution saving throw or be <a class=\"tooltip-hover condition-tooltip\" href=\"/compendium/rules/basic-rules/appendix-a-conditions#Poisoned\" aria-haspopup=\"true\" data-tooltip-href=\"/conditions/11-tooltip\" data-tooltip-json-href=\"/conditions/11/tooltip-json\">poisoned</a> for 24 hours. Until this poison ends, the target is <a class=\"tooltip-hover condition-tooltip\" href=\"/compendium/rules/basic-rules/appendix-a-conditions#Unconscious\" aria-haspopup=\"true\" data-tooltip-href=\"/conditions/15-tooltip\" data-tooltip-json-href=\"/conditions/15/tooltip-json\">unconscious</a>. Another creature can use an action to shake the target awake.</p>\n<p><em><strong>Constrict.</strong> Melee Weapon Attack:</em> <span data-dicenotation=\"1d20+6\" data-rolltype=\"to hit\" data-rollaction=\"Constrict\">+6</span> to hit, reach 10 ft., one Medium or smaller creature. <em>Hit:</em> 10 <span data-dicenotation=\"2d6+3\" data-rolltype=\"damage\" data-rollaction=\"Constrict\" data-rolldamagetype=\"bludgeoning\">(2d6 + 3)</span> bludgeoning damage, and the target is <a class=\"tooltip-hover condition-tooltip\" href=\"/compendium/rules/basic-rules/appendix-a-conditions#Grappled\" aria-haspopup=\"true\" data-tooltip-href=\"/conditions/6-tooltip\" data-tooltip-json-href=\"/conditions/6/tooltip-json\">grappled</a> (escape DC 15). Until this grapple ends, the target is <a class=\"tooltip-hover condition-tooltip\" href=\"/compendium/rules/basic-rules/appendix-a-conditions#Restrained\" aria-haspopup=\"true\" data-tooltip-href=\"/conditions/13-tooltip\" data-tooltip-json-href=\"/conditions/13/tooltip-json\">restrained</a>, and the couatl can't constrict another target.</p>\n<p><em><strong>Change Shape.</strong></em> The couatl magically polymorphs into a humanoid or beast that has a challenge rating equal to or less than its own, or back into its true form. It reverts to its true form if it dies. Any equipment it is wearing or carrying is absorbed or borne by the new form (the couatl's choice).</p>\n<p>In a new form, the couatl retains its game statistics and ability to speak, but its AC, movement modes, Strength, Dexterity, and other actions are replaced by those of the new form, and it gains any statistics and capabilities (except class features, legendary actions, and lair actions) that the new form has but that it lacks. If the new form has a bite attack, the couatl can use its bite in that form.</p>
//        """.trimIndent()
//
//        val actions = ActionParser.parseActions(input)
//        assertEquals(3, actions.size)
//        assertEquals(true, actions[0] is AttackAction)
//        assertEquals(true, actions[1] is AttackAction)
//        assertEquals(true, actions[2] is Action)
//    }
//
//    @Test
//    fun TestSolarActions() {
//        val input = """
//            <p><em><strong>Multiattack.</strong></em> The solar makes two greatsword attacks.</p>\n<p><em><strong>Greatsword.</strong> Melee Weapon Attack:</em> <span data-dicenotation=\"1d20+15\" data-rolltype=\"to hit\" data-rollaction=\"Greatsword\">+15</span> to hit, reach 5 ft., one target. <em>Hit:</em> 22 <span data-dicenotation=\"4d6+8\" data-rolltype=\"damage\" data-rollaction=\"Greatsword\" data-rolldamagetype=\"slashing\">(4d6 + 8)</span> slashing damage plus 27 <span data-dicenotation=\"6d8\" data-rolltype=\"damage\" data-rollaction=\"Greatsword\" data-rolldamagetype=\"radiant\">(6d8)</span> radiant damage.</p>\n<p><em><strong>Slaying Longbow.</strong> Ranged Weapon Attack:</em> <span data-dicenotation=\"1d20+13\" data-rolltype=\"to hit\" data-rollaction=\"Slaying Longbow\">+13</span> to hit, range 150/600 ft., one target. <em>Hit:</em> 15 <span data-dicenotation=\"2d8+6\" data-rolltype=\"damage\" data-rollaction=\"Slaying Longbow\" data-rolldamagetype=\"piercing\">(2d8 + 6)</span> piercing damage plus 27 <span data-dicenotation=\"6d8\" data-rolltype=\"damage\" data-rollaction=\"Slaying Longbow\" data-rolldamagetype=\"radiant\">(6d8)</span> radiant damage. If the target is a creature that has 100 hit points or fewer, it must succeed on a DC 15 Constitution saving throw or die.</p>\n<p><em><strong>Flying Sword.</strong></em> The solar releases its greatsword to hover magically in an unoccupied space within 5 feet of it. If the solar can see the sword, the solar can mentally command it as a bonus action to fly up to 50 feet and either make one attack against a target or return to the solar's hands. If the hovering sword is targeted by any effect, the solar is considered to be holding it. The hovering sword falls if the solar dies.</p>\n<p><em><strong>Healing Touch (4/Day).</strong></em> The solar touches another creature. The target magically regains 40 <span data-dicenotation=\"8d8+4\" data-rolltype=\"heal\" data-rollaction=\"Healing Touch\">(8d8 + 4)</span> hit points and is freed from any curse, disease, poison, blindness, or deafness.</p>
//        """.trimIndent()
//        val actions = ActionParser.parseActions(input)
//        assertEquals(5, actions.size)
//        assertEquals(true, actions[0] is Action)
//        assertEquals(true, actions[1] is AttackAction)
//        assertEquals(true, actions[2] is AttackAction)
//        assertEquals(true, actions[3] is Action)
//        assertEquals(true, actions[4] is RollableAction)
//    }
//
//    @Test
//    fun TestSolarLegendary() {
//        val input = """
//                  "<p>The solar can take 3 legendary actions, choosing from the options below. Only one legendary action option can be used at a time and only at the end of another creature's turn. The solar regains spent legendary actions at the start of its turn.</p>\n<p><strong>Teleport.</strong> The solar magically teleports, along with any equipment it is wearing or carrying, up to 120 feet to an unoccupied space it can see.</p>\n<p><strong>Searing Burst (Costs 2 Actions).</strong> The solar emits magical, divine energy. Each creature of its choice in a 10-foot radius must make a DC 23 Dexterity saving throw, taking 14 <span data-dicenotation=\"4d6\" data-rolltype=\"damage\" data-rollaction=\"Searing Burst\" data-rolldamagetype=\"fire\">(4d6)</span> fire damage plus 14 <span data-dicenotation=\"4d6\" data-rolltype=\"damage\" data-rollaction=\"Searing Burst\" data-rolldamagetype=\"radiant\">(4d6)</span> radiant damage on a failed save, or half as much damage on a successful one.</p>\n<p><strong>Blinding Gaze (Costs 3 Actions).</strong> The solar targets one creature it can see within 30 feet of it. If the target can see it, the target must succeed on a DC 15 Constitution saving throw or be <a class=\"tooltip-hover condition-tooltip\" href=\"/compendium/rules/basic-rules/appendix-a-conditions#Blinded\" aria-haspopup=\"true\" data-tooltip-href=\"/conditions/1-tooltip\" data-tooltip-json-href=\"/conditions/1/tooltip-json\">blinded</a> until magic such as the <a class=\"tooltip-hover spell-tooltip\" href=\"/spells/lesser-restoration\" aria-haspopup=\"true\" data-tooltip-href=\"/spells/2164-tooltip\" data-tooltip-json-href=\"/spells/2164/tooltip-json\">lesser restoration</a> spell removes the blindness.</p>
//        """.trimIndent()
//        val actions = ActionParser.parseActions(input)
//        assertEquals(3, actions.size)
//        assertEquals(true, actions[0] is Action)
//        assertEquals(true, actions[1] is RollableAction)
//        assertEquals(true, actions[2] is Action)
//    }
}
