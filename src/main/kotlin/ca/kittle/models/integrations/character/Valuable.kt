package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class Valuable(
    val typeId: Long?,
    val value: String?,
    val notes: String?,
    val valueId: String?,
    val valueTypeId: String?,
    val contextId: String?,
    val contextTypeId: String?
)

