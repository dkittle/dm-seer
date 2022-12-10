package ca.kittle.models.integrations.encounter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Campaign (

  @SerialName("id"   ) var id   : Int?    = null,
  @SerialName("name" ) var name : String? = null

)
