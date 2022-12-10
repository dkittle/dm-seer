package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Conditions (

  @SerialName("id"    ) var id    : Int?    = null,
  @SerialName("level" ) var level : String? = null

)
