package ca.kittle.models

import ca.kittle.models.Campaigns.nullable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class Encounter(
    var id: Long,
    var name: String,
    var dmName: String,
    var campaignName: String,
    var locationId: Long,
    var location: String,
    var source: String,
    var suggestedAcl: Int
)


object Encounters : IntIdTable("encounters") {
    val name = text("name")
    val description = text("description")
    val suggestedAcl = integer("suggested_acl")
    val difficulty = integer("difficulty")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val sourcePage = integer("source_page").nullable()
    val accountId = reference("account_id", Accounts)
    val campaignId = reference("campaign_id", Campaigns).nullable()
    val locationId = reference("location_id", Locations).nullable()
    val roomId = reference("room_id", Rooms).nullable()
    val sourceId = reference("source_id", DndSources).nullable()
}


class EncounterDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<EncounterDO>(Encounters)
    var name by Encounters.name
    var description by Encounters.description
    var suggestedAcl by Encounters.suggestedAcl
    var difficulty by Encounters.difficulty
    var sourcePage by Encounters.sourcePage
    var createdOn by Encounters.createdOn
    var updatedOn by Encounters.updatedOn
    var accountId = AccountDO referencedOn Accounts.id
    var campaignId = CampaignDO referencedOn Campaigns.id
    var locationId = LocationDO referencedOn Locations.id
    var roomId = RoomDO referencedOn Rooms.id
    var sourceId = DndSourceDO referencedOn DndSources.id
}

object Combats : IntIdTable("combats") {
    val inProgress = bool("in_progress")
    val roundNumber = integer("round_number")
    val turnNumber = integer("turn_number")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val encounterId = reference("encounter_id", Encounters)
}

class CombatDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CombatDO>(Combats)
    var inProgress by Combats.inProgress
    var roundNumber by Combats.roundNumber
    var turnNumber by Combats.turnNumber
    var createdOn by Combats.createdOn
    var updatedOn by Combats.updatedOn
    var encounterId = EncounterDO referencedOn Encounters.id
}

/**
 * For PlayerCharacters:
 * type = PC
 * dates
 * initiative
 * characterId
 *
 * For Creatures:
 * type = CREATURE or NPC
 * dates
 * "health"
 * initiative
 * optional groupId & groupOrder
 * name
 * creatureId
 *
 * For Manual Entries
 * name
 * duration
 */
object Combatants : IntIdTable("combatants") {
    val type = text("type")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val hitpoints = integer("hitpoints")
    val maximumHitpoints = integer("maximum_hitpoints")
    val temporaryHitpoints = integer("temporaryHitpoints")
    val temporaryMaximum = integer("temporary_maximum")
    val initiative = integer("initiative")
    val groupId = text("group_id").nullable()
    val groupOrder = integer("group_order").nullable()
    val name = text("name").nullable()
    val duration = integer("duration").nullable()
    val creatureId = reference("creature_id", Creatures)
    val characterId = reference("character_id", Characters)
}

class CombatantDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CombatantDO>(Combatants)
    var type by Combatants.type
    var createdOn by Combatants.createdOn
    var updatedOn by Combatants.updatedOn
    var hitpoints by Combatants.hitpoints
    var maximumHitpoints by Combatants.maximumHitpoints
    var temporaryHitpoints by Combatants.temporaryHitpoints
    var temporaryMaximum by Combatants.temporaryMaximum
    var initiative by Combatants.initiative
    var groupId by Combatants.groupId
    var groupOrder by Combatants.groupOrder
    var name by Combatants.name
    var duration by Combatants.duration
    var creatureId = CreatureDO referencedOn Creatures.id
    var characterId = CharacterDO referencedOn Characters.id
}

object Creatures : IntIdTable("creatures") {
    val species = text("species")
    val averageHitpoints = integer("average_hitpoints")
    val hitpointDice = text("hitpoint_dice")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val official = bool("official")
}

class CreatureDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CreatureDO>(Creatures)
    var species by Creatures.species
    var averageHitpoints by Creatures.averageHitpoints
    var hitpointDice by Creatures.hitpointDice
    var createdOn by Creatures.createdOn
    var updatedOn by Creatures.updatedOn
    var official by Creatures.official
}

object Characters : IntIdTable("characters") {
    val name = text("name")
    val species = text("species")
    val subSpecies = text("sub_species")
    val classByline = text("class_byline")
    val avatarUrl = text("avatar_url")
    val hitpoints = integer("hitpoints")
    val armorClass = integer("armor_class")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val accountId = reference("account_id", Accounts)
}

class CharacterDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CharacterDO>(Characters)
    var name by Characters.name
    var species by Characters.species
    var subSpecies by Characters.subSpecies
    var classByline by Characters.classByline
    var avatarUrl by Characters.avatarUrl
    var hitpoints by Characters.hitpoints
    var armorClass by Characters.armorClass
    var createdOn by Characters.createdOn
    var updatedOn by Characters.updatedOn
    var accountId = AccountDO referencedOn Accounts.id
}
