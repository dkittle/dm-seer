package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bonds (

  @SerialName("id"          ) var id          : Int?    = null,
  @SerialName("description" ) var description : String? = null,
  @SerialName("diceRoll"    ) var diceRoll    : Int?    = null

)
