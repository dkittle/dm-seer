package ca.kittle.routes

import kotlinx.serialization.Serializable

@Serializable
data class AccountAvailableResponse(val isAvailable: Boolean, val username: String)
