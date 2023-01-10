package ca.kittle.models.spell

import ca.kittle.models.Dice
import ca.kittle.models.Duration
import kotlinx.serialization.Serializable

@Serializable
data class SpellModifier (

  var fixedValue            : Int?           = null,
  var id                    : String,
  var entityId              : Int?           = null,
  var entityTypeId          : Int?           = null,
  var type                  : String,
  var subType               : String,
  var dice                  : Dice?,
  var restriction           : String?           = null,
  var statId                : Int?           = null,
  var requiresAttunement    : Boolean,
  var duration              : Duration?           = null,
  var friendlyTypeName      : String,
  var friendlySubtypeName   : String,
  var isGranted             : Boolean,
  var value                 : Int?           = null,
  var modifierTypeId        : Int,
  var modifierSubTypeId     : Int,
  var componentId           : Int,
  var componentTypeId       : Int,
  var die                   : Dice?,
  var count                 : Int,
  var durationUnit          : String?           = null,
  var usePrimaryStat        : Boolean,
  var atHigherLevels        : AtHigherLevelsList

)
