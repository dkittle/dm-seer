package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spells (

    @SerialName("race"       ) var race       : ArrayList<String> = arrayListOf(),
    @SerialName("class"      ) var charClass      : ArrayList<ca.kittle.models.integrations.character.CharClass>  = arrayListOf(),
    @SerialName("background" ) var background : String?           = null,
    @SerialName("item"       ) var item       : ArrayList<String> = arrayListOf(),
    @SerialName("feat"       ) var feat       : ArrayList<String> = arrayListOf()

)
