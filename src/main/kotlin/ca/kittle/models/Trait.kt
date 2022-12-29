package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseTrait {
    abstract var name: String
    abstract var description: String
    abstract var resets: ResetType?
}

@Serializable
@SerialName("trait")
data class Trait (
    override var name: String = "",
    override var description: String = "",
    override var resets: ResetType? = null,
    var activationType: ActivationType? = null,
    val uses: Int? = null,
) : BaseTrait()

@Serializable
@SerialName("rollableTrait")
data class RollableTrait (
    override var name: String = "",
    override var description: String = "",
    override var resets: ResetType? = null,
    var activationType: ActivationType? = null,
    var uses: Int? = null,
    val rolls: List<Roll>
) : BaseTrait()

sealed class SpellCastingTrait() : BaseTrait() {
    abstract var spells: List<SpellUses>
}

@Serializable
@SerialName("spellsPerDayTrait")
data class SpellsPerDayTrait (
    override var name: String = "",
    override var description: String = "",
    override var resets: ResetType? = null,
    override var spells: List<SpellUses>
) : SpellCastingTrait()

@Serializable
@SerialName("spellSlotsTrait")
data class SpellSlotsTrait (
    override var name: String = "",
    override var description: String = "",
    override var resets: ResetType? = null,
    override var spells: List<SpellUses>
) : SpellCastingTrait()

