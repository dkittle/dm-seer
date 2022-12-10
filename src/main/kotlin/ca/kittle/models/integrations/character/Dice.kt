package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dice (

  @SerialName("diceCount"      ) var diceCount      : Int?    = null,
  @SerialName("diceValue"      ) var diceValue      : Int?    = null,
  @SerialName("diceMultiplier" ) var diceMultiplier : String? = null,
  @SerialName("fixedValue"     ) var fixedValue     : String? = null,
  @SerialName("diceString"     ) var diceString     : String? = null

)
