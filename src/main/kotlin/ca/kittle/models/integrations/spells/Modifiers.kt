package ca.kittle.integrations.spells

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Modifiers (

  @SerialName("fixedValue"            ) var fixedValue            : String?           = null,
  @SerialName("id"                    ) var id                    : String?           = null,
  @SerialName("entityId"              ) var entityId              : String?           = null,
  @SerialName("entityTypeId"          ) var entityTypeId          : String?           = null,
  @SerialName("type"                  ) var type                  : String?           = null,
  @SerialName("subType"               ) var subType               : String?           = null,
  @SerialName("dice"                  ) var dice                  : String?           = null,
  @SerialName("restriction"           ) var restriction           : String?           = null,
  @SerialName("statId"                ) var statId                : String?           = null,
  @SerialName("requiresAttunement"    ) var requiresAttunement    : Boolean?          = null,
  @SerialName("duration"              ) var duration              : String?           = null,
  @SerialName("friendlyTypeName"      ) var friendlyTypeName      : String?           = null,
  @SerialName("friendlySubtypeName"   ) var friendlySubtypeName   : String?           = null,
  @SerialName("isGranted"             ) var isGranted             : Boolean?          = null,
  @SerialName("bonusTypes"            ) var bonusTypes            : ArrayList<String> = arrayListOf(),
  @SerialName("value"                 ) var value                 : String?           = null,
  @SerialName("availableToMulticlass" ) var availableToMulticlass : String?           = null,
  @SerialName("modifierTypeId"        ) var modifierTypeId        : Int?              = null,
  @SerialName("modifierSubTypeId"     ) var modifierSubTypeId     : Int?              = null,
  @SerialName("componentId"           ) var componentId           : Int?              = null,
  @SerialName("componentTypeId"       ) var componentTypeId       : Int?              = null,
  @SerialName("die"                   ) var die                   : Die?              = Die(),
  @SerialName("count"                 ) var count                 : Int?              = null,
  @SerialName("durationUnit"          ) var durationUnit          : String?           = null,
  @SerialName("usePrimaryStat"        ) var usePrimaryStat        : Boolean?          = null,
  @SerialName("atHigherLevels"        ) var atHigherLevels        : AtHigherLevels?   = AtHigherLevels()

)
