package ca.kittle.util

import java.util.regex.Pattern

data class DiceRoller(val numberDice: Int, val numberSides: Int, val numberKeep: Int, val modifier: Int) {

    fun roll(): Int {
        return 0
    }

    companion object {
        @JvmStatic
        fun build(diceRoll: String): DiceRoller {
            var numberDice = 0
            var numberSides = 0
            var numberKeep = 0
            var modifier = 0
            val p = Pattern.compile("([+\\-]?\\d+)?(d[+\\-]?\\d+)?(k[+\\-]?\\d+)?([+\\-]\\d+)?(.+)?")
            val m = p.matcher(diceRoll)
            if (m.find()) {
                if (m.group(1) != null && !m.group(1).isEmpty()) numberDice = m.group(1).toInt()
                if (m.group(2) != null && !m.group(2).isEmpty()) {
                    numberSides = m.group(2).substring(1).toInt()
                    if (numberDice == 0) numberDice = 1
                }
                numberKeep =
                    if (m.group(3) != null && !m.group(3).isEmpty()) m.group(3).substring(1).toInt() else numberDice
                if (m.group(4) != null && !m.group(4).isEmpty()) modifier = m.group(4).toInt()
                if (m.group(5) != null && !m.group(5).isEmpty()) println("Remainder: " + m.group(5))
                if (numberSides == 0) {
                    modifier = numberDice
                    numberDice = 0
                    numberKeep = 0
                }
            }
            return DiceRoller(numberDice, numberSides, numberKeep, modifier)
        }
    }
}
