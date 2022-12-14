package ca.kittle.models.integration.item

import ca.kittle.models.Dice
import ca.kittle.models.Duration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GrantedModifier (

  @SerialName("fixedValue"            ) var fixedValue            : Int?              = null,
  @SerialName("id"                    ) var id                    : String?           = null,
  @SerialName("entityId"              ) var entityId              : Int?              = null,
  @SerialName("entityTypeId"          ) var entityTypeId          : Int?              = null,
  @SerialName("type"                  ) var type                  : String?           = null,
  @SerialName("subType"               ) var subType               : String?           = null,
  @SerialName("dice"                  ) var dice                  : Dice?           = null,
  @SerialName("restriction"           ) var restriction           : String?           = null,
  @SerialName("statId"                ) var statId                : String?           = null,
  @SerialName("requiresAttunement"    ) var requiresAttunement    : Boolean?          = null,
  @SerialName("duration"              ) var duration              : Duration?           = null,
  @SerialName("friendlyTypeName"      ) var friendlyTypeName      : String?           = null,
  @SerialName("friendlySubtypeName"   ) var friendlySubtypeName   : String?           = null,
  @SerialName("isGranted"             ) var isGranted             : Boolean?          = null,
  @SerialName("bonusTypes"            ) var bonusTypes            : ArrayList<String> = arrayListOf(),
  @SerialName("value"                 ) var value                 : Int?              = null,
  @SerialName("availableToMulticlass" ) var availableToMulticlass : Boolean?          = null,
  @SerialName("modifierTypeId"        ) var modifierTypeId        : Int?              = null,
  @SerialName("modifierSubTypeId"     ) var modifierSubTypeId     : Int?              = null,
  @SerialName("componentId"           ) var componentId           : Int?              = null,
  @SerialName("componentTypeId"       ) var componentTypeId       : Int?              = null

)
