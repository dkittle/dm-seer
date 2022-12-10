package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Range (

  @SerialName("range"                    ) var range                    : String?  = null,
  @SerialName("longRange"                ) var longRange                : String?  = null,
  @SerialName("aoeType"                  ) var aoeType                  : String?  = null,
  @SerialName("aoeSize"                  ) var aoeSize                  : String?  = null,
  @SerialName("hasAoeSpecialDescription" ) var hasAoeSpecialDescription : Boolean? = null,
  @SerialName("minimumRange"             ) var minimumRange             : String?  = null

)
