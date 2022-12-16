package ca.kittle.models.integrations.character

import ca.kittle.models.Dice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Species (

    @SerialName("fixedValue"            ) var fixedValue            : String?        = null,
    @SerialName("id"                    ) var id                    : String?        = null,
    @SerialName("entityId"              ) var entityId              : String?        = null,
    @SerialName("entityTypeId"          ) var entityTypeId          : String?        = null,
    @SerialName("type"                  ) var type                  : String?        = null,
    @SerialName("subType"               ) var subType               : String?        = null,
    @SerialName("dice"                  ) var dice                  : Dice?        = null,
    @SerialName("restriction"           ) var restriction           : String?        = null,
    @SerialName("statId"                ) var statId                : String?        = null,
    @SerialName("requiresAttunement"    ) var requiresAttunement    : Boolean?       = null,
    @SerialName("duration"              ) var duration              : String?        = null,
    @SerialName("friendlyTypeName"      ) var friendlyTypeName      : String?        = null,
    @SerialName("friendlySubtypeName"   ) var friendlySubtypeName   : String?        = null,
    @SerialName("isGranted"             ) var isGranted             : Boolean?       = null,
//  @SerialName("bonusTypes"            ) var bonusTypes            : List<Int>,
    @SerialName("value"                 ) var value                 : String?        = null,
    @SerialName("availableToMulticlass" ) var availableToMulticlass : Boolean?       = null,
    @SerialName("modifierTypeId"        ) var modifierTypeId        : Int?           = null,
    @SerialName("modifierSubTypeId"     ) var modifierSubTypeId     : Int?           = null,
    @SerialName("componentId"           ) var componentId           : Int?           = null,
    @SerialName("componentTypeId"       ) var componentTypeId       : Int?           = null

)
