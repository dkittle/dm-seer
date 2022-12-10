package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sources (

  @SerialName("sourceId"   ) var sourceId   : Int?    = null,
  @SerialName("pageNumber" ) var pageNumber : String? = null,
  @SerialName("sourceType" ) var sourceType : Int?    = null

)
