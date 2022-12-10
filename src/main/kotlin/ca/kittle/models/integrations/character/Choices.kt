package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choices (

    @SerialName("race"              ) var species              : ArrayList<ca.kittle.models.integrations.character.Species>              = arrayListOf(),
    @SerialName("class"             ) var charClass             : ArrayList<ca.kittle.models.integrations.character.CharClass>             = arrayListOf(),
    @SerialName("background"        ) var background        : ArrayList<ca.kittle.models.integrations.character.Background>        = arrayListOf(),
    @SerialName("item"              ) var item              : String?                      = null,
    @SerialName("feat"              ) var feat              : ArrayList<String>            = arrayListOf(),
    @SerialName("choiceDefinitions" ) var choiceDefinitions : ArrayList<ca.kittle.models.integrations.character.ChoiceDefinitions> = arrayListOf()

)
