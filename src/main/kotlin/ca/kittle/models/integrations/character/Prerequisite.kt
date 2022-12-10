package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class Prerequisite(
    var description: String?,
    var prerequisiteMappings: List<PrerequisiteMapping>? = null
)

