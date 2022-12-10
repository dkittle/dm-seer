package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Modifiers(

    @SerialName("race") var species: ArrayList<ca.kittle.models.integrations.character.Species> = arrayListOf(),
    @SerialName("class") var actions: ArrayList<ca.kittle.models.integrations.character.Action> = arrayListOf(),
    @SerialName("background") var background: ArrayList<ca.kittle.models.integrations.character.Background> = arrayListOf(),
    @SerialName("item") var item: ArrayList<Item> = arrayListOf(),
    @SerialName("feat") var feat: ArrayList<Feat> = arrayListOf(),
    @SerialName("condition") var condition: ArrayList<String> = arrayListOf()

)
