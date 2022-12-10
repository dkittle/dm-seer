package ca.kittle.models.integrations.encounter

import kotlinx.serialization.Serializable

@Serializable
data class Editable(val code: String, val editable: Boolean)
