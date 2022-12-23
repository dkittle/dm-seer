package ca.kittle.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class Account(
    val username: String,
    val password: String,
    val email: String,
    val active: Boolean
)

@Serializable
data class NewAccount(val username: String, val password: String, val email: String)

@Serializable
data class Credentials(val username: String, val password: String)

@Serializable
data class AccountUsername(val username: String)


object Accounts : IntIdTable("accounts") {
    val username = text("username").uniqueIndex()
    val password = text("password")
    val email = text("email").uniqueIndex()
    val active = bool("active")
    val createdOn = datetime("created_on")
    val lastLogin = datetime("last_login")
}

class AccountDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<AccountDO>(Accounts)
    var username by Accounts.username
    var password by Accounts.password
    var email by Accounts.email
    var active by Accounts.active
    var createdOn by Accounts.createdOn
    var lastLogin by Accounts.lastLogin
}
