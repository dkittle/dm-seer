package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class Movement(
    var movementId: Long?,
    var speed: Int?,
    var notes: String?
)
