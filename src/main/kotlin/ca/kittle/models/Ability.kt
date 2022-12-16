package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Abilities (
    val abilities: List<Ability>
) {
    companion object {
        fun modifiers(): IntArray {
            return IntArray(30) {
                when (it) {
                    0 -> -5
                    in 1..28 -> (it + 1) / 2 - 5
                    29 -> 10
                    else -> 0
                }
            }
        }
    }
}

@Serializable
data class Ability (
    val id: Int,
    val name: String,
    val value: Int,
    var modifier: Int
) {
    init {
        if (value >= 1 && value <= 30)
            modifier = Abilities.modifiers().get(value - 1)
    }
}
