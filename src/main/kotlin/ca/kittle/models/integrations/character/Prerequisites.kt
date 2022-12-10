package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prerequisites (

  @SerialName("description"          ) var description          : String?                         = null,
  @SerialName("prerequisiteMappings" ) var prerequisiteMappings : ArrayList<ca.kittle.models.integrations.character.PrerequisiteMappings> = arrayListOf()

)
