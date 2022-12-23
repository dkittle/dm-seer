package ca.kittle.models

import ca.kittle.util.DiceRoller
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.regex.Pattern

@Serializable
data class Dice(
    val diceCount: Int?,
    val diceValue: Int?,
    val diceMultiplier: Int?,
    val fixedValue: Int?,
    var diceString: String? = ""
) {

    init {
        if (diceString.isNullOrBlank()) {
            diceString = "${diceCount}d${diceValue}"
            if (fixedValue != null && fixedValue != 0)
                diceString += "${if (fixedValue > 0) "+" else ""}$fixedValue"
        }
    }

    companion object {
        fun parseRoll(diceRoll: String): Dice {
            var numberDice = 0
            var numberSides = 0
            var numberKeep = 0
            var modifier = 0
            val p = Pattern.compile("([+\\-]?\\d+)?(d[+\\-]?\\d+)?(k[+\\-]?\\d+)?([+\\-]\\d+)?(.+)?")
            val m = p.matcher(diceRoll.replace(" ", ""))
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
            return Dice(numberDice, numberSides, 1, modifier, diceRoll)
        }

    }
}
