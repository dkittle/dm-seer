package ca.kittle

import ca.kittle.models.Dice
import kotlin.test.Test
import kotlin.test.assertEquals

class TestDice {

    @Test
    fun testNoDiceString() {
        val d6 = Dice(1, 6, 0, 0, null)
        assert("1d6".equals(d6.diceString) )
        val threeD8 = Dice(3, 8, 0, 0, null)
        assert("3d8".equals(threeD8.diceString) )
        val sixD10Plus12 = Dice(6, 10, 0, 12, null)
        assert("6d10+12".equals(sixD10Plus12.diceString) )
        val fourD4Minus2 = Dice(4, 4, 0, -2, null)
        assert("4d4-2".equals(fourD4Minus2.diceString) )
    }
}
