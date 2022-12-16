package ca.kittle.routes.support

import kotlinx.serialization.Serializable

val OK: Status = Status("OK", "all okay")
val ERROR: Status = Status("KO", "error")

@Serializable
data class Status (val status: String, val message: String)

