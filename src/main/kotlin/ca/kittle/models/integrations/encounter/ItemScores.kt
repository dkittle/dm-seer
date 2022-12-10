package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ItemScores (

  @SerialName("id"    ) var id    : String? = null,
  @SerialName("score" ) var score : Double? = null

)
