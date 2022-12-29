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
        fun getAbilityById(id: Int): AbilityId? {
            for (ability in AbilityId.values()) {
                if (ability.id == id)
                    return ability
            }
            return null
        }

    }
}

@Serializable
data class Ability (
    val ability: AbilityId,
    val value: Int,
    var modifier: Int = 0
) {
    init {
        if (value >= 1 && value <= 30)
            modifier = Abilities.modifiers().get(value - 1)
    }
}

enum class AbilityId(val id: Int) {
    STRENGTH(1),
    DEXTERITY(2),
    CONSTITUTION(3),
    INTELLIGENCE(4),
    WISDOM(5),
    CHARISMA(6)
}
