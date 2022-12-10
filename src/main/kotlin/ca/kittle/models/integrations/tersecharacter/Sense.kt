package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.Serializable

@Serializable
data class Sense(
    val name: String?,
    val distance: String?
)
