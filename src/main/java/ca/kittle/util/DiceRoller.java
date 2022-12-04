package ca.kittle.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record DiceRoller(int numberDice, int numberSides, int numberKeep, int modifier) {

    public static DiceRoller build (String diceRoll) {
        int numberDice = 0;
        int numberSides = 0;
        int numberKeep = 0;
        int modifier = 0;

        Pattern p = Pattern.compile("([+\\-]?\\d+)?(d[+\\-]?\\d+)?(k[+\\-]?\\d+)?([+\\-]\\d+)?(.+)?");
        Matcher m = p.matcher(diceRoll);
        if (m.find()) {
            if (m.group(1) != null && !m.group(1).isEmpty()) numberDice = Integer.parseInt(m.group(1));
            if (m.group(2) != null && !m.group(2).isEmpty()) {
                numberSides = Integer.parseInt(m.group(2).substring(1));
                if (numberDice == 0)
                    numberDice = 1;
            }
            if (m.group(3) != null && !m.group(3).isEmpty()) numberKeep = Integer.parseInt(m.group(3).substring(1));
                else numberKeep = numberDice;
            if (m.group(4) != null && !m.group(4).isEmpty()) modifier = Integer.parseInt(m.group(4));
            if (m.group(5) != null && !m.group(5).isEmpty()) System.out.println("Remainder: " + m.group(5));
            if (numberSides == 0) {
                modifier = numberDice;
                numberDice = 0;
                numberKeep = 0;
            }
        }
        return new DiceRoller(numberDice, numberSides, numberKeep, modifier);
    }

    public int roll() {
        return 0;
    }

}
