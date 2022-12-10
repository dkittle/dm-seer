package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CharClass (

  @SerialName("name"         ) var name         : String? = null,
  @SerialName("level"        ) var level        : Int?    = null,
  @SerialName("subclassName" ) var subclassName : String? = null

)
