package ca.kittle.models

import ca.kittle.models.Campaigns.nullable
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class Encounter(
    var id: Int,
    var name: String,
    var suggestedAcl: Int,
    var description: String,
    var private: Boolean,
    var dmgDifficulty: Int,
    var sfDifficulty: Int,
    var dceDifficulty: Int,
    var dcDifficulty: Int,
    var official: Boolean,
    var sourcePageNumber: Int,
    var createdOn: LocalDateTime,
    var updatedOn: LocalDateTime,
    var dmName: String,
    var campaign: String,
    var location: String,
    var room: String,
    var source: String
)


object Encounters : IntIdTable("encounters") {
    val name = text("name")
    val description = text("description")
    val private = bool("private")
    val suggestedAcl = integer("suggested_acl")
    val dmgDifficulty = integer("dmg_difficulty")
    val sfDifficulty = integer("sf_difficulty")
    val dceDifficulty = integer("dce_difficulty")
    val dcDifficulty = integer("dc_difficulty")
    val official = bool("official")
    val sourcePage = integer("source_page").nullable()
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
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
    var private by Encounters.private
    var suggestedAcl by Encounters.suggestedAcl
    var dmgDifficulty by Encounters.dmgDifficulty
    var official by Encounters.official
    var sourcePage by Encounters.sourcePage
    var createdOn by Encounters.createdOn
    var updatedOn by Encounters.updatedOn
    var accountId = AccountDO referencedOn Encounters.accountId
    var campaignId = CampaignDO optionalReferencedOn Encounters.campaignId
    var locationId = LocationDO optionalReferencedOn Encounters.locationId
    var roomId = RoomDO optionalReferencedOn Encounters.roomId
    var sourceId = DndSourceDO optionalReferencedOn Encounters.sourceId
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
object Encounterees : IntIdTable("encounterees") {
    val name = text("name").nullable()
    val type = text("type")
    val hitpoints = integer("hitpoints")
    val groupId = text("group_id").nullable()
    val groupOrder = integer("group_order").nullable()
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val creatureId = reference("creature_id", Creatures).nullable()
}

class EncountereeDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<EncountereeDO>(Encounterees)
    var name by Encounterees.name
    var type by Encounterees.type
    var hitpoints by Encounterees.hitpoints
    var groupId by Encounterees.groupId
    var groupOrder by Encounterees.groupOrder
    var createdOn by Encounterees.createdOn
    var updatedOn by Encounterees.updatedOn
    var creatureId = CreatureDO optionalReferencedOn Encounterees.creatureId
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
    var encounterId = EncounterDO referencedOn Combats.encounterId
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
    val name = text("name").nullable()
    val type = text("type")
    val initiative = integer("initiative")
    val hitpoints = integer("hitpoints")
    val maximumHitpoints = integer("maximum_hitpoints")
    val temporaryHitpoints = integer("temporaryHitpoints")
    val temporaryMaximum = integer("temporary_maximum")
    val groupId = text("group_id").nullable()
    val groupOrder = integer("group_order").nullable()
    val duration = integer("duration").nullable()
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val creatureId = reference("creature_id", Creatures).nullable()
    val characterId = reference("character_id", Characters).nullable()
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
    var creatureId = CreatureDO optionalReferencedOn Combatants.creatureId
    var characterId = CharacterDO optionalReferencedOn Combatants.characterId
}

object EncounterOrigins : IntIdTable("encounter_origins") {
    val originId = text("origin_id")
    val encounterId = reference("encounter_id", Encounters)
}

class EncounterOriginDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<EncounterOriginDO>(EncounterOrigins)
    var originId by EncounterOrigins.originId
    var encounterId by EncounterDO referencedOn EncounterOrigins.encounterId
}
