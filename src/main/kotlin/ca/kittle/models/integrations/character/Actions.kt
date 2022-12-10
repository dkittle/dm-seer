package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Actions(

    @SerialName("race") var species: List<Action>? = null,
    @SerialName("class") var actions: List<Action>? = null,
    @SerialName("background") var background: List<Action>? = null,
    @SerialName("item") var item: List<Action>? = null,
    @SerialName("feat") var feat: List<Action>? = null

)
