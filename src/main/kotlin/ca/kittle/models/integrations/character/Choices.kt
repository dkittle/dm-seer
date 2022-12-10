package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choices(

    @SerialName("race") var species: ArrayList<Species>? = null,
    @SerialName("class") var actions: ArrayList<Action>? = null,
    @SerialName("background") var background: List<Background>? = null,
    @SerialName("item") var item: String? = null,
    @SerialName("feat") var feat: ArrayList<Feat>? = null,
    @SerialName("choiceDefinitions") var choiceDefinitions: ArrayList<ChoiceDefinitions>? = null

)
