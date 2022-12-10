package ca.kittle.models.integrations.encounter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MetaData (

  @SerialName("maxScore"   ) var maxScore   : Float?                  = null,
  @SerialName("itemScores" ) var itemScores : ArrayList<ItemScores>

)
