package ca.kittle.models.integrations.character

import ca.kittle.models.Activation
import ca.kittle.models.Dice
import ca.kittle.models.integrations.Source
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
    val overrideSaveDc: Int?,
    val limitedUse: LimitedUse?,
    val id: Long?,
    val entityTypeId: Long?,
    val definition: SpellDefinition?,
    val activation: Activation?,
    val range: SpellRange?,
    val asPartOfWeaponAttack: Boolean?,
    val description: String?,
    val snippet: String?,
    val concentration: Boolean?,
    val ritual: Boolean?,
    val rangeArea: String?,
    val damageEffect: String?,
    val components: List<Int>?,
    val componentsDescription: String?,
    val saveDcAbilityId: Long?,
    val healing: String?,
    val healingDice: List<Dice>?,
    val tempHpDice: List<Dice>?,
    val attackType: String?,
    val canCastAtHigherLevel: Boolean?,
    val isHomebrew: Boolean?,
    val version: Int?,
    val sourceId: Long?,
    val sourcePageNumber: Int?,
    val requiresSavingThrow: Boolean?,
    val requiresAttackRoll: Boolean?,
    val atHigherLevels: AtHigherLevels?,
    val modifiers: List<SpellModifier>?,
    val conditions: List<String>?,
    val tags: List<String>?,
    val castingTimeDescription: String?,
    val scaleType: String?,
    val sources: List<Source>?,
    val spellGroups: List<Int>?,
    val definitionId: Long?,
    val prepared: Boolean?,
    val countsAsKnownSpell: Boolean?,
    val usesSpellSlot: Boolean?,
    val castAtLevel: Int?,
    val alwaysPrepared: Boolean?,
    val restriction: String?,
    val spellCastingAbilityId: Long?,
    val displayAsAttack: Boolean?,
    val additionalDescription: String?,
    val castOnlyAsRitual: Boolean?,
    val ritualCastingType: String?,
    val baseLevelAtWill: Boolean?,
    val atWillLimitedUseLevel: Int?,
    val isSignatureSpell: Boolean?,
    val componentId: Long?,
    val componentTypeId: Long?,
    val spellListId: Long?
)

