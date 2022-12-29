package ca.kittle.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

@Serializable
data class SourceReference(val source: String, val pageNumber: Int)


object DndSources : IntIdTable("sources") {
    val label = text("label")
    val description = text("description")
    val avatarUrl = text("avatar_url")
    val official = bool("official")
    val accountId = reference("account_id", Accounts)
}


class DndSourceDO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<DndSourceDO>(DndSources)
    var label by DndSources.label
    var description by DndSources.description
    var avatarUrl by DndSources.avatarUrl
    var official by DndSources.official
    var accountId = AccountDO referencedOn Accounts.id
}
