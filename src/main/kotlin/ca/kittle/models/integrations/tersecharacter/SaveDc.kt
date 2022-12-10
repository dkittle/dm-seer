package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SaveDc (

  @SerialName("sources" ) var sources : ArrayList<String> = arrayListOf(),
  @SerialName("value"   ) var value   : Int?              = null

)
