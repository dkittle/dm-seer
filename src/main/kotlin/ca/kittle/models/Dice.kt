package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dice(

    var diceCount: Int? = null,
    var diceValue: Int? = null,
    var diceMultiplier: Int? = null,
    var fixedValue: String? = null,
    var diceString: String? = null

)
