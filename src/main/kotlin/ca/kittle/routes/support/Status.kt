package ca.kittle.routes.support

import kotlinx.serialization.Serializable

val OK: Status = Status("OK", "all okay")
val TRUE: Status = Status("OK", "true")
val FALSE: Status = Status("OK", "false")
val ERROR: Status = Status("KO", "error")

@Serializable
data class Status (val status: String, val message: String)

