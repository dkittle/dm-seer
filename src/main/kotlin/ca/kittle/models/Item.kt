package ca.kittle.models

import ca.kittle.models.ItemModifiers.nullable
import ca.kittle.models.Items.nullable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class DdbItems (
    @SerialName("id"         ) var id         : Int?            = null,
    @SerialName("success"    ) var success    : Boolean?        = null,
    @SerialName("message"    ) var message    : String?         = null,
    @SerialName("data"       ) var items       : ArrayList<Item> = arrayListOf(),
    @SerialName("pagination" ) var pagination : String?         = null

)

@Serializable
data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val snippet: String?,
    val weight: Float,
    val weightMultiplier: Float,
    val capacity: String?,
    val capacityWeight: Float,
    val type: String?,
    val subType: String?,
    val attunementDescription: String?,
    val rarity: String,
    val bundleSize: Int,
    val avatarUrl: String?,
    val largeAvatarUrl: String?,
    val filterType: String,
    val cost: Float?,
    val tags: List<String>,
    val grantedModifiers: List<Modifier>,
    val weaponBehaviors: List<WeaponBehavior>,
    val baseItemId: Int?,
    val baseArmorName: String?,
    val strengthRequirement: Int?,
    val armorClass: Int?,
    val stealthCheck: Int?,
    val damage: Dice?,
    val damageType: String?,
    val fixedDamage: Int?,
    val properties: List<WeaponProp>?,
    val attackType: Int?,
    val categoryId: Int?,
    val range: Int?,
    val longRange: Int?,
    val levelInfusionGranted: Int?,
    val sources: List<ItemSource>,
    val armorTypeId: Int?,
    val gearTypeId: Int?,
    val groupedId: Int?,
    val magic: Boolean,
    val canAttune: Boolean,
    val canEquip: Boolean,
    val isHomebrew: Boolean,
    val stackable: Boolean,
    val isPack: Boolean,
    val isConsumable: Boolean,
    val isMonkWeapon: Boolean,
    val canBeAddedToInventory: Boolean,
    val isContainer: Boolean,
    val isCustomItem: Boolean,
    val srd: Boolean = false
)

@Serializable
data class Modifier(
    val id: Int,
    val fixedValue: Int?,
    val entityId: Int?,
    val entityTypeId: Int?,
    val type: String,
    val subType: String,
    val dice: Dice?,
    val restriction: String?,
    val statId: Int?,
    val requiresAttunement: Boolean,
    val duration: Duration?,
    val friendlyTypeName: String,
    val friendlySubtypeName: String,
    val isGranted: Boolean,
    val value: Int?,
    val availableToMulticlass: Boolean,
    val modifierTypeId: Int,
    val modifierSubTypeId: Int,
    val componentId: Int,
    val componentTypeId: Int
)

@Serializable
data class WeaponBehavior (
    val baseItemId: Int,
    val baseTypeId: Int,
    val type: String,
    val attackType: Int,
    val categoryId: Int,
    val properties: List<WeaponProp>?,
    val damage: Dice,
    val damageType: String,
    val range: Int,
    val longRange: Int,
    val isMonkWeapon: Boolean
)

@Serializable
data class WeaponProp (
    val id: Int,
    val name: String,
    val description: String,
    val notes: String?
)

object Items : IntIdTable("items") {
    val name = text("name")
    val description = text("description")
    val snippet = text("snippet").nullable()
    val weight = float("weight")
    val weightMultiplier = float("weight_multiplier")
    val capacity = text("capacity").nullable()
    val capacityWeight = float("capacity_weight")
    val type = text("type").nullable()
    val subType = text("sub_type").nullable()
    val attunementDescription = text("attunement_description").nullable()
    val rarity = text("rarity")
    val bundleSize = integer("bundle_size")
    val avatarUrl = text("avatar_url").nullable()
    val largeAvatarUrl = text("large_avatar_url").nullable()
    val filterType = text("filter_type")
    val cost = float("cost").nullable()
    val baseItemId = integer("base_item_id").nullable()
    val baseArmorName = text("base_armor_name").nullable()
    val strengthRequirement = integer("strength_req").nullable()
    val armorClass = integer("armor_class").nullable()
    val stealthCheck = integer("stealth_check").nullable()
    val damage = text("damage").nullable()
    val damageType = text("damage_type").nullable()
    val fixedDamage = integer("fixed_damage").nullable()
    val attackType = integer("attack_type").nullable()
    val categoryId = integer("category_id").nullable()
    val range = integer("range").nullable()
    val longRange = integer("long_range").nullable()
    val levelInfusionGranted = integer("level_infusion_granted").nullable()
    val armorTypeId = integer("armor_type_id").nullable()
    val gearTypeId = integer("gear_type_id").nullable()
    val groupedId = integer("grouped_id").nullable()
    val magic = bool("magic")
    val canAttune = bool("can_attune")
    val canEquip = bool("can_equip")
    val isHomebrew = bool("is_homebrew")
    val stackable = bool("stackable")
    val isPack = bool("is_pack")
    val isConsumable = bool("is_consumable")
    val isMonkWeapon = bool("is_monk_weapon")
    val canBeAddedToInventory = bool("can_be_added_to_inv")
    val isContainer = bool("is_container")
    val isCustomItem = bool("is_custom_item")
    val srd = bool("srd")
    val accountId = reference("account_id", Accounts)
}

class ItemDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ItemDO>(Items)
    var name by Items.name
    var description by Items.description
    var snippet by Items.snippet
    var weight by Items.weight
    var weightMultiplier by Items.weightMultiplier
    var capacity by Items.capacity
    var capacityWeight by Items.capacityWeight
    var type by Items.type
    var subType by Items.subType
    var attunementDescription by Items.attunementDescription
    var rarity by Items.rarity
    var bundleSize by Items.bundleSize
    var avatarUrl by Items.avatarUrl
    var largeAvatarUrl by Items.largeAvatarUrl
    var filterType by Items.filterType
    var cost by Items.cost
    var baseItemId by Items.baseItemId
    var baseArmorName by Items.baseArmorName
    var strengthRequirement by Items.strengthRequirement
    var armorClass by Items.armorClass
    var stealthCheck by Items.stealthCheck
    var damage by Items.damage
    var damageType by Items.damageType
    var fixedDamage by Items.fixedDamage
    var attackType by Items.attackType
    var categoryId by Items.categoryId
    var range by Items.range
    var longRange by Items.longRange
    var levelInfusionGranted by Items.levelInfusionGranted
    var armorTypeId by Items.armorTypeId
    var gearTypeId by Items.gearTypeId
    var groupedId by Items.groupedId
    var magic by Items.magic
    var canAttune by Items.canAttune
    var canEquip by Items.canEquip
    var isHomebrew by Items.isHomebrew
    var stackable by Items.stackable
    var isPack by Items.isPack
    var isConsumable by Items.isConsumable
    var isMonkWeapon by Items.isMonkWeapon
    var canBeAddedToInventory by Items.canBeAddedToInventory
    var isContainer by Items.isContainer
    var isCustomItem by Items.isCustomItem
    var srd by Items.srd
    var accountId = AccountDO referencedOn Items.accountId
}

object ItemSources : IntIdTable("item_sources") {
    val sourceId = integer("source_id").index()
    val pageNumber = integer("page_number").nullable()
    val sourceType = integer("source_type")
    val itemId = reference("item_id", Items)
}

class ItemSourceDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ItemSourceDO>(ItemSources)
    var sourceId by ItemSources.sourceId
    var pageNumber by ItemSources.pageNumber
    var sourceType by ItemSources.sourceType
    var itemId = ItemDO referencedOn ItemSources.itemId
}

object ItemTags : IntIdTable("item_tags") {
    val tag = text("tag").index()
    val itemId = reference("item_id", Items)
}

class ItemTagDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ItemTagDO>(ItemTags)
    var tag by ItemTags.tag
    var itemId = ItemDO referencedOn ItemTags.itemId
}

object ItemModifiers : IntIdTable("item_modifiers") {
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
    val availableToMulticlass = bool("available_to_multiclass")
    val modifierTypeId = integer("modifier_type_id")
    val modifierSubTypeId = integer("modifier_sub_type_id")
    val componentId = integer("component_id")
    val componentTypeId = integer("component_type_id")
    val itemId = reference("item_id", Items)
}

class ItemModifierDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ItemModifierDO>(ItemModifiers)
    var fixedValue by ItemModifiers.fixedValue
    var entityId by ItemModifiers.entityId
    var entityTypeId by ItemModifiers.entityTypeId
    var type by ItemModifiers.type
    var subType by ItemModifiers.subType
    var dice by ItemModifiers.dice
    var restriction by ItemModifiers.restriction
    var statId by ItemModifiers.statId
    var requiresAttunement by ItemModifiers.requiresAttunement
    var durationInterval by ItemModifiers.durationInterval
    var durationUnit by ItemModifiers.durationUnit
    var friendlyTypeName by ItemModifiers.friendlyTypeName
    var friendlySubtypeName by ItemModifiers.friendlySubtypeName
    var isGranted by ItemModifiers.isGranted
    var value by ItemModifiers.value
    var availableToMulticlass by ItemModifiers.availableToMulticlass
    var modifierTypeId by ItemModifiers.modifierTypeId
    var modifierSubTypeId by ItemModifiers.modifierSubTypeId
    var componentId by ItemModifiers.componentId
    var componentTypeId by ItemModifiers.componentTypeId
    var itemId = ItemDO referencedOn ItemModifiers.itemId
}


object WeaponBehaviors : IntIdTable("weapon_behaviors") {
    val baseItemId = integer("base_item_id")
    val baseTypeId = integer("base_type_id")
    val type = text("type")
    val attackType = integer("attack_type")
    val categoryId = integer("category_id")
    val properties = text("properties").nullable()
    val damage = text("damage")
    val damageType = text("damage_type")
    val range = integer("range")
    val longRange = integer("long_range")
    val isMonkWeapon = bool("is_monk_weapon")
    val itemId = reference("item_id", Items)
}
class WeaponBehaviorDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<WeaponBehaviorDO>(WeaponBehaviors)
    var baseItemId by WeaponBehaviors.baseItemId
    var baseTypeId by WeaponBehaviors.baseTypeId
    var type by WeaponBehaviors.type
    var attackType by WeaponBehaviors.attackType
    var categoryId by WeaponBehaviors.categoryId
    var properties by WeaponBehaviors.properties
    var damage by WeaponBehaviors.damage
    var damageType by WeaponBehaviors.damageType
    var range by WeaponBehaviors.range
    var longRange by WeaponBehaviors.longRange
    var isMonkWeapon by WeaponBehaviors.isMonkWeapon
    var itemId = ItemDO referencedOn WeaponBehaviors.itemId
}

object ItemWeaponProperties : IntIdTable("item_properties") {
    val name = text("name")
    val notes = text("notes").nullable()
    val description = text("description")
    val weaponBehaviorId = reference("weapon_behavior_id", WeaponBehaviors)
}

class ItemWeaponPropertyDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ItemWeaponPropertyDO>(ItemWeaponProperties)
    var name by ItemWeaponProperties.name
    var notes by ItemWeaponProperties.notes
    var description by ItemWeaponProperties.description
    var weaponBehaviorId = WeaponBehaviorDO referencedOn ItemWeaponProperties.weaponBehaviorId
}
