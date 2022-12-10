package ca.kittle.models

import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class Campaign(val id: Long, val name: String, val description: String, val publicNotes: String, val privateNotes: String, val official: Boolean)
