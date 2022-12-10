package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Ability (

  @SerialName("name"     ) var name     : String? = null,
  @SerialName("save"     ) var save     : Int?    = null,
  @SerialName("score"    ) var score    : Int?    = null,
  @SerialName("label"    ) var label    : String? = null,
  @SerialName("modifier" ) var modifier : Int?    = null

)
