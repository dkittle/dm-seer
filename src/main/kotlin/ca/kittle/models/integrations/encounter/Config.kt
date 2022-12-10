package ca.kittle.models.integrations.encounter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config (

  @SerialName("encounterLimit"        ) var encounterLimit        : String? = null,
  @SerialName("currentEncounterCount" ) var currentEncounterCount : Int?    = null

)
