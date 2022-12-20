package ca.kittle.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BasicTrait {
    abstract var name: String
    abstract var description: String
}

@Serializable
@SerialName("trait")
data class Trait (
    override var name: String = "",
    override var description: String = ""
) : BasicTrait()

@Serializable
@SerialName("spell-casting")
data class SpellCastingTrait (
    override var name: String = "",
    override var description: String = "",
    var atWill: List<String>,
    var threeTimesPerDay: List<String>,
    var oncePerDay: List<String>
) : BasicTrait()

