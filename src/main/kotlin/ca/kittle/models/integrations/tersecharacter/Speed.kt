package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Speed (

  @SerialName("distance" ) var distance : Int?    = null,
  @SerialName("name"     ) var name     : String? = null

)
