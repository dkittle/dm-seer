package ca.kittle.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class Creature (
    val id: Int,
    val name: String,
    val subTypes: List<String>,
    val size: String,
    val alignment: String,
    val str: Int,
    val dex: Int,
    val con: Int,
    val int: Int,
    val wis: Int,
    val cha: Int,
    val averageHitPoints: Int,
    val hpDice: Dice,
    val challengeRating: String,
    val swarmName: String?,
    val swarmSize: String?,
    val swarmType: String?,
    val armorClass: Int,
    val armor: String,
    val avatarUrl : String,
    val largeAvatarUrl : String?,
    val basicAvatarUrl : String?,
    val speeds: List<CreatureSpeed> = arrayListOf(),
    val senses: List<String> = arrayListOf(),
    val savingThrows: List<String> = arrayListOf(),
    val skills: List<CreatureSkill> = arrayListOf(),
    val languages: List<String>,
    val creatureType: String,
    val passivePerception: Int,
    val isHomebrew: Boolean,
    val sources: List<SourceReference>,
    val isLegacy: Boolean,
    val isLegendary: Boolean,
    val isMythic: Boolean,
    val hasLair: Boolean,
    val environments: List<String> = arrayListOf(),
    val immunities: List<DamageType> = arrayListOf(),
    val resistances: List<DamageType> = arrayListOf(),
    val vulnerabilities: List<DamageType> = arrayListOf(),
    val conditionImmunities: List<Condition> = arrayListOf(),
    val traits: List<BaseTrait> = arrayListOf(),
    val actions: List<BaseFeature> = arrayListOf(),
    val bonusActions: List<BaseFeature> = arrayListOf(),
    val reactions: List<BaseFeature> = arrayListOf(),
    val mythicActions: List<BaseFeature> = arrayListOf(),
    val legendaryActions: List<BaseFeature> = arrayListOf(),
    val mythicDescription: String,
    val legendaryDescription: String,
    val tags: List<String> = arrayListOf(),
    val createdOn: LocalDateTime,
    val updatedOn: LocalDateTime,
    val official: Boolean,
    val srd: Boolean
) {
    companion object {
        fun proficiencyBonus(cr: String): Int {
            val id = ChallengeRatings.getChallengeRatingByLabel(cr)?.id ?: 0
            return when (id) {
                in 1..8 -> 2
                in 9..12 -> 3
                in 13..16 -> 4
                in 17..20 -> 5
                in 21..24 -> 6
                in 25..28 -> 7
                in 29..32 -> 8
                in 33..34 -> 9
                else -> 0
            }
        }
        fun experiencePoints(cr: String): Int {
            val id = ChallengeRatings.getChallengeRatingByLabel(cr)?.id ?: 0
            return when (id) {
                1 -> 10
                2 -> 25
                3 -> 50
                4 -> 10
                5 -> 200
                6 -> 450
                7 -> 700
                8 -> 1100
                9 -> 1800
                10 -> 2300
                11 -> 2900
                12 -> 3900
                13 -> 5000
                14 -> 5900
                15 -> 7200
                16 -> 8400
                17 -> 1000
                18 -> 11500
                19 -> 13000
                20 -> 15000
                21 -> 18000
                22 -> 20000
                23 -> 22000
                24 -> 25000
                25 -> 33000
                26 -> 41000
                27 -> 50000
                28 -> 62000
                29 -> 75000
                30 -> 90000
                31 -> 105000
                32 -> 120000
                33 -> 135000
                34 -> 155000
                else -> 0
            }
        }
    }
}

@Serializable
data class CoreCreature (
    val id: Int,
    val name: String,
    val size: String,
    val alignment: String,
    val averageHitPoints: Int,
    val challengeRating: String,
    val avatarUrl: String,
    val swarmName: String?,
    val armorClass: Int,
    val creatureType: String,
    val srd: Boolean,
    val isLegacy: Boolean,
    val isLegendary: Boolean,
    val isMythic: Boolean
)

@Serializable
data class CreatureAvatars (
    val id: Int,
    val avatarUrl: String,
    val largeAvatarUrl: String?,
    val basicAvatarUrl: String?
)

