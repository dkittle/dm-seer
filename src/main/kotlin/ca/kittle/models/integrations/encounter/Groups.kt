package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Groups (

  @SerialName("id"    ) var id    : String? = null,
  @SerialName("order" ) var order : Int?    = null,
  @SerialName("name"  ) var name  : String? = null

)
