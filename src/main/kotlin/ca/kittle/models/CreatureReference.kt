package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class CreatureReference(
    val id: Long,
    val sourceId: Int,
    val sourcePage: Int,
    val externalUrl: String
)
