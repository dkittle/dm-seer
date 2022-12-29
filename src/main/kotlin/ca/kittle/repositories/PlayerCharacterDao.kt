package ca.kittle.repositories

import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.CampaignOrigins
import ca.kittle.models.CharacterOrigins
import ca.kittle.models.Characters
import ca.kittle.models.integrations.encounter.Player
import mu.KotlinLogging
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select

object PlayerCharacterDao {
    private val logger = KotlinLogging.logger {}


    //    suspend fun playerCharacterOrigin(ddbCharacterId: Int, accountId: Int) = {
//
//    }
    suspend fun playerCharacterFromDdb(ch: Player, userAccountId: Int): Int = dbQuery {
        return@dbQuery Characters.insertAndGetId {
            it[name] = ch.name ?: "Hero yet to become one"
            it[level] = ch.level ?: 0
            it[species] = ch.species ?: "unknown"
            it[gender] = ch.gender ?: "unkonwn"
            it[subSpecies] = "unknown"
            it[inspiration] = false
            it[classByline] = ch.classByLine ?: "unknown"
            it[hitpoints] = ch.currentHitPoints ?: 0
            it[avatarUrl] = ch.avatarUrl ?: ""
            it[userName] = ch.userName ?: ""
            it[accountId] = userAccountId
        }.value
    }

    suspend fun characterOrigin(newCharacterId: Int, ddbPlayerName: String, vttId: Int) = dbQuery {
        CharacterOrigins.select { CharacterOrigins.characterId eq newCharacterId }
            .mapNotNull { it }
            .singleOrNull()
            ?: CharacterOrigins.insert {
                it[originId] = vttId
                it[originName] = "DDB"
                it[playerName] = ddbPlayerName
                it[characterId] = newCharacterId
            }
    }

}

