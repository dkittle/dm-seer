package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class KnownSkill(
    var skillId: Long?,
    var value: Int?,
    var additionalBonus: Int?
)
