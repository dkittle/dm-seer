package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class CreatureRule(
    var creatureGroupId: Long?,
    var monsterTypeId: Long?,
    var maxChallengeRatingId: Long?,
    var levelDivisor: Int?,
    var monsterIds: List<Long>? = null,
    var movementIds: List<Long>? = null,
    var sizeIds: List<Long>? = null
)

