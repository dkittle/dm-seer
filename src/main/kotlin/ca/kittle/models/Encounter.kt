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
    var sourcePage by Encounters.sourcePage
    var createdOn by Encounters.createdOn
    var updatedOn by Encounters.updatedOn
    var accountId = AccountDO referencedOn Accounts.id
    var campaignId = CampaignDO referencedOn Campaigns.id
    var locationId = LocationDO referencedOn Locations.id
    var roomId = RoomDO referencedOn Rooms.id
    var sourceId = DndSourceDO referencedOn DndSources.id
}
