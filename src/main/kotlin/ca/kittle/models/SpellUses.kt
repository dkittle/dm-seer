package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpellUses(val order: Int, val uses: Int, val spells: List<String>) {
    companion object {
        fun create(order: Int, uses:Int, spells: String) =
            SpellUses(order, uses,
                spells.split(",").map { it.replace("*","")}.map { it.trim() })
    }
}


//@Serializable
//@SerialName("usesPerDaySpells")
//data class PerDaySpells (
//    override var uses: Int,
//    override var spells: List<String>
//) : BaseSpells()
//
//@Serializable
//@SerialName("spellSlotsPerDaySpells")
//data class SpellSlotSpells (
//    override var uses: Int,
//    override var spells: List<String>
//) : BaseSpells()