object Creatures : IntIdTable("creatures") {
    val name = text("name")
    val size = text("size")
    val alignment = text("alignment")
    val averageHitpoints = integer("average_hitpoints")
    val hitpointDice = text("hitpoint_dice")
    val challengeRating = integer("challenge_rating").index()
    val armorClass = integer("armor_class")
    val armor = text("armor")
    val avatarUrl = text("avatar_url")
    val largeAvatarUrl= text("large_avatar_url").nullable()
    val basicAvatarUrl = text("basic_avatar_url").nullable()
    val creatureType = text("creature_type")
    val passivePerception = integer("passive_perception")
    val isHomebrew = bool("is_homebrew")
    val isLegacy = bool("is_legacy")
    val isLegendary = bool("is_legendary")
    val isMythic = bool("is_mythic")
    val hasLair = bool("has_lair")
    val swarmName = text("swarm_name").nullable()
    val swarmSize = integer("swarm_size").nullable()
    val swarmType = integer("swarm_type").nullable()
    val lairDescription = text("lairDescription")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val official = bool("official")
    val srd = bool("srd")
    val legendaryDescription = text("legendary_description")
    val mythicDescription = text("mythic_desription")
    val accountId = reference("account_id", Accounts)
}

class CreatureDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureDO>(Creatures)
    var name by Creatures.name
    var size by Creatures.size
    var alignment by Creatures.alignment
    var averageHitpoints by Creatures.averageHitpoints
    var hitpointDice by Creatures.hitpointDice
    var challengeRating by Creatures.challengeRating
    var armorClass by Creatures.armorClass
    var armor by Creatures.armor
    var avatarUrl by Creatures.avatarUrl
    var largeAvatarUrl by Creatures.largeAvatarUrl
    var basicAvatarUrl by Creatures.basicAvatarUrl
    var creatureType by Creatures.creatureType
    var passivePerception by Creatures.passivePerception
    var isHomebrew by Creatures.isHomebrew
    var isLegacy by Creatures.isLegacy
    var isLegendary by Creatures.isLegendary
    var isMythic by Creatures.isMythic
    var hasLair by Creatures.hasLair
    var swarmName by Creatures.swarmName
    var swarmSize by Creatures.swarmSize
    var swarmType by Creatures.swarmType
    var lairDescription by Creatures.lairDescription
    var createdOn by Creatures.createdOn
    var updatedOn by Creatures.updatedOn
    var official by Creatures.official
    var srd by Creatures.srd
    var legendaryDescription by Creatures.legendaryDescription
    var mythicDescription by Creatures.mythicDescription
    var accountId by AccountDO referencedOn Creatures.accountId
}


object CreatureOrigins : IntIdTable("creature_origins") {
    val originId = integer("origin_id").index()
    val originUrl = text("origin_url")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureOriginDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureOriginDO>(CreatureOrigins)
    var originId by CreatureOrigins.originId
    var originUrl by CreatureOrigins.originUrl
    var creatureId by CreatureDO referencedOn CreatureOrigins.creatureId
}

object MonsterSubTypes : IntIdTable("creature_subtypes") {
    val subType = integer("subType")
    val creatureId = reference("creature_id", Creatures)
}

class MonsterSubTypeDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<MonsterSubTypeDO>(MonsterSubTypes)
    var subType by MonsterSubTypes.subType
    var creatureId = CreatureDO referencedOn MonsterSubTypes.creatureId
}

object CreatureStats : IntIdTable("creature_stats") {
    val stat = integer("stat")
    val value = integer("value")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureStatDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureStatDO>(CreatureStats)
    var stat by CreatureStats.stat
    var value by CreatureStats.value
    var creatureId = CreatureDO referencedOn CreatureStats.creatureId
}

object CreatureSenses : IntIdTable("creature_senses") {
    val sense = integer("sense")
    val notes = text("notes")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureSenseDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureSenseDO>(CreatureSenses)
    var sense by CreatureSenses.sense
    var notes by CreatureSenses.notes
    var creatureId = CreatureDO referencedOn CreatureSenses.creatureId
}

object CreatureSaves : IntIdTable("creature_saves") {
    val save = integer("save")
    val modifier = integer("modifier")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureSaveDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureSaveDO>(CreatureSaves)
    var save by CreatureSaves.save
    var modifier by CreatureSaves.modifier
    var creatureId = CreatureDO referencedOn CreatureSaves.creatureId
}

