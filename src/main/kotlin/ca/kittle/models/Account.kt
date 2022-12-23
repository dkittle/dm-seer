package ca.kittle.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class Account(
    val id: Int,
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

data class VttAccount(val id: Int, val accountId: Int, val vttName: String, val vttId: Int, val vttKey: String?)

@Serializable
data class NewVttAccount(val vttName: String, val vttId: Int, val vttKey: String?)

data class UserSession(val accountId: Int, val username: String, val vttId: Int?, val vttKey: String?)

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

object VttAccounts : IntIdTable("vtt_accounts") {
    val vttName = text("vtt_name").uniqueIndex()
    val vttId = integer("vtt_id")
    val vttKey = text("vtt_key").nullable()
    val account = reference("account_id", Accounts)
}

class VttAccountDO (id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<VttAccountDO>(VttAccounts)
    var vttName by VttAccounts.vttName
    var vttId by VttAccounts.vttId
    var vttKey by VttAccounts.vttKey
    var account by AccountDO referencedOn VttAccounts.account
}
