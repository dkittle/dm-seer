package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Resistance (

  @SerialName("name" ) var name : String? = null,
  @SerialName("type" ) var type : Int?    = null

)
