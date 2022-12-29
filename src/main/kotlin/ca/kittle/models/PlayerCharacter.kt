package ca.kittle.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime


data class PlayerCharacter (
    val name: String,
    val level: Int,
    val species: String,
    val gender: String,
    val subSpecies: String,
    val inspiration: Boolean,
    val classByline: String,
    val hitpoints: Int,
    val avatarUrl: String,
    val userName: String,
    val accountId: Int
)


object Characters : IntIdTable("characters") {
    val name = text("name")
    val level = integer("level")
    val species = text("species")
    val gender = text("gender")
    val subSpecies = text("sub_species")
    val inspiration = bool("inspiration")
    val classByline = text("class_byline")
    val hitpoints = integer("hitpoints")
    val avatarUrl = text("avatar_url")
    val userName = text("user_name")
    val accountId = reference("account_id", Accounts)
}

class CharacterDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CharacterDO>(Characters)
    var name by Characters.name
    var level by Characters.level
    var species by Characters.species
    var gender by Characters.gender
    var subSpecies by Characters.subSpecies
    var inspiration by Characters.inspiration
    var classByline by Characters.classByline
    var hitpoints by Characters.hitpoints
    var avatarUrl by Characters.avatarUrl
    var userName by Characters.userName
    var accountId = AccountDO referencedOn Accounts.id
}


object CharacterOrigins : IntIdTable("character_origins") {
    val originId = integer("origin_id")
    val originName = text("origin_name")
    val playerName = text("player_name")
    val characterId = reference("character_id", Characters)
}

class CharacterOriginsDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CharacterOriginsDO>(CharacterOrigins)
    var originId by CharacterOrigins.originId
    var originName by CharacterOrigins.originName
    var playerName by CharacterOrigins.playerName
    var characterId by CharacterDO referencedOn CharacterOrigins.characterId
}
