package ca.kittle.models.spell

import ca.kittle.models.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class DdbSpells (
  @SerialName("id"         ) var id         : Int?            = null,
  @SerialName("success"    ) var success    : Boolean?        = null,
  @SerialName("message"    ) var message    : String?         = null,
  @SerialName("data"       ) var spells       : ArrayList<Spell> = arrayListOf(),
  @SerialName("pagination" ) var pagination : String?         = null

)

@Serializable
data class Spell (
  var overrideSaveDc        : String?     = null,
  var limitedUse            : String?     = null,
  var id                    : Int?        = null,
  var entityTypeId          : Int?        = null,
  var definition            : Definition,
  var prepared              : Boolean?    = null,
  var countsAsKnownSpell    : Boolean?    = null,
  var usesSpellSlot         : Boolean?    = null,
  var castAtLevel           : String?     = null,
  var alwaysPrepared        : Boolean?    = null,
  var restriction           : String?     = null,
  var spellCastingAbilityId : String?     = null,
  var displayAsAttack       : String?     = null,
  var additionalDescription : String?     = null,
  var castOnlyAsRitual      : Boolean?    = null,
  var ritualCastingType     : String?     = null,
  var range                 : Range?,
  var activation            : Activation?,
)

@Serializable
data class Definition (
  var id                     : Int,
  var name                   : String,
  var level                  : Int,
  var school                 : String,
  var duration               : Duration,
  var activation             : Activation,
  var range                  : Range,
  var asPartOfWeaponAttack   : Boolean,
  var description            : String?              = null,
  var concentration          : Boolean,
  var ritual                 : Boolean,
  var components             : List<Int>       = listOf(),
  var componentsDescription  : String,
  var saveDcAbilityId        : Int?              = null,
  var attackType             : Int?                 = null,
  var canCastAtHigherLevel   : Boolean,
  var isHomebrew             : Boolean,
  var requiresSavingThrow    : Boolean,
  var requiresAttackRoll     : Boolean,
  var atHigherLevels         : AtHigherLevelsList?,
  var modifiers              : List<SpellModifier> = listOf(),
  var conditions             : List<SpellCondition>    = listOf(),
  var tags                   : List<String>    = listOf(),
  var castingTimeDescription : String?              = null,
  var scaleType              : String?              = null,
  var sources                : List<SpellSource>   = listOf(),
  var spellGroups            : List<Int>    = listOf()
)

@Serializable
data class SpellCondition(
  val type: Int,
  val conditionId: Int,
  val durationUnit: String?,
  val exception: String
)

object Spells : IntIdTable("spells") {
  val name = text("name")
  val level = integer("level")
  val school = text("school")
  val durationInterval = integer("duration_interval")
  val durationUnit = text("duration_unit").nullable()
  val durationType = text("duration_type").nullable()
  val activationTime = integer("activation_time")
  val activationType = integer("activation_type")
  val origin = text("origin")
  val rangeValue = integer("range_value").nullable()
  val aoeType = text("aoe_type").nullable()
  val aoeValue = integer("aoe_value").nullable()
  val partOfWeaponAttack = bool("part_of_weapon_attack")
  val description = text("description")
  val concentration = bool("concentration")
  val ritual = bool("ritual")
  val vComponent = bool("v_component")
  val sComponent = bool("s_component")
  val mComponent = bool("m_component")
  val componentsDescription = text("components_description")
  val saveDcAbilityId = integer("save_dc_ability_id").nullable()
  val attackType = integer("attack_type").nullable()
  val spellGroup = integer("spell_group").nullable()
  val canCastAtHigherLevel = bool("can_cast_at_higher_level")
  val isHomebrew = bool("is_homebrew")
  val requiresSavingThrow = bool("requires_saving_throw")
  val requiresAttackRoll = bool("requires_attack_roll")
  val castingTimeDescription = text("casting_time_description").nullable()
  val scaleType = text("scale_type").nullable()
  val public = bool("public")
  val srd = bool("srd")
  val accountId = reference("account_id", Accounts)
}

class SpellDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<SpellDO>(Spells)
  var name by Spells.name
  var level by Spells.level
  var school by Spells.school
  var durationInterval by Spells.durationInterval
  var durationUnit by Spells.durationUnit
  var durationType by Spells.durationType
  var activationTime by Spells.activationTime
  var activationType by Spells.activationType
  var origin by Spells.origin
  var rangeValue by Spells.rangeValue
  var aoeType by Spells.aoeType
  var aoeValue by Spells.aoeValue
  var partOfWeaponAttack by Spells.partOfWeaponAttack
  var description by Spells.description
  var concentration by Spells.concentration
  var ritual by Spells.ritual
  var vComponent by Spells.vComponent
  var sComponent by Spells.sComponent
  var mComponent by Spells.mComponent
  var componentsDescription by Spells.componentsDescription
  var saveDcAbilityId by Spells.saveDcAbilityId
  var attackType by Spells.attackType
  var spellGroup by Spells.spellGroup
  var canCastAtHigherLevel by Spells.canCastAtHigherLevel
  var isHomebrew by Spells.isHomebrew
  var requiresSavingThrow by Spells.requiresSavingThrow
  var requiresAttackRoll by Spells.requiresAttackRoll
  var castingTimeDescription by Spells.castingTimeDescription
  var scaleType by Spells.scaleType
  var public by Spells.public
  var srd by Spells.srd
  var accountId = AccountDO referencedOn Spells.accountId
}

object KnownSpells : IntIdTable("known_spells") {
  val overrideSaveDc = text("overrideSaveDc")
  val limitedUse = text("limitedUse")
  val prepared = bool("prepared")
  val countsAsKnownSpell = bool("countsAsKnownSpell")
  val usesSpellSlot = bool("usesSpellSlot")
  val castAtLevel = text("castAtLevel")
  val alwaysPrepared = bool("alwaysPrepared")
  val restriction = text("restriction")
  val spellCastingAbilityId = text("spellCastingAbilityId")
  val displayAsAttack = text("displayAsAttack")
  val additionalDescription = text("additionalDescription")
  val castOnlyAsRitual = bool("castOnlyAsRitual")
  val ritualCastingType = text("ritualCastingType")
  val activationTime = integer("activation_time")
  val activationType = integer("activation_type")
  val origin = text("origin")
  val rangeValue = integer("range_value").nullable()
  val aoeType = text("aoe_type").nullable()
  val aoeValue = integer("aoe_value").nullable()
  val spellId = reference("spell_id", Spells)
}

class KnownSpellDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<KnownSpellDO>(KnownSpells)
  var overrideSaveDc by KnownSpells.overrideSaveDc
  var limitedUse by KnownSpells.limitedUse
  var prepared by KnownSpells.prepared
  var countsAsKnownSpell by KnownSpells.countsAsKnownSpell
  var usesSpellSlot by KnownSpells.usesSpellSlot
  var castAtLevel by KnownSpells.castAtLevel
  var alwaysPrepared by KnownSpells.alwaysPrepared
  var restriction by KnownSpells.restriction
  var spellCastingAbilityId by KnownSpells.spellCastingAbilityId
  var displayAsAttack by KnownSpells.displayAsAttack
  var additionalDescription by KnownSpells.additionalDescription
  var castOnlyAsRitual by KnownSpells.castOnlyAsRitual
  var ritualCastingType by KnownSpells.ritualCastingType
  var activationTime by KnownSpells.activationTime
  var activationType by KnownSpells.activationType
  var origin by KnownSpells.origin
  var rangeValue by KnownSpells.rangeValue
  var aoeType by KnownSpells.aoeType
  var aoeValue by KnownSpells.aoeValue
  var spellId = SpellDO referencedOn KnownSpells.spellId
}

object AtHigherLevels : IntIdTable("at_higher_levels") {
  val atLevel = integer("at_level").nullable()
  val typeId = integer("type_id")
  val dice = text("dice").nullable()
  val value = text("value").nullable()
  val details = text("details")
  val spellModifierId = reference("spell_modifier_id", SpellModifiers)
}

class AtHigherLevelDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<AtHigherLevelDO>(AtHigherLevels)
  var atLevel by AtHigherLevels.atLevel
  var typeId by AtHigherLevels.typeId
  var dice by AtHigherLevels.dice
  var value by AtHigherLevels.value
  var details by AtHigherLevels.details
  var spellmodifierId = SpellModifierDO referencedOn AtHigherLevels.spellModifierId
}

object SpellModifiers : IntIdTable("spell_modifiers") {
  val fixedValue = integer("fixed_value").nullable()
  val entityId = integer("entity_id").nullable()
  val entityTypeId = integer("entity_type_id").nullable()
  val type = text("type")
  val subType = text("sub_type")
  val dice = text("dice").nullable()
  val restriction = text("restriction").nullable()
  val statId = integer("stat_id").nullable()
  val requiresAttunement = bool("requires_attunement")
  val durationInterval = integer("duration_interval").nullable()
  val durationUnit = text("duration_unit").nullable()
  val friendlyTypeName = text("friendly_type_name")
  val friendlySubtypeName = text("friendly_subtype_name")
  val isGranted = bool("is_granted")
  val value = integer("value").nullable()
  val modifierTypeId = integer("modifier_type_id")
  val modifierSubTypeId = integer("modifier_sub_type_id")
  val componentId = integer("component_id")
  val componentTypeId = integer("component_type_id")
  val die = text("die").nullable()
  val count = integer("count")
  val usePrimaryStat = bool("use_primary_stat")
  val spellId = reference("spell_id", Spells)
}

class SpellModifierDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<SpellModifierDO>(SpellModifiers)
  var fixedValue by SpellModifiers.fixedValue
  var entityId by SpellModifiers.entityId
  var entityTypeId by SpellModifiers.entityTypeId
  var type by SpellModifiers.type
  var subType by SpellModifiers.subType
  var dice by SpellModifiers.dice
  var restriction by SpellModifiers.restriction
  var statId by SpellModifiers.statId
  var requiresAttunement by SpellModifiers.requiresAttunement
  var durationInterval by SpellModifiers.durationInterval
  var durationUnit by SpellModifiers.durationUnit
  var friendlyTypeName by SpellModifiers.friendlyTypeName
  var friendlySubtypeName by SpellModifiers.friendlySubtypeName
  var isGranted by SpellModifiers.isGranted
  var value by SpellModifiers.value
  var modifierTypeId by SpellModifiers.modifierTypeId
  var modifierSubTypeId by SpellModifiers.modifierSubTypeId
  var componentId by SpellModifiers.componentId
  var componentTypeId by SpellModifiers.componentTypeId
  var die by SpellModifiers.die
  var count by SpellModifiers.count
  var usePrimaryStat by SpellModifiers.usePrimaryStat
  var spellId = SpellDO referencedOn SpellModifiers.spellId
}

object SpellConditions : IntIdTable("spell_conditions") {
  val type = integer("type")
  val conditionId = integer("condition_id")
  val durationUnit = text("duration_unit").nullable()
  val exception = text("exception")
  val spellId = reference("spell_id", Spells)
}

class SpellConditionDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<SpellConditionDO>(SpellConditions)
  var type by SpellConditions.type
  var conditionId by SpellConditions.conditionId
  var durationUnit by SpellConditions.durationUnit
  var exception by SpellConditions.exception
  var spellId = SpellConditionDO referencedOn SpellConditions.spellId
}

object SpellSources : IntIdTable("spell_sources") {
  val sourceId = integer("source_id").index()
  val pageNumber = integer("page_number").nullable()
  val sourceType = integer("source_type")
  val spellId = reference("spell_id", Spells)
}

class SpellSourceDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<SpellSourceDO>(SpellSources)
  var sourceId by SpellSources.sourceId
  var pageNumber by SpellSources.pageNumber
  var sourceType by SpellSources.sourceType
  var spellId = SpellDO referencedOn SpellSources.spellId
}

object SpellTags : IntIdTable("spell_tags") {
  val tag = text("tag").index()
  val spellId = reference("spell_id", Spells)
}

class SpellTagDO(id: EntityID<Int>): IntEntity(id) {
  companion object : IntEntityClass<SpellTagDO>(SpellTags)
  var tag by SpellTags.tag
  var spellId = SpellDO referencedOn SpellTags.spellId
}

