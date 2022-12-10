package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Configuration (

  @SerialName("startingEquipmentType" ) var startingEquipmentType : Int?     = null,
  @SerialName("abilityScoreType"      ) var abilityScoreType      : Int?     = null,
  @SerialName("showHelpText"          ) var showHelpText          : Boolean? = null

)
