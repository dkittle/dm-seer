package ca.kittle.routes.support

import kotlinx.serialization.Serializable

@Serializable
data class AccountUsername(val username: String)

@Serializable
data class AccountUsernameAvailable(val available: Boolean, val username: String)
