package ca.kittle.models.integrations.spells

import kotlinx.serialization.Serializable

@Serializable
data class Condition(val type: Int, val conditionId: Int, val conditionDuration: Int, val durationUnit: String, val exception: String)
