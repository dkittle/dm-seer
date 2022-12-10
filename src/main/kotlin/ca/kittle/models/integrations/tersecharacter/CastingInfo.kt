package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastingInfo (

    @SerialName("modifiers"    ) var modifiers    : ArrayList<Modifier>    = arrayListOf(),
    @SerialName("spellAttacks" ) var spellAttacks : ArrayList<SpellAttack> = arrayListOf(),
    @SerialName("saveDcs"      ) var saveDcs      : ArrayList<SaveDc>      = arrayListOf()

)
