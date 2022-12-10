package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spells(

    @SerialName("race") var race: ArrayList<Spell>? = null,
    @SerialName("class") var actions: ArrayList<Spell>? = null,
    @SerialName("background") var background: List<Spell>? = null,
    @SerialName("item") var item: ArrayList<Spell>? = null,
    @SerialName("feat") var feat: ArrayList<Spell>? = null

)
