package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Avatar (

  @SerialName("avatarUrl" ) var avatarUrl : String? = null,
  @SerialName("frameUrl"  ) var frameUrl  : String? = null

)
