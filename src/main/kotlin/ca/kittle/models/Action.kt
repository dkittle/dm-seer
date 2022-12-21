package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseAction {
    abstract var name: String
    abstract var description: String
}

@Serializable
@SerialName("action")
data class Action (
    override var name: String = "",
    override var description: String = ""
) : BaseAction()

@Serializable
@SerialName("attack")
data class AttackAction (
    override var name: String = "",
    override var description: String = "",
    val toHit: Int,
    val attackType: AttackType,
    val range: String,
    val target: String,
    val rolls: List<Roll>
) : BaseAction()

@Serializable
@SerialName("rollableAction")
data class RollableAction (
    override var name: String = "",
    override var description: String = "",
    val rolls: List<Roll>
) : BaseAction()

@Serializable
@SerialName("spellCastingAction")
data class SpellCastingAction (
    override var name: String = "",
    override var description: String = "",
    val spells: List<BaseCreatureSpells>
) : BaseAction()

@Serializable
//data class Roll(val diceRoll: Dice, val type: DamageType?) {
data class Roll(val diceRoll: Dice, val type: String?) {
    companion object {
        val NO_DICE = Roll(Dice(0,0,0,0,""), null)
    }
}

