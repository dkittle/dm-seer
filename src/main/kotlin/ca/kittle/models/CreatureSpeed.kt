package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class CreatureSpeed(val type: Movement, val speed: Int, val notes: String)
