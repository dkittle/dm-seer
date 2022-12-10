package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BonusStats (

  @SerialName("id"    ) var id    : Int?    = null,
  @SerialName("name"  ) var name  : String? = null,
  @SerialName("value" ) var value : String? = null

)
