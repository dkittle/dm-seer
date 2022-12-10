package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DdbTerseCharacterResponse (

  @SerialName("foundCharacters" ) var foundCharacters : ArrayList<Character> = arrayListOf(),
  @SerialName("queuedIds"       ) var queuedIds       : ArrayList<String>          = arrayListOf(),
  @SerialName("notFoundIds"     ) var notFoundIds     : ArrayList<String>          = arrayListOf()

)
