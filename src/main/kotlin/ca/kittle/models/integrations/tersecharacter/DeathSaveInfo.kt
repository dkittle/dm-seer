package ca.kittle.models.integrations.tersecharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeathSaveInfo (

  @SerialName("failCount"    ) var failCount    : Int? = null,
  @SerialName("successCount" ) var successCount : Int? = null

)
