package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Value (

  @SerialName("diceValue"      ) var diceValue      : Int?    = null,
  @SerialName("fixedValue"     ) var fixedValue     : Int?    = null,
  @SerialName("diceCount"      ) var diceCount      : Int?    = null,
  @SerialName("diceString"     ) var diceString     : String? = null,
  @SerialName("diceMultiplier" ) var diceMultiplier : String? = null

)
