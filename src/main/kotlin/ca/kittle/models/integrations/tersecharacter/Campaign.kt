package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Campaign (

  @SerialName("name"     ) var name     : String? = null,
  @SerialName("id"       ) var id       : Int?    = null,
  @SerialName("dmUserId" ) var dmUserId : Int?    = null,
  @SerialName("url"      ) var url      : String? = null

)
