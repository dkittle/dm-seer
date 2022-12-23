package ca.kittle.integrations.spells

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Spell (

  @SerialName("overrideSaveDc"        ) var overrideSaveDc        : String?     = null,
  @SerialName("limitedUse"            ) var limitedUse            : String?     = null,
  @SerialName("id"                    ) var id                    : Int?        = null,
  @SerialName("entityTypeId"          ) var entityTypeId          : Int?        = null,
  @SerialName("definition"            ) var definition            : Definition? = Definition(),
  @SerialName("definitionId"          ) var definitionId          : Int?        = null,
  @SerialName("prepared"              ) var prepared              : Boolean?    = null,
  @SerialName("countsAsKnownSpell"    ) var countsAsKnownSpell    : Boolean?    = null,
  @SerialName("usesSpellSlot"         ) var usesSpellSlot         : Boolean?    = null,
  @SerialName("castAtLevel"           ) var castAtLevel           : String?     = null,
  @SerialName("alwaysPrepared"        ) var alwaysPrepared        : Boolean?    = null,
  @SerialName("restriction"           ) var restriction           : String?     = null,
  @SerialName("spellCastingAbilityId" ) var spellCastingAbilityId : String?     = null,
  @SerialName("displayAsAttack"       ) var displayAsAttack       : String?     = null,
  @SerialName("additionalDescription" ) var additionalDescription : String?     = null,
  @SerialName("castOnlyAsRitual"      ) var castOnlyAsRitual      : Boolean?    = null,
  @SerialName("ritualCastingType"     ) var ritualCastingType     : String?     = null,
  @SerialName("range"                 ) var range                 : Range?      = Range(),
  @SerialName("activation"            ) var activation            : Activation? = Activation(),
  @SerialName("baseLevelAtWill"       ) var baseLevelAtWill       : Boolean?    = null,
  @SerialName("atWillLimitedUseLevel" ) var atWillLimitedUseLevel : String?     = null,
  @SerialName("isSignatureSpell"      ) var isSignatureSpell      : String?     = null,
  @SerialName("componentId"           ) var componentId           : Int?        = null,
  @SerialName("componentTypeId"       ) var componentTypeId       : Int?        = null,
  @SerialName("spellListId"           ) var spellListId           : String?     = null

)
