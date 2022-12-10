package ca.kittle.models.integrations.character

import kotlinx.serialization.Serializable

@Serializable
data class SpellDefinition(
    val id: Long?,
    val definitionKey: String?,
    val name: String?,
    val level: Int?,
    val school: String?,
    val duration: Duration?
)
