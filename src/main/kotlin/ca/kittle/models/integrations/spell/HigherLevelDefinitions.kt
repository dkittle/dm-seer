package ca.kittle.integrations.spells

import ca.kittle.models.Dice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HigherLevelDefinitions (

  @SerialName("level"   ) var level   : Int?    = null,
  @SerialName("typeId"  ) var typeId  : Int?    = null,
  @SerialName("dice"    ) var dice    : Dice?   = null,
  @SerialName("value"   ) var value   : String? = null,
  @SerialName("details" ) var details : String? = null

)
