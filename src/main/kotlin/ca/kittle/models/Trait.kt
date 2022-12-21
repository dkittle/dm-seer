package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseTrait {
    abstract var name: String
    abstract var description: String
}

@Serializable
@SerialName("trait")
data class Trait (
    override var name: String = "",
    override var description: String = ""
) : BaseTrait()

@Serializable
@SerialName("rollableTrait")
data class RollableTrait (
    override var name: String = "",
    override var description: String = "",
    val rolls: List<Roll>
) : BaseTrait()

@Serializable
@SerialName("spellCastingTrait")
data class SpellCastingTrait (
    override var name: String = "",
    override var description: String = "",
    val spells: List<BaseCreatureSpells>
) : BaseTrait()

