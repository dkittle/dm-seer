package ca.kittle.models

class Conditions {
    companion object {
        fun getConditionById(id: Int): Condition {
            return when (id) {
                1 -> Condition.BLINDED
                2 -> Condition.CHARMED
                3 -> Condition.DEAFENED
                4 -> Condition.EXHAUSTION
                5 -> Condition.FRIGHTENED
                6 -> Condition.GRAPPLED
                9 -> Condition.PARALYZED
                10 -> Condition.PETRIFIED
                11 -> Condition.POISON
                12 -> Condition.PRONE
                13 -> Condition.RESTRAINED
                14 -> Condition.STUNNED
                15 -> Condition.UNCONSCIOUS
                else -> Condition.NONE
            }
        }
    }
}


enum class Condition{
    BLINDED,
    CHARMED,
    DEAFENED,
    EXHAUSTION,
    FRIGHTENED,
    GRAPPLED,
    PARALYZED,
    PETRIFIED,
    POISON,
    PRONE,
    RESTRAINED,
    STUNNED,
    UNCONSCIOUS,
    NONE
}
