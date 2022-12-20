package ca.kittle.models.integrations

import ca.kittle.models.integrations.ItemScores
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MetaData (

  @SerialName("maxScore"   ) var maxScore   : Float?                  = null,
  @SerialName("itemScores" ) var itemScores : ArrayList<ItemScores>

)
