package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Damage (

  @SerialName("type"  ) var type  : String? = null,
  @SerialName("value" ) var value : Value?  = Value()

)
