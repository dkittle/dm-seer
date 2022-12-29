package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseFeature {
    abstract var name: String
    abstract var description: String
}

@Serializable
@SerialName("feature")
data class Feature (
    override var name: String = "",
    override var description: String = "",
    var activationType: ActivationType? = null,
    val uses: Int? = null,
    val resets: ResetType? = null,
) : BaseFeature()

@Serializable
@SerialName("attack")
data class AttackAction (
    override var name: String = "",
    override var description: String = "",
    var activationType: ActivationType? = null,
    val toHit: Int,
    val attackType: AttackType,
    val range: String,
    val target: String,
    val rolls: List<Roll>
) : BaseFeature()

@Serializable
@SerialName("rollableFeature")
data class RollableFeature(
    override var name: String = "",
    override var description: String = "",
    var activationType: ActivationType? = null,
    val uses: Int? = null,
    val resets: ResetType? = null,
    val rolls: List<Roll>
) : BaseFeature()

sealed class SpellCastingFeature() : BaseFeature() {
    abstract var spells: List<SpellUses>
}

@Serializable
@SerialName("spellsPerDayFeature")
data class SpellsPerDayFeature (
    override var name: String = "",
    override var description: String = "",
    val resets: ResetType? = null,
    override var spells: List<SpellUses>
) : SpellCastingFeature()

@Serializable
@SerialName("spellSlotsFeature")
data class SpellSlotsFeature (
    override var name: String = "",
    override var description: String = "",
    val resets: ResetType? = null,
    override var spells: List<SpellUses>
) : SpellCastingFeature()

@Serializable
//data class Roll(val diceRoll: Dice, val type: DamageType?) {
data class Roll(val diceRoll: Dice,
                val type: DamageType,
                val condition: Condition? = null,
                val saveDc: Int? = null, val saveAbility: Int? = null) {
    companion object {
        val NO_DICE = Roll(Dice(0,0,0,0,""),
            DamageType.NONE)
    }
}

data class RollSave(val saveDc: Int, val saveAbility: Int, val condition: Condition?)
