package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Range (

  @SerialName("label"          ) var label          : String? = null,
  @SerialName("longRangeValue" ) var longRangeValue : Int?    = null,
  @SerialName("value"          ) var value          : Int?    = null,
  @SerialName("origin"         ) var origin         : String? = null

)
