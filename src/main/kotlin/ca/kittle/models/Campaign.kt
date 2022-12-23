package ca.kittle.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Campaign(
    val id: Long,
    val name: String,
    val dmName: String,
    val splashUrl: String,
    val description: String,
    val publicNotes: String,
    val privateNotes: String,
    val official: Boolean,
    val source: String)

object Campaigns : IntIdTable("campaigns") {
    val name = varchar("name", 200)
    val splashUrl = varchar("splash_url", 255)
    val description = text("description")
    val publicNotes = text("public_notes")
    val privateNotes = text("private_notes")
    val official = bool("official")
    val dmId = reference("dm_id", Accounts)
}

class CampaignDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CampaignDO>(Campaigns)
    var name by Campaigns.name
    var splashUrl by Campaigns.splashUrl

//        var account by Account referencedOn VttAccounts.account
}
