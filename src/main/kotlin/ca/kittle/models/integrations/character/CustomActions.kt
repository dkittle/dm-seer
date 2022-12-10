package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomActions (

  @SerialName("id"                     ) var id                     : String?  = null,
  @SerialName("entityTypeId"           ) var entityTypeId           : String?  = null,
  @SerialName("name"                   ) var name                   : String?  = null,
  @SerialName("toHitBonus"             ) var toHitBonus             : String?  = null,
  @SerialName("description"            ) var description            : String?  = null,
  @SerialName("snippet"                ) var snippet                : String?  = null,
  @SerialName("isProficient"           ) var isProficient           : Boolean? = null,
  @SerialName("isOffhand"              ) var isOffhand              : Boolean? = null,
  @SerialName("statId"                 ) var statId                 : String?  = null,
  @SerialName("rangeId"                ) var rangeId                : String?  = null,
  @SerialName("diceCount"              ) var diceCount              : Int?     = null,
  @SerialName("diceType"               ) var diceType               : Int?     = null,
  @SerialName("fixedValue"             ) var fixedValue             : String?  = null,
  @SerialName("damageTypeId"           ) var damageTypeId           : String?  = null,
  @SerialName("onMissDescription"      ) var onMissDescription      : String?  = null,
  @SerialName("saveFailDescription"    ) var saveFailDescription    : String?  = null,
  @SerialName("saveSuccessDescription" ) var saveSuccessDescription : String?  = null,
  @SerialName("saveStatId"             ) var saveStatId             : String?  = null,
  @SerialName("fixedSaveDc"            ) var fixedSaveDc            : String?  = null,
  @SerialName("actionType"             ) var actionType             : Int?     = null,
  @SerialName("attackSubtype"          ) var attackSubtype          : String?  = null,
  @SerialName("range"                  ) var range                  : String?  = null,
  @SerialName("longRange"              ) var longRange              : String?  = null,
  @SerialName("aoeType"                ) var aoeType                : String?  = null,
  @SerialName("aoeSize"                ) var aoeSize                : String?  = null,
  @SerialName("activationTime"         ) var activationTime         : String?  = null,
  @SerialName("activationType"         ) var activationType         : String?  = null,
  @SerialName("isSilvered"             ) var isSilvered             : Boolean? = null,
  @SerialName("damageBonus"            ) var damageBonus            : String?  = null,
  @SerialName("isMartialArts"          ) var isMartialArts          : Boolean? = null,
  @SerialName("spellRangeType"         ) var spellRangeType         : String?  = null,
  @SerialName("displayAsAttack"        ) var displayAsAttack        : Boolean? = null

)
