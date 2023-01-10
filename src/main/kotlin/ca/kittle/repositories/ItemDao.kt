package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select

object ItemDao {

    suspend fun saveItem(item: Item, accountId: Int): Int {
        val itemId = saveCoreItem(item, accountId)
        if (!item.tags.isNullOrEmpty())
            saveItemTags(item.tags, itemId)
        if (!item.grantedModifiers.isNullOrEmpty())
            saveItemModifiers(item.grantedModifiers, itemId)
        item.weaponBehaviors.forEach {behavior ->
            val id = saveItemWeaponBehavior(behavior, itemId)
            behavior.properties?.forEach {
                saveItemWeaponProperty(it, id)
            }
        }
        if (!item.sources.isNullOrEmpty())
            saveItemSources(item.sources, itemId)
        return itemId
    }

    private suspend fun saveCoreItem(item: Item, userId: Int): Int = dbQuery {
        return@dbQuery Items.insertAndGetId {
            it[name] = item.name
            it[description] = item.description
            it[snippet] = item.snippet
            it[weight] = item.weight
            it[weightMultiplier] = item.weightMultiplier
            it[capacity] = item.capacity
            it[capacityWeight] = item.capacityWeight
            it[type] = item.type
            it[subType] = item.subType
            it[attunementDescription] = item.attunementDescription
            it[rarity] = item.rarity
            it[bundleSize] = item.bundleSize
            it[avatarUrl] = item.avatarUrl
            it[largeAvatarUrl] = item.largeAvatarUrl
            it[filterType] = item.filterType
            it[cost] = item.cost
            it[baseItemId] = item.baseItemId
            it[baseArmorName] = item.baseArmorName
            it[strengthRequirement] = item.strengthRequirement
            it[armorClass] = item.armorClass
            it[stealthCheck] = item.stealthCheck
            it[damage] = item.damage?.diceString ?: ""
            it[damageType] = item.damageType
            it[fixedDamage] = item.fixedDamage
            it[attackType] = item.attackType
            it[categoryId] = item.categoryId
            it[range] = item.range
            it[longRange] = item.longRange
            it[levelInfusionGranted] = item.levelInfusionGranted
            it[armorTypeId] = item.armorTypeId
            it[gearTypeId] = item.gearTypeId
            it[groupedId] = item.groupedId
            it[magic] = item.magic
            it[canAttune] = item.canAttune
            it[canEquip] = item.canEquip
            it[isHomebrew] = item.isHomebrew
            it[stackable] = item.stackable
            it[isPack] = item.isPack
            it[isConsumable] = item.isConsumable
            it[isMonkWeapon] = item.isMonkWeapon
            it[canBeAddedToInventory] = item.canBeAddedToInventory
            it[isContainer] = item.isContainer
            it[isCustomItem] = item.isCustomItem
            it[srd] = false
            it[accountId] = userId
        }.value
    }

    private suspend fun saveItemTags(tags: List<String>, id: Int) = dbQuery {
        tags.forEach {t ->
            ItemTags.insert {i ->
                i[tag] = t
                i[itemId] = id
            }
        }
    }

    private suspend fun saveItemModifiers(modifiers: List<Modifier>, id: Int) = dbQuery {
        modifiers.forEach { modifier ->
            ItemModifiers.insert {
                it[fixedValue] = modifier.fixedValue
                it[entityId] = modifier.entityId
                it[entityTypeId] = modifier.entityTypeId
                it[type] = modifier.type
                it[subType] = modifier.subType
                it[dice] = modifier.dice?.diceString
                it[restriction] = modifier.restriction
                it[statId] = modifier.statId
                it[requiresAttunement] = modifier.requiresAttunement
                it[durationInterval] = modifier.duration?.durationInterval
                it[durationUnit] = modifier.duration?.durationUnit
                it[friendlyTypeName] = modifier.friendlyTypeName
                it[friendlySubtypeName] = modifier.friendlySubtypeName
                it[isGranted] = modifier.isGranted
                it[value] = modifier.value
                it[availableToMulticlass] = modifier.availableToMulticlass
                it[modifierTypeId] = modifier.modifierTypeId
                it[modifierSubTypeId] = modifier.modifierSubTypeId
                it[componentId] = modifier.componentId
                it[componentTypeId] = modifier.componentTypeId
                it[itemId] = id
            }
        }
    }

    private suspend fun saveItemWeaponBehavior(behavior: WeaponBehavior, id: Int): Int = dbQuery {
        return@dbQuery WeaponBehaviors.insertAndGetId {
            it[baseItemId] = behavior.baseItemId
            it[baseTypeId] = behavior.baseTypeId
            it[type] = behavior.type
            it[attackType] = behavior.attackType
            it[categoryId] = behavior.categoryId
            it[damage] = behavior.damage.diceString ?: ""
            it[damageType] = behavior.damageType
            it[range] = behavior.range
            it[longRange] = behavior.longRange
            it[isMonkWeapon] = behavior.isMonkWeapon
            it[itemId] = id
        }.value
    }

    private suspend fun saveItemWeaponProperty(property: WeaponProp, id: Int) = dbQuery {
        ItemWeaponProperties.insert {
            it[name] = property.name
            it[notes] = property.notes
            it[description] = property.description
            it[weaponBehaviorId] = id
        }
    }

    private suspend fun saveItemSources(sources: List<ItemSource>, id: Int) = dbQuery {
        sources.forEach { src ->
            ItemSources.insert {
                it[sourceId] = src.sourceId
                it[pageNumber] = src.pageNumber
                it[sourceType] = src.sourceType
                it[itemId] = id
            }
        }
    }

}
