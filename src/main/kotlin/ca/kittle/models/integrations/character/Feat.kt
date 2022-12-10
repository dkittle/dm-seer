package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class Feat(
    var componentTypeId: Long?,
    var componentId: Long?,
    var definitionId: Long?,
    var definition: Definition?
)
