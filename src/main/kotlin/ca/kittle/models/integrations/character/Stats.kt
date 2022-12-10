package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats (

  @SerialName("id"    ) val id    : Int?,
  @SerialName("name"  ) val name  : String?,
  @SerialName("value" ) val value : Int?

)
