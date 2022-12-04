package ca.kittle.resources;

import ca.kittle.util.DiceRoller;
import org.junit.jupiter.api.Test;

public class TestDiceRoller {

    @Test
    public void TestD6() {
        DiceRoller roller = DiceRoller.build(("1d6"));
        assert(roller.numberDice() == 1);
        assert(roller.numberSides() == 6);
        assert(roller.numberKeep() == 1);
        assert(roller.modifier() == 0);
    }

    @Test
    public void TestMinusD6() {
        DiceRoller roller = DiceRoller.build(("-1d6"));
        assert(roller.numberDice() == -1);
        assert(roller.numberSides() == 6);
        assert(roller.numberKeep() == -1);
        assert(roller.modifier() == 0);
    }

    @Test
    public void TestD6Plus3() {
        DiceRoller roller = DiceRoller.build(("d6+3"));
        assert(roller.numberDice() == 1);
        assert(roller.numberSides() == 6);
        assert(roller.numberKeep() == 1);
        assert(roller.modifier() == 3);
    }

    @Test
    public void Test4D6Keep3() {
        DiceRoller roller = DiceRoller.build(("4d6k3"));
        assert(roller.numberDice() == 4);
        assert(roller.numberSides() == 6);
        assert(roller.numberKeep() == 3);
        assert(roller.modifier() == 0);
    }

    @Test
    public void TestOnlyPositiveModifier() {
        DiceRoller roller = DiceRoller.build(("+7"));
        assert(roller.numberDice() == 0);
        assert(roller.numberSides() == 0);
        assert(roller.numberKeep() == 0);
        assert(roller.modifier() == 7);
    }

    @Test
    public void TestOnlyNegativeModifier() {
        DiceRoller roller = DiceRoller.build(("-5"));
        assert(roller.numberDice() == 0);
        assert(roller.numberSides() == 0);
        assert(roller.numberKeep() == 0);
        assert(roller.modifier() == -5);
    }

    @Test
    public void TestFourD8() {
        DiceRoller roller = DiceRoller.build(("4d8"));
        assert(roller.numberDice() == 4);
        assert(roller.numberSides() == 8);
        assert(roller.numberKeep() == 4);
        assert(roller.modifier() == 0);
    }

    @Test
    public void TestFourD8Plus24() {
        DiceRoller roller = DiceRoller.build(("4d8+24"));
        assert(roller.numberDice() == 4);
        assert(roller.numberSides() == 8);
        assert(roller.numberKeep() == 4);
        assert(roller.modifier() == 24);
    }

    @Test
    public void TestFourD8Minus3() {
        DiceRoller roller = DiceRoller.build(("4d8-3"));
        assert(roller.numberDice() == 4);
        assert(roller.numberSides() == 8);
        assert(roller.numberKeep() == 4);
        assert(roller.modifier() == -3);
    }

    @Test
    public void TestD6Plus3Plus2D8() {
        DiceRoller roller = DiceRoller.build(("d6+3+2d8"));
        System.out.println(roller.toString());
        assert(roller.numberDice() == 1);
        assert(roller.numberSides() == 6);
        assert(roller.numberKeep() == 1);
        assert(roller.modifier() == 3);
    }

//    @Test
//    public void TestD6Plus2D8() {
//        DiceRoller roller = DiceRoller.build(("d6+2d8"));
//        System.out.println(roller.toString());
//        assert(roller.numberDice() == 1);
//        assert(roller.numberSides() == 6);
//        assert(roller.numberKeep() == 1);
//        assert(roller.modifier() == 0);
//    }

}
