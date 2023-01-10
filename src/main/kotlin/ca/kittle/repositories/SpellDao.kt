package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.models.*
import ca.kittle.models.spell.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId

object SpellDao {

    suspend fun saveSpell(spell: Definition, accountId: Int): Int {
        val spellId = saveCoreSpell(spell, accountId)
        spell.modifiers.forEach { modifier ->
            val id = saveSpellModifier(modifier, spellId)
            modifier.atHigherLevels.higherLevelDefinitions.forEach {
                saveAtHigherLevels(it, id)
            }
        }
        if (spell.conditions.isNotEmpty())
            saveSpellConditions(spell.conditions, spellId)
        if (spell.tags.isNotEmpty())
            saveSpellTags(spell.tags, spellId)
        if (spell.sources.isNotEmpty())
            saveSpellSources(spell.sources, spellId)
        return spellId
    }

    private suspend fun saveCoreSpell(spell: Definition, userId: Int): Int = Database.dbQuery {
        return@dbQuery Spells.insertAndGetId {
            it[name] = spell.name
            it[level] = spell.level
            it[school] = spell.school
            it[durationInterval] = spell.duration.durationInterval
            it[durationUnit] = spell.duration.durationUnit
            it[durationType] = spell.duration.durationType
            it[activationTime] = spell.activation.activationTime
            it[activationType] = spell.activation.activationType
            it[origin] = spell.range.origin
            it[rangeValue] = spell.range.rangeValue
            it[aoeType] = spell.range.aoeType
            it[aoeValue] = spell.range.aoeValue
            it[partOfWeaponAttack] = spell.asPartOfWeaponAttack
            it[description] = spell.description ?: ""
            it[concentration] = spell.concentration
            it[ritual] = spell.ritual
            it[vComponent] = spell.components.contains(1)
            it[sComponent] = spell.components.contains(2)
            it[mComponent] = spell.components.contains(3)
            it[componentsDescription] = spell.componentsDescription
            it[saveDcAbilityId] = spell.saveDcAbilityId
            it[attackType] = spell.attackType
            it[spellGroup] = if (spell.spellGroups.contains(1)) 1 else null
            it[canCastAtHigherLevel] = spell.canCastAtHigherLevel
            it[isHomebrew] = spell.isHomebrew
            it[requiresSavingThrow] = spell.requiresSavingThrow
            it[requiresAttackRoll] = spell.requiresAttackRoll
            it[castingTimeDescription] = spell.castingTimeDescription
            it[scaleType] = spell.scaleType
            it[public] = false
            it[srd] = false
            it[accountId] = userId
        }.value
    }

    private suspend fun saveSpellModifier(modifier: SpellModifier, id: Int): Int = Database.dbQuery {
        SpellModifiers.insertAndGetId {
            it[fixedValue] = modifier.fixedValue
            it[entityId] = modifier.entityId
            it[entityTypeId] = modifier.entityTypeId
            it[type] = modifier.type
            it[subType] = modifier.subType
            it[dice] = modifier.dice?.diceString ?: ""
            it[restriction] = modifier.restriction
            it[statId] = modifier.statId
            it[requiresAttunement] = modifier.requiresAttunement
            it[durationInterval] = modifier.duration?.durationInterval
            it[durationUnit] = modifier.duration?.durationUnit
            it[friendlyTypeName] = modifier.friendlyTypeName
            it[friendlySubtypeName] = modifier.friendlySubtypeName
            it[isGranted] = modifier.isGranted
            it[value] = modifier.value
            it[modifierTypeId] = modifier.modifierTypeId
            it[modifierSubTypeId] = modifier.modifierSubTypeId
            it[componentId] = modifier.componentId
            it[componentTypeId] = modifier.componentTypeId
            it[die] = modifier.die?.diceString
            it[count] = modifier.count
            it[usePrimaryStat] = modifier.usePrimaryStat
            it[spellId] = id
        }.value
    }

    private suspend fun saveAtHigherLevels(higherLevel: HigherLevelDefinitions, id: Int) = Database.dbQuery {
        return@dbQuery AtHigherLevels.insert {
            it[atLevel] = higherLevel.level
            it[typeId] = higherLevel.typeId
            it[dice] = higherLevel.dice?.diceString
            it[value] = higherLevel.value
            it[details] = higherLevel.details
            it[spellModifierId] = id
        }
    }

    private suspend fun saveSpellConditions(conditions: List<SpellCondition>, id: Int) = Database.dbQuery {
        conditions.forEach { condition ->
            SpellConditions.insert {
                it[type] = condition.type
                it[conditionId] = condition.conditionId
                it[durationUnit] = condition.durationUnit
                it[exception] = condition.exception
                it[spellId] = id
            }
        }
    }

    private suspend fun saveSpellTags(tags: List<String>, id: Int) = Database.dbQuery {
        tags.forEach { t ->
            SpellTags.insert { i ->
                i[tag] = t
                i[spellId] = id
            }
        }
    }

    private suspend fun saveSpellSources(sources: List<SpellSource>, id: Int) = Database.dbQuery {
        sources.forEach { src ->
            SpellSources.insert {
                it[sourceId] = src.sourceId
                it[pageNumber] = src.pageNumber
                it[sourceType] = src.sourceType
                it[spellId] = id
            }
        }
    }

}
