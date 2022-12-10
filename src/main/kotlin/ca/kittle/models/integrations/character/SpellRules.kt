package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellRules (

  @SerialName("multiClassSpellSlotDivisor"  ) var multiClassSpellSlotDivisor  : Int?                      = null,
  @SerialName("isRitualSpellCaster"         ) var isRitualSpellCaster         : Boolean?                  = null,
  @SerialName("levelCantripsKnownMaxes"     ) var levelCantripsKnownMaxes     : ArrayList<Int>            = arrayListOf(),
  @SerialName("levelSpellKnownMaxes"        ) var levelSpellKnownMaxes        : ArrayList<Int>            = arrayListOf(),
  @SerialName("levelSpellSlots"             ) var levelSpellSlots             : ArrayList<ArrayList<Int>> = arrayListOf(),
  @SerialName("multiClassSpellSlotRounding" ) var multiClassSpellSlotRounding : Int?                      = null

)
