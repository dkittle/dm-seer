package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class CreatureSkill(val skillId: String, val modifier: Int)
