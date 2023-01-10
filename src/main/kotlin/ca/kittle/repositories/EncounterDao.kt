package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import ca.kittle.models.integrations.encounter.Monster
import ca.kittle.util.isPositiveInteger
import kotlinx.datetime.*
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


object EncounterDao {
    private val logger = KotlinLogging.logger {}


//    suspend fun encounter(id: Int, userId: Int): Encounter? = dbQuery {
//        logger.debug { "Retrieving a specific encounter" }
//        val encounter = Encounters.innerJoin(Accounts).innerJoin(Campaigns).innerJoin(Locations).innerJoin(Rooms).innerJoin(DndSources)
//            .slice(Encounters.id, Encounters.name, Encounters.suggestedAcl, Encounters.description, Encounters.private,
//                Encounters.dmgDifficulty, Encounters.sfDifficulty, Encounters.dceDifficulty, Encounters.dcDifficulty,
//                Encounters.luDifficulty, Encounters.official, Encounters.sourcePage, Encounters.createdOn, Encounters.updatedOn,
//                Accounts.username, Campaigns.name, Locations.name, Rooms.name, DndSources.label)
//            .select{ Encounters.id eq id }
//            .andWhere { Encounters.accountId eq userId or not(Encounters.private) }
//            .map { dtoToEncounter(it) }.singleOrNull()
//        return@dbQuery encounter
//    }

    suspend fun encounter(id: Int, userId: Int): Encounter? = dbQuery {
        logger.debug { "Retrieving a specific encounter" }
        val encounter = Encounters
            .join(Accounts, JoinType.LEFT, additionalConstraint = { Accounts.id eq Encounters.accountId })
            .join(Campaigns, JoinType.LEFT, additionalConstraint = { Campaigns.id eq Encounters.campaignId })
            .join(Locations, JoinType.LEFT, additionalConstraint = { Locations.id eq Encounters.locationId })
            .join(Rooms, JoinType.LEFT, additionalConstraint = { Rooms.id eq Encounters.roomId })
            .join(DndSources, JoinType.LEFT, additionalConstraint = { DndSources.id eq Encounters.sourceId })
            .slice(Encounters.id, Encounters.name, Encounters.suggestedAcl, Encounters.description, Encounters.private,
                Encounters.dmgDifficulty, Encounters.sfDifficulty, Encounters.dceDifficulty, Encounters.dcDifficulty,
                Encounters.luDifficulty, Encounters.official, Encounters.sourcePage, Encounters.createdOn, Encounters.updatedOn,
                Accounts.username, Campaigns.name, Locations.name, Rooms.name, DndSources.label)
            .select{ Encounters.id eq id }
            .andWhere { Encounters.accountId eq userId or not(Encounters.private) }
            .map { dtoToEncounter(it) }.singleOrNull() ?: return@dbQuery null
        return@dbQuery encounter.copy(creatures = countCritters(encounter.id))
    }

    suspend fun encounters(userId: Int): List<Encounter>? = dbQuery {
        logger.debug { "Retrieving encounters for a user" }
        val encounters = Encounters
            .join(Accounts, JoinType.LEFT, additionalConstraint = { Accounts.id eq Encounters.accountId })
            .join(Campaigns, JoinType.LEFT, additionalConstraint = { Campaigns.id eq Encounters.campaignId })
            .join(Locations, JoinType.LEFT, additionalConstraint = { Locations.id eq Encounters.locationId })
            .join(Rooms, JoinType.LEFT, additionalConstraint = { Rooms.id eq Encounters.roomId })
            .join(DndSources, JoinType.LEFT, additionalConstraint = { DndSources.id eq Encounters.sourceId })
            .slice(Encounters.id, Encounters.name, Encounters.suggestedAcl, Encounters.description, Encounters.private,
                Encounters.dmgDifficulty, Encounters.sfDifficulty, Encounters.dceDifficulty, Encounters.dcDifficulty,
                Encounters.luDifficulty, Encounters.official, Encounters.sourcePage, Encounters.createdOn, Encounters.updatedOn,
                Accounts.username, Campaigns.name, Locations.name, Rooms.name, DndSources.label)
            .selectAll()
            .andWhere { Encounters.accountId eq userId or not(Encounters.private) }
            .orderBy( Encounters.private to SortOrder.DESC )
            .orderBy( Encounters.name to SortOrder.ASC )
            .map { dtoToEncounter(it) }
        return@dbQuery encounters.map { it.copy(creatures = countCritters(it.id)) }
    }

    fun countCritters(id: Int): Int =
        Encounterees.slice(Encounterees.name.count()).select{ Encounterees.encounterId eq id }
            .map { it[Encounterees.name.count()].toInt()}.singleOrNull() ?: 0

    private fun dtoToEncounter(row: ResultRow): Encounter =
        Encounter(row[Encounters.id].value,
            row[Encounters.name],
            row[Encounters.suggestedAcl],
            row[Encounters.description],
            row[Encounters.private],
            0,
            row[Encounters.dmgDifficulty],
            row[Encounters.sfDifficulty],
            row[Encounters.dceDifficulty],
            row[Encounters.dcDifficulty],
            row[Encounters.luDifficulty],
            row[Encounters.official],
            row[Encounters.sourcePage],
            row[Encounters.createdOn],
            row[Encounters.updatedOn],
            row[Accounts.username] ?: "",
            row[Campaigns.name] ?: "",
            row[Locations.name] ?: "",
            row[Rooms.name] ?: "",
            row[DndSources.label] ?: ""
        )




