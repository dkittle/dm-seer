package ca.kittle

import ca.kittle.util.DiceRoller
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestDiceRoller {

    @Test
    fun TestD6() {
        val roller: DiceRoller = DiceRoller.build("1d6")
        assertEquals(1, roller.numberDice)
        assertEquals(6, roller.numberSides)
        assertEquals(1, roller.numberKeep)
        assertEquals(0, roller.modifier)
    }

    @Test
    fun TestMinusD6() {
        val roller: DiceRoller = DiceRoller.build("-1d6")
        assertEquals(-1, roller.numberDice)
        assertEquals(6, roller.numberSides)
        assertEquals(-1, roller.numberKeep)
        assertEquals(0, roller.modifier)
    }

    @Test
    fun TestD6Plus3() {
        val roller: DiceRoller = DiceRoller.build("d6+3")
        assertEquals(1, roller.numberDice)
        assertEquals(6, roller.numberSides)
        assertEquals(1, roller.numberKeep)
        assertEquals(3, roller.modifier)
    }

    @Test
    fun Test4D6Keep3() {
        val roller: DiceRoller = DiceRoller.build("4d6k3")
        assertEquals(4, roller.numberDice)
        assertEquals(6, roller.numberSides)
        assertEquals(3, roller.numberKeep)
        assertEquals(0, roller.modifier)
    }

    @Test
    fun TestOnlyPositivemodifier() {
        val roller: DiceRoller = DiceRoller.build("+7")
        assertEquals(0, roller.numberDice)
        assertEquals(0, roller.numberSides)
        assertEquals(0, roller.numberKeep)
        assertEquals(7, roller.modifier)
    }

    @Test
    fun TestOnlyNegativemodifier() {
        val roller: DiceRoller = DiceRoller.build("-5")
        assertEquals(0, roller.numberDice)
        assertEquals(0, roller.numberSides)
        assertEquals(0, roller.numberKeep)
        assertEquals(-5, roller.modifier)
    }

    @Test
    fun TestFourD8() {
        val roller: DiceRoller = DiceRoller.build("4d8")
        assertEquals(4, roller.numberDice)
        assertEquals(8, roller.numberSides)
        assertEquals(4, roller.numberKeep)
        assertEquals(0, roller.modifier)
    }

    @Test
    fun TestFourD8Plus24() {
        val roller: DiceRoller = DiceRoller.build("4d8+24")
        assertEquals(4, roller.numberDice)
        assertEquals(8, roller.numberSides)
        assertEquals(4, roller.numberKeep)
        assertEquals(24, roller.modifier)
    }

    @Test
    fun TestFourD8Minus3() {
        val roller: DiceRoller = DiceRoller.build("4d8-3")
        assertEquals(4, roller.numberDice)
        assertEquals(8, roller.numberSides)
        assertEquals(4, roller.numberKeep)
        assertEquals(-3, roller.modifier)
    }

    @Test
    fun TestD6Plus3Plus2D8() {
        val roller: DiceRoller = DiceRoller.build("d6+3+2d8")
        System.out.println(roller.toString())
        assertEquals(1, roller.numberDice)
        assertEquals(6, roller.numberSides)
        assertEquals(1, roller.numberKeep)
        assertEquals(3, roller.modifier)
    }

//    @Test
//    fun TestD6Plus2D8() {
//        val roller: DiceRoller = DiceRoller.build("d6+2d8")
//        System.out.println(roller.toString())
//        assertEquals(1, roller.numberDice)
//        assertEquals(6, roller.numberSides)
//        assertEquals(1, roller.numberKeep)
//        assertEquals(0, roller.modifier)
//    }
}
