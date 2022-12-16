package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Movement(
    var movementId: Long?,
    var speed: Int?,
    var notes: String?
)


enum class Movements(val id: Int) {
    WALK(1),
    BURROW(2),
    CLIMB(3),
    FLY(4),
    SWIM(5)
}