    /**
     * These functions cache data from DDB
     */
    suspend fun cacheEncountersFromDdb(ddbEncounters: List<ca.kittle.models.integrations.encounter.Encounter>, accountId: Int) {
        ddbEncounters.forEach { encounter ->
            val newCampaignId = encounter.campaign?.id?.let { CampaignDao.findCampaignIdByOrigin(it) }
            // Creates the encounter and returns the id or new or existing encounter
            logger.debug { "Found an existing campaign $newCampaignId for ${encounter.name}" }
            val encounterId = encounterFromDdb(encounter, newCampaignId, accountId)
            val groups = mutableMapOf<String, Int>()
            val characters = mutableMapOf<Int, Int>()
            val creatures = mutableMapOf<Int, Int>()
            groupsWithMultipleMonsters(encounter).forEach{ groups[it] = 1 }
            if (encounterId > 0) {
                encounterOrigin(encounterId, encounter.id)
                // create combat
                combatFromDdb(encounter, encounterId)
                // cache players
                encounter.players.forEach {
                    // Fake party members who are just a level have a text based id, so we can skip them.
                    if (it.id.isPositiveInteger()) {
                        val id = PlayerCharacterDao.playerCharacterFromDdb(it, accountId)
                        characters[it.id.toInt()] = id
                        PlayerCharacterDao.characterOrigin(id, it.userName ?: "Unnamed explorer", it.id.toInt())
                    }
                }
                // create encounterees
                encounter.monsters.forEach {
                    val creatureId = CreatureDao.getCachedCreatureId(it.id)
                    if (creatureId != null) {
                        if (it.groupId in groups.keys) {
                            val i = groups[it.groupId] ?: 1
                            encountereeFromDdb(it, creatureId, encounterId, i)
                            groups[it.groupId] = i + 1
                        } else
                            encountereeFromDdb(it, creatureId, encounterId, null)
                    }
                    else
                        logger.warn { "Skipping creature ${it.name} as an encounteree due to not being cached."}
                }
            }
        }
    }

    private suspend fun encounterFromDdb(encounter: ca.kittle.models.integrations.encounter.Encounter, newCampaignId: Int?, userId: Int): Int = dbQuery {
        val found = Encounters.innerJoin(EncounterOrigins)
            .slice(Encounters.columns) // only select the Actors columns
            .select { EncounterOrigins.originId eq  encounter.id }
            .mapNotNull { it[Encounters.id].value }
            .singleOrNull()
                ?: return@dbQuery Encounters.insertAndGetId {
                    it[name] = encounter.name ?: "Unnamed mayhem"
                    it[suggestedAcl] = 0
                    it[description] = encounter.description ?: ""
                    it[private] = true
                    it[dmgDifficulty] = encounter.difficulty ?: 0
                    it[sfDifficulty] = 0
                    it[dceDifficulty] = 0
                    it[dcDifficulty] = 0
                    it[luDifficulty] = 0
                    it[official] = false
                    it[sourcePage] = null
                    it[createdOn] = encounterDate(encounter.dateCreated).toLocalDateTime(TimeZone.UTC)
                    it[updatedOn] = Database.now
                    it[accountId] = userId
                    it[campaignId] = newCampaignId
                    it[locationId] = null
                    it[roomId] = null
                    it[sourceId] = null
                }.value
        return@dbQuery found
    }

    private suspend fun encountereeFromDdb(creature: Monster, newCreatureId: Int, encId: Int, index: Int?) = dbQuery {
        Encounterees.insertAndGetId {
            it[name] = creature.name ?: "Unnamed nasty"
            it[type] = "CREATURE"
            it[hitpoints] = creature.currentHitPoints
            it[groupId] = if (index == null) null else creature.groupId
            it[groupOrder] = index
            it[createdOn] = Database.now
            it[updatedOn] = Database.now
            it[encounterId] = encId
            it[creatureId] = newCreatureId
        }
    }


    private suspend fun combatFromDdb(encounter: ca.kittle.models.integrations.encounter.Encounter, newEncounterId: Int): Int = dbQuery {
        Combats.insertAndGetId {
            it[inProgress] = encounter.inProgress ?: false
            it[roundNumber] = encounter.roundNum ?: 0
            it[turnNumber] = encounter.turnNum ?: 0
            it[createdOn] = Database.now
            it[updatedOn] = Database.now
            it[encounterId] = newEncounterId
        }.value
    }
    suspend fun encounterOrigin(newEncounterId: Int, vttId: String) = dbQuery {
        EncounterOrigins.select { EncounterOrigins.encounterId eq newEncounterId }
            .mapNotNull { it }
            .singleOrNull()
            ?: EncounterOrigins.insert {
                it[originId] = vttId
                it[encounterId] = newEncounterId
            }
    }

    private fun groupsWithMultipleMonsters(encounter: ca.kittle.models.integrations.encounter.Encounter): List<String> =
        encounter.monsters.filter { it.quantity > 1 }.map { it.groupId }

    private fun encounterDate(millis: Long?): Instant =
        if(millis == null)
            Clock.System.now()
        else
            Instant.fromEpochMilliseconds(millis)


}
