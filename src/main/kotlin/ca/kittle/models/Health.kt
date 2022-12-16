package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Health(
    var hitPoints: Int = 0,
    var maximumHitPoints: Int = 0,
    var temporaryHitPoints: Int = 0,
    var temporaryMaximum: Int = 0
)
