package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import ca.kittle.models.integrations.encounter.Monster
import kotlinx.datetime.*
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*


object EncounterDao {
    private val logger = KotlinLogging.logger {}


//    fun encounter(id: Long, accountId: Long?): Encounter? {
//        logger.debug { "Retrieving a specific encounter" }
//        db.connect().use { conn ->
//            conn.createStatement().use { stmt ->
//                stmt.executeQuery(encounterQuery(1, id)).use { rs ->
//                    while (rs.next())
//                        return readEncounter(rs)
//                }
//            }
//        }
//        return null
//    }
//
//    fun encounters(accountId: Long?): List<Encounter> {
//        logger.debug { "Retrieving all encounters for current user." }
//        val result = arrayListOf<Encounter>()
//        db.connect().use { conn ->
//            conn.createStatement().use { stmt ->
//                stmt.executeQuery(encounterQuery(null, accountId)).use { rs ->
//                    while (rs.next())
//                        result.add(readEncounter(rs))
//                }
//            }
//        }
//        return result
//    }
//
//    fun readEncounter(rs: ResultSet): Encounter {
//        return Encounter(rs.getLong("id"),
//                rs.getString("name"),
//                rs.getString("dm_name"),
//                rs.getString("campaign_name"),
//                rs.getLong("locationId"),
//                rs.getString("location"),
//                rs.getString("title"),
//                rs.getInt("suggested_acl"));
//    }
//
//    companion object {
//        fun encounterQuery(id: Long? = null, accountId: Long? = null): String {
//            val specific = if (id != null) " e.id=$id" else ""
//            val account = if (accountId != null) " e.created_by=$accountId" else ""
//            val where = if (!specific.isEmpty() || !account.isEmpty()) "WHERE" else ""
//            val joiner = if (!specific.isEmpty() && !account.isEmpty()) "AND" else ""
//            return "SELECT e.*, c.name AS campaign, l.id AS location_id, l.name AS location, r.name AS room " +
//                    "ac.username AS dm_name, s.title " +
//                    "FROM encounters e " +
//                    "LEFT OUTER JOIN accounts ac ON e.dm_id = ac.id " +
//                    "LEFT OUTER JOIN campaigns c ON e.campaign_id = c.id " +
//                    "LEFT OUTER JOIN locations l ON e.location_id = l.id " +
//                    "LEFT OUTER JOIN rooms r ON e.parent_location_id = r.id " +
//                    "LEFT OUTER JOIN sources s ON e.source_id = s.id " +
//                    "$where $account $joiner ${specific}";
//        }
//    }


    /**
     * These functions cache data from DDB
     */
    suspend fun cacheEncountersFromDdb(ddbEncounters: List<ca.kittle.models.integrations.encounter.Encounter>, accountId: Int) {
        ddbEncounters.forEach { encounter ->
            val encounterCampaign = encounter.campaign?.id
            val newCampaignId = if (encounterCampaign != null) CampaignDao.findCampaignIdByOrigin(encounterCampaign) else  null
            // Creates the encounter and returns the id or new or existing encounter
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
                    val id = PlayerCharacterDao.playerCharacterFromDdb(it, accountId)
                    characters[it.id.toInt()] = id
                    PlayerCharacterDao.characterOrigin(id, it.userName, it.id.toInt())
                }
                // cache monsters
//                encounter.monsters.forEach {
//                    val id = CreatureDao.creatureFromDdb(it, accountId)
//                    creatures[it.id] = id
//                    CreatureDao.creatureOrigin(id, it.id)
//                }
                //create encounterees
                encounter.monsters.forEach {
                    if (it.groupId in groups.keys) {
                        val i = groups[it.groupId] ?: 1
                        encountereeFromDdb(it, 0, i)
                        groups[it.groupId] = i + 1
                    }
                    else
                        encountereeFromDdb(it, 0,null)
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
                    it[name] = encounter.name
                    it[suggestedAcl] = 0
                    it[description] = encounter.description ?: ""
                    it[private] = true
                    it[dmgDifficulty] = encounter.difficulty ?: 0
                    it[sfDifficulty] = 0
                    it[dceDifficulty] = 0
                    it[dcDifficulty] = 0
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

    private suspend fun encountereeFromDdb(creature: Monster, newCreatureId: Int, index: Int?) = dbQuery {
        Encounterees.insertAndGetId {
            it[name] = creature.name
            it[type] = "CREATURE"
            it[hitpoints] = creature.currentHitPoints
            it[groupId] = if (index == null) null else creature.groupId
            it[groupOrder] = index
            it[createdOn] = Database.now
            it[updatedOn] = Database.now
            it[creatureId] = newCreatureId
        }
    }

//    private suspend fun combatantFromDdb(creature: Monster, combatantType: String, index: Int?) = dbQuery {
//        Combatants.insertAndGetId {
//            it[name] = creature.name
//            it[type] = combatantType
//            it[initiative] = creature.initiative
//            it[hitpoints] = creature.currentHitPoints
//            it[maximumHitpoints] = creature.maximumHitPoints
//            it[temporaryHitpoints] = creature.temporaryHitPoints
//            it[temporaryMaximum] = creature.maximumHitPoints
//            it[groupId] = if (index == null) null else creature.groupId
//            it[groupOrder] = index
//            it[duration] = null
//            it[createdOn] = Database.now
//            it[updatedOn] = Database.now
//            it[creatureId] = newCreatureId
//            it[characterId] = null
//        }
//    }
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