object CreatureSkills : IntIdTable("creature_skills") {
    val skill = integer("skill").index()
    val value = integer("value")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureSkillDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureSkillDO>(CreatureSkills)
    var skill by CreatureSkills.skill
    var value by CreatureSkills.value
    var creatureId = CreatureDO referencedOn CreatureSkills.creatureId
}


object CreatureMovements : IntIdTable("creature_movements") {
    val movement = integer("movement").index()
    val speed = integer("speed")
    val notes = text("notes")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureMovementDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureMovementDO>(CreatureMovements)
    var movement by CreatureMovements.movement
    var speed by CreatureMovements.speed
    var notes by CreatureMovements.notes
    var creatureId = CreatureDO referencedOn CreatureMovements.creatureId
}

object CreatureLanguages : IntIdTable("creature_languages") {
    val language = integer("language")
    val notes = text("notes")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureLanguageDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureLanguageDO>(CreatureLanguages)
    var language by CreatureLanguages.language
    var notes by CreatureLanguages.notes
    var creatureId = CreatureDO referencedOn CreatureLanguages.creatureId
}

object CreatureSources : IntIdTable("creature_sources") {
    val sourceBook = integer("source").index()
    val pageNumber = integer("page_number")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureSourceDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureSourceDO>(CreatureSources)
    var sourceBook by CreatureSources.sourceBook
    var pageNumber by CreatureSources.pageNumber
    var creatureId = CreatureDO referencedOn CreatureSources.creatureId
}

object CreatureTags : IntIdTable("creature_tags") {
    val tag = text("tag").index()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureTagDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureTagDO>(CreatureTags)
    var tag by CreatureTags.tag
    var creatureId = CreatureDO referencedOn CreatureTags.creatureId
}

object CreatureEnvironments : IntIdTable("creature_environments") {
    val environment = integer("environment").index()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureEnvironmentDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureEnvironmentDO>(CreatureEnvironments)
    var environment by CreatureEnvironments.environment
    var creatureId = CreatureDO referencedOn CreatureEnvironments.creatureId
}


object CreatureResistances : IntIdTable("creature_resistances") {
    val resistance = integer("resistance")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureResistanceDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureResistanceDO>(CreatureResistances)
    var resistance by CreatureResistances.resistance
    var creatureId = CreatureDO referencedOn CreatureResistances.creatureId
}

object CreatureImmunities : IntIdTable("creature_immunities") {
    val immunity = integer("immunity")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureImmunityDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureImmunityDO>(CreatureImmunities)
    var immunity by CreatureImmunities.immunity
    var creatureId = CreatureDO referencedOn CreatureImmunities.creatureId
}

object CreatureVulnerabilities : IntIdTable("creature_vulnerabilities") {
    val vulnerability = integer("vulnerability")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureVulnerabilityDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureVulnerabilityDO>(CreatureVulnerabilities)
    var vulnerability by CreatureVulnerabilities.vulnerability
    var creatureId = CreatureDO referencedOn CreatureVulnerabilities.creatureId
}

object CreatureConditionImmunities : IntIdTable("condition_immunities") {
    val condition = integer("condition")
    val creatureId = reference("creature_id", Creatures)
}

class CreatureConditionImmunityDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureConditionImmunityDO>(CreatureConditionImmunities)
    var condition by CreatureConditionImmunities.condition
    var creatureId = CreatureDO referencedOn CreatureConditionImmunities.creatureId
}


/**
 * Basic trait (type 1):
 * trait
 * type (Trait)
 * description
 * activationType (optional)
 * uses (optional number of uses)
 * resetType: 1 = short rest, 2 = long rest
 *
 * Rollable trait (type 2):
 * trait
 * type (RollableTrait)
 * description
 * activationType (optional)
 * uses (optional number of uses)
 * resetType: 1 = short rest, 2 = long rest
 * list of rolls - to find the list of rolls in Creature Rolls
 *
 * Per-day spell casting trait (type 3):
 * trait
 * type (SpellCastingTrait)
 * description
 * resetType: 1 = short rest, 2 = long rest
 * list of spells - to find the list of spells in Creature Spells
 *
 * Spell-slots casting trait (type 4):
 * trait
 * type (SpellCastingTrait)
 * description
 * resetType: 1 = short rest, 2 = long rest
 * list of spells - to find the list of spells in Creature Spells
 */
