package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Traits (

  @SerialName("personalityTraits" ) var personalityTraits : String? = null,
  @SerialName("ideals"            ) var ideals            : String? = null,
  @SerialName("bonds"             ) var bonds             : String? = null,
  @SerialName("flaws"             ) var flaws             : String? = null,
  @SerialName("appearance"        ) var appearance        : String? = null

)
