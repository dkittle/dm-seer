package ca.kittle.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class Campaign(
    val id: Int,
    val name: String,
    val dmName: String,
    val splashUrl: String,
    val description: String,
    val publicNotes: String,
    val privateNotes: String,
    val official: Boolean,
    val source: String)

object Campaigns : IntIdTable("campaigns") {
    val name = text("name")
    val splashUrl = text("splash_url").nullable()
    val description = text("description").nullable()
    val publicNotes = text("public_notes").nullable()
    val privateNotes = text("private_notes").nullable()
    val official = bool("official").nullable()
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val dmId = reference("dm_id", Accounts)
}

class CampaignDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CampaignDO>(Campaigns)
    var name by Campaigns.name
    var splashUrl by Campaigns.splashUrl
    var description by Campaigns.description
    var publicNotes by Campaigns.publicNotes
    var privateNotes by Campaigns.privateNotes
    var official by Campaigns.official
    var createdOn by Campaigns.createdOn
    var updatedOn by Campaigns.updatedOn
    var dmId = AccountDO referencedOn Campaigns.dmId
}

object CampaignOrigins : IntIdTable("campaign_origins") {
    val originId = integer("origin_id")
    val originName = text("origin_name")
    val campaign = reference("campaign_id", Campaigns)
}

class CampaignOriginsDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<CampaignOriginsDO>(CampaignOrigins)
    var originId by CampaignOrigins.originId
    var originName by CampaignOrigins.originName
    var campaignId by CampaignDO referencedOn CampaignOrigins.campaign
}