object CreatureTraits : IntIdTable("creature_traits") {
    val trait = text("trait").index()
    val type = integer("type")
    val description = text("description")
    val activationType = integer("activation_type").nullable()
    val uses = integer("uses").nullable()
    val resetType = integer("reset_type").nullable()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureTraitDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureTraitDO>(CreatureTraits)
    var trait by CreatureTraits.trait
    val type by CreatureTraits.type
    var description by CreatureTraits.description
    var activationType by CreatureTraits.activationType
    var uses by CreatureTraits.uses
    var resetType by CreatureTraits.resetType
    var creatureId = CreatureDO referencedOn CreatureTraits.creatureId
}

object CreatureRolls : IntIdTable("creature_rolls") {
    val roll = text("roll")
    val damageType = text("damage_type")
    val condition = text("condition").nullable()
    val saveDc = integer("save_dc").nullable()
    val saveAbility = integer("save_ability").nullable()
    val featureName = text("feature_name").index()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureRollDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureRollDO>(CreatureRolls)
    var roll by CreatureRolls.roll
    var damageType by CreatureRolls.damageType
    var condition by CreatureRolls.condition
    var saveDc by CreatureRolls.saveDc
    var saveAbility by CreatureRolls.saveAbility
    var featureName by CreatureRolls.featureName
    var creatureId = CreatureDO referencedOn CreatureRolls.creatureId
}

object CreatureSpells : IntIdTable("creature_spells") {
    val order = integer("order")
    val uses = integer("uses")
    val spells = text("spells")
    val featureName = text("feature_name").index()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureSpellDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureSpellDO>(CreatureSpells)
    var order by CreatureSpells.order
    var uses by CreatureSpells.uses
    var spells by CreatureSpells.spells
    var featureName by CreatureSpells.featureName
    var creatureId = CreatureDO referencedOn CreatureSpells.creatureId
}

/**
 * Base Feature (type 1)]:
 * name
 * type (Feature)
 * description
 * activationType (optional)
 * uses (optional number of uses)
 * resetType: 1 = short rest, 2 = long rest
 *
 * Rollable feature (type 2):
 * name
 * type (RollableFeature)
 * description
 * activationType (optional)
 * uses (optional number of uses)
 * resetType: 1 = short rest, 2 = long rest
 * list of rolls - to find the list of rolls in Creature Rolls
 *
 * Attack action (type 3):
 * name
 * type (AttackAction)
 * description
 * toHit
 * attackType
 * range
 * target
 * list of rolls - to find the list of rolls in Creature Rolls
 *
 * Spell casting feature (type 4):
 * name
 * type (SpellCastingFeature)
 * description
 * resetType: 1 = short rest, 2 = long rest
 * list of spells - to find the list of spells in Creature Spells
 *
 * Spell casting action (type 5):
 * name
 * type (SpellCastingFeature)
 * description
 * resetType: 1 = short rest, 2 = long rest
 * list of spells - to find the list of spells in Creature Spells
 */
object CreatureFeatures : IntIdTable("creature_features") {
    val feature = text("feature").index()
    val type = integer("type")
    val description = text("description")
    val activationType = integer("activation_type")
    val uses = integer("uses").nullable()
    val resetType = integer("reset_type").nullable()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureFeatureDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureFeatureDO>(CreatureFeatures)
    var feature by CreatureFeatures.feature
    var type by CreatureFeatures.type
    var description by CreatureFeatures.description
    var activationType by CreatureFeatures.activationType
    var uses by CreatureFeatures.uses
    var resetType by CreatureFeatures.resetType
    var creatureId = CreatureDO referencedOn CreatureFeatures.creatureId
}

object CreatureAttacks : IntIdTable("creature_attacks") {
    val type = integer("type")
    val toHit = integer("to_hit")
    val range = text("range")
    val target = text("target")
    val featureName = text("feature_name").index()
    val creatureId = reference("creature_id", Creatures)
}

class CreatureAttackDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureAttackDO>(CreatureAttacks)
    var type by CreatureAttacks.type
    var toHit by CreatureAttacks.toHit
    var range by CreatureAttacks.range
    var target by CreatureAttacks.target
    var featureName by CreatureAttacks.featureName
    var creatureId = CreatureDO referencedOn CreatureAttacks.creatureId
}
