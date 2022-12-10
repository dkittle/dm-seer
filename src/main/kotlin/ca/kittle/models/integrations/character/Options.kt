package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Options (

  @SerialName("id"          ) var id          : Int?    = null,
  @SerialName("label"       ) var label       : String? = null,
  @SerialName("description" ) var description : String? = null

)
