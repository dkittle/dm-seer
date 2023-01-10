package ca.kittle.models.spell

import ca.kittle.models.Dice
import kotlinx.serialization.Serializable

@Serializable
data class HigherLevelDefinitions (

  var level   : Int?    = null,
  var typeId  : Int,
  var dice    : Dice?,
  var value   : String? = null,
  var details : String

)
