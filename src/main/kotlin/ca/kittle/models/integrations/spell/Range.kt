package ca.kittle.integrations.spells


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Range (

  @SerialName("origin"     ) var origin     : String? = null,
  @SerialName("rangeValue" ) var rangeValue : Int?    = null,
  @SerialName("aoeType"    ) var aoeType    : String? = null,
  @SerialName("aoeValue"   ) var aoeValue   : String? = null

)
