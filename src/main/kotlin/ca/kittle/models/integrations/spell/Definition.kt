package ca.kittle.integrations.spells

import ca.kittle.models.integrations.Source
import ca.kittle.models.integrations.character.Conditions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Definition (

  @SerialName("id"                     ) var id                     : Int?                 = null,
  @SerialName("definitionKey"          ) var definitionKey          : String?              = null,
  @SerialName("name"                   ) var name                   : String?              = null,
  @SerialName("level"                  ) var level                  : Int?                 = null,
  @SerialName("school"                 ) var school                 : String?              = null,
  @SerialName("duration"               ) var duration               : Duration?            = Duration(),
  @SerialName("activation"             ) var activation             : Activation?          = Activation(),
  @SerialName("range"                  ) var range                  : Range?               = Range(),
  @SerialName("asPartOfWeaponAttack"   ) var asPartOfWeaponAttack   : Boolean?             = null,
  @SerialName("description"            ) var description            : String?              = null,
  @SerialName("snippet"                ) var snippet                : String?              = null,
  @SerialName("concentration"          ) var concentration          : Boolean?             = null,
  @SerialName("ritual"                 ) var ritual                 : Boolean?             = null,
  @SerialName("rangeArea"              ) var rangeArea              : String?              = null,
  @SerialName("damageEffect"           ) var damageEffect           : String?              = null,
  @SerialName("components"             ) var components             : ArrayList<Int>       = arrayListOf(),
  @SerialName("componentsDescription"  ) var componentsDescription  : String?              = null,
  @SerialName("saveDcAbilityId"        ) var saveDcAbilityId        : String?              = null,
  @SerialName("healing"                ) var healing                : String?              = null,
  @SerialName("healingDice"            ) var healingDice            : ArrayList<String>    = arrayListOf(),
  @SerialName("tempHpDice"             ) var tempHpDice             : ArrayList<String>    = arrayListOf(),
  @SerialName("attackType"             ) var attackType             : Int?                 = null,
  @SerialName("canCastAtHigherLevel"   ) var canCastAtHigherLevel   : Boolean?             = null,
  @SerialName("isHomebrew"             ) var isHomebrew             : Boolean?             = null,
  @SerialName("version"                ) var version                : String?              = null,
  @SerialName("sourceId"               ) var sourceId               : String?              = null,
  @SerialName("sourcePageNumber"       ) var sourcePageNumber       : String?              = null,
  @SerialName("requiresSavingThrow"    ) var requiresSavingThrow    : Boolean?             = null,
  @SerialName("requiresAttackRoll"     ) var requiresAttackRoll     : Boolean?             = null,
  @SerialName("atHigherLevels"         ) var atHigherLevels         : AtHigherLevels?      = AtHigherLevels(),
  @SerialName("modifiers"              ) var modifiers              : ArrayList<Modifiers> = arrayListOf(),
  @SerialName("conditions"             ) var conditions             : ArrayList<Conditions>?    = arrayListOf(),
  @SerialName("tags"                   ) var tags                   : ArrayList<String>    = arrayListOf(),
  @SerialName("castingTimeDescription" ) var castingTimeDescription : String?              = null,
  @SerialName("scaleType"              ) var scaleType              : String?              = null,
  @SerialName("sources"                ) var sources                : ArrayList<Source>   = arrayListOf(),
  @SerialName("spellGroups"            ) var spellGroups            : ArrayList<String>    = arrayListOf()

)
