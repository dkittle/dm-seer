package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HitPointInfo (

  @SerialName("maximum" ) var maximum : Int? = null,
  @SerialName("current" ) var current : Int? = null,
  @SerialName("temp"    ) var temp    : Int? = null

)
