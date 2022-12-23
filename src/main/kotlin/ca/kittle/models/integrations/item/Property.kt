package ca.kittle.models.integrations.item

import kotlinx.serialization.Serializable

@Serializable
data class Property(val id: Int, val name: String, val description: String, val notes: String?)
