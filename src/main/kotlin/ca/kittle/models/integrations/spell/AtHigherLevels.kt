package ca.kittle.integrations.spells

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AtHigherLevels (

  @SerialName("higherLevelDefinitions" ) var higherLevelDefinitions : ArrayList<HigherLevelDefinitions> = arrayListOf(),
  @SerialName("additionalAttacks"      ) var additionalAttacks      : ArrayList<String>                 = arrayListOf(),
  @SerialName("additionalTargets"      ) var additionalTargets      : ArrayList<String>                 = arrayListOf(),
  @SerialName("areaOfEffect"           ) var areaOfEffect           : ArrayList<String>                 = arrayListOf(),
  @SerialName("duration"               ) var duration               : ArrayList<String>                 = arrayListOf(),
  @SerialName("creatures"              ) var creatures              : ArrayList<String>                 = arrayListOf(),
  @SerialName("special"                ) var special                : ArrayList<String>                 = arrayListOf(),
  @SerialName("points"                 ) var points                 : ArrayList<String>                 = arrayListOf(),
  @SerialName("range"                  ) var range                  : ArrayList<String>                 = arrayListOf()

)
