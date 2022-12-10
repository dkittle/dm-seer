package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassSpells (

  @SerialName("entityTypeId"     ) var entityTypeId     : Int?              = null,
  @SerialName("characterClassId" ) var characterClassId : Int?              = null,
  @SerialName("spells"           ) var spells           : ArrayList<String> = arrayListOf()

)
