package ca.kittle.models

import ca.kittle.models.Encounters.nullable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Locations : IntIdTable("locations") {
    val name = text("name")
    val description = text("description")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val accountId = reference("account_id", Accounts)
    val campaignId = reference("campaign_id", Campaigns).nullable()
}


class LocationDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<LocationDO>(Locations)
    var name by Locations.name
    var description by Locations.description
    var createdOn by Locations.createdOn
    var updatedOn by Locations.updatedOn
    var accountId = AccountDO referencedOn Accounts.id
    var campaignId = CampaignDO referencedOn Campaigns.id
}

object Rooms : IntIdTable("rooms") {
    val name = text("name")
    val description = text("description")
    val createdOn = datetime("created_on")
    val updatedOn = datetime("updated_on")
    val accountId = reference("account_id", Accounts)
    val locationId = reference("location_id", Locations).nullable()
}


class RoomDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<RoomDO>(Rooms)
    var name by Rooms.name
    var description by Rooms.description
    var createdOn by Rooms.createdOn
    var updatedOn by Rooms.updatedOn
    var accountId = AccountDO referencedOn Accounts.id
    var locationId = LocationDO referencedOn Locations.id
}

