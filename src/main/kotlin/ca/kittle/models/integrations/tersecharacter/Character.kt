package ca.kittle.models.integrations.tersecharacter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Character (

  @SerialName("senses"               ) var senses               : ArrayList<Sense>            = arrayListOf(),
  @SerialName("proficiencyGroups"    ) var proficiencyGroups    : ArrayList<ProficiencyGroup> = arrayListOf(),
  @SerialName("classes"              ) var classes              : ArrayList<CharClass>           = arrayListOf(),
  @SerialName("proficiencyBonus"     ) var proficiencyBonus     : Int?                         = null,
  @SerialName("resistances"          ) var resistances          : ArrayList<Resistance>       = arrayListOf(),
  @SerialName("decorations"          ) var decorations          : Decorations?                 = Decorations(),
  @SerialName("inspiration"          ) var inspiration          : Boolean?                     = null,
  @SerialName("readOnlyUrl"          ) var readOnlyUrl          : String?                      = null,
  @SerialName("abilities"            ) var abilities            : ArrayList<Ability>         = arrayListOf(),
  @SerialName("skills"               ) var skills               : ArrayList<Skill>            = arrayListOf(),
  @SerialName("attunedItems"         ) var attunedItems         : ArrayList<AttunedItem>      = arrayListOf(),
  @SerialName("deathSaveInfo"        ) var deathSaveInfo        : DeathSaveInfo?               = DeathSaveInfo(),
  @SerialName("armorClass"           ) var armorClass           : Int?                         = null,
  @SerialName("attacks"              ) var attacks              : ArrayList<Attack>           = arrayListOf(),
  @SerialName("speeds"               ) var speeds               : ArrayList<Speed>            = arrayListOf(),
  @SerialName("characterId"          ) var characterId          : Int?                         = null,
  @SerialName("passiveInvestigation" ) var passiveInvestigation : Int?                         = null,
  @SerialName("isAssignedToPlayer"   ) var isAssignedToPlayer   : Boolean?                     = null,
  @SerialName("currentXp"            ) var currentXp            : Int?                         = null,
  @SerialName("immunities"           ) var immunities           : ArrayList<Immunity>        = arrayListOf(),
  @SerialName("race"                 ) var species                 : Species?                        = Species(),
  @SerialName("level"                ) var level                : Int?                         = null,
  @SerialName("hitPointInfo"         ) var hitPointInfo         : HitPointInfo?                = HitPointInfo(),
  @SerialName("initiativeBonus"      ) var initiativeBonus      : Int?                         = null,
  @SerialName("passiveInsight"       ) var passiveInsight       : Int?                         = null,
  @SerialName("castingInfo"          ) var castingInfo          : CastingInfo?                 = CastingInfo(),
  @SerialName("userId"               ) var userId               : Int?                         = null,
  @SerialName("passivePerception"    ) var passivePerception    : Int?                         = null,
  @SerialName("name"                 ) var name                 : String?                      = null,
  @SerialName("campaign"             ) var campaign             : Campaign?                    = Campaign(),
  @SerialName("vulnerabilities"      ) var vulnerabilities      : ArrayList<String>            = arrayListOf(),
  @SerialName("conditions"           ) var conditions           : ArrayList<String>            = arrayListOf()

)
