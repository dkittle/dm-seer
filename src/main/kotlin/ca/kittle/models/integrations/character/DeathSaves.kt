package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeathSaves (

  @SerialName("failCount"    ) var failCount    : String?  = null,
  @SerialName("successCount" ) var successCount : String?  = null,
  @SerialName("isStabilized" ) var isStabilized : Boolean? = null

)
