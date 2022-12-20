package ca.kittle.models.integrations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats (

  @SerialName("elapsedMilliseconds" ) var elapsedMilliseconds : Int? = null

)
