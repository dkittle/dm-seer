package ca.kittle.models.integrations.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SubclassDefinition (

  @SerialName("id"                    ) var id                    : Int?                     = null,
  @SerialName("definitionKey"         ) var definitionKey         : String?                  = null,
  @SerialName("name"                  ) var name                  : String?                  = null,
  @SerialName("description"           ) var description           : String?                  = null,
  @SerialName("equipmentDescription"  ) var equipmentDescription  : String?                  = null,
  @SerialName("parentClassId"         ) var parentClassId         : Int?                     = null,
  @SerialName("avatarUrl"             ) var avatarUrl             : String?                  = null,
  @SerialName("largeAvatarUrl"        ) var largeAvatarUrl        : String?                  = null,
  @SerialName("portraitAvatarUrl"     ) var portraitAvatarUrl     : String?                  = null,
  @SerialName("moreDetailsUrl"        ) var moreDetailsUrl        : String?                  = null,
  @SerialName("spellCastingAbilityId" ) var spellCastingAbilityId : Int?                     = null,
  @SerialName("sources"               ) var sources               : ArrayList<ca.kittle.models.integrations.character.Sources>,
  @SerialName("classFeatures"         ) var classFeatures         : ArrayList<ca.kittle.models.integrations.character.ClassFeatures>,
  @SerialName("hitDice"               ) var hitDice               : Int?                     = null,
  @SerialName("wealthDice"            ) var wealthDice            : String?                  = null,
  @SerialName("canCastSpells"         ) var canCastSpells         : Boolean?                 = null,
  @SerialName("knowsAllSpells"        ) var knowsAllSpells        : String?                  = null,
  @SerialName("spellPrepareType"      ) var spellPrepareType      : String?                  = null,
  @SerialName("spellContainerName"    ) var spellContainerName    : String?                  = null,
  @SerialName("sourcePageNumber"      ) var sourcePageNumber      : Int?                     = null,
  @SerialName("subclassDefinition"    ) var subclassDefinition    : String?                  = null,
  @SerialName("isHomebrew"            ) var isHomebrew            : Boolean?                 = null,
  @SerialName("primaryAbilities"      ) var primaryAbilities      : String?                  = null,
  @SerialName("spellRules"            ) var spellRules            : String?                  = null,
  @SerialName("prerequisites"         ) var prerequisites         : String?                  = null

)
