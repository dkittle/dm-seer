package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Actions (

    @SerialName("race"       ) var species       : List<ca.kittle.models.integrations.character.Species>,
    @SerialName("class"      ) var charClass      : List<ca.kittle.models.integrations.character.CharClass>,
    @SerialName("background" ) var background : String?           = null,
    @SerialName("item"       ) var item       : String?           = null,
    @SerialName("feat"       ) var feat       : List<String>

)
