package ca.kittle.models

import kotlinx.serialization.Serializable

@Serializable
data class Campaign(
    val id: Long,
    val name: String,
    val description: String,
    val publicNotes: String,
    val privateNotes: String,
    val official: Boolean)
