package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseCreatureSpells {
    abstract var uses: Int
    abstract var spells: List<String>
}

@Serializable
@SerialName("usesEachPerDaySpells")
data class PerDaySpells (
    override var uses: Int,
    override var spells: List<String>
) : BaseCreatureSpells()

@Serializable
@SerialName("spellSlotsPerDaySpells")
data class SpellSlotSpells (
    override var uses: Int,
    override var spells: List<String>
) : BaseCreatureSpells()
