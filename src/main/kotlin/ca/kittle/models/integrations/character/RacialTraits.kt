package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RacialTraits (

  @SerialName("definition" ) var definition : ca.kittle.models.integrations.character.Definition?

)
