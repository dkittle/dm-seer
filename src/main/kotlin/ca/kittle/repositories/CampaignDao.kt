package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import ca.kittle.models.integrations.DdbCampaign
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import java.util.regex.Pattern

object CampaignDao {
    private val logger = KotlinLogging.logger {}

    suspend fun campaign(campaign: Campaign, accountId: Int): Int = dbQuery {
        val old = Campaigns.select { Campaigns.id eq campaign.id }
            .mapNotNull { it }
            .singleOrNull()
            ?: return@dbQuery Campaigns.insertAndGetId {
                    it[name] = campaign.name
                    it[splashUrl] = campaign.splashUrl
                    it[description] = campaign.description
                    it[publicNotes] = campaign.publicNotes
                    it[privateNotes] = campaign.privateNotes
                    it[official] = campaign.official
                    it[createdOn] = Database.now
                    it[updatedOn] = Database.now
                    it[dmId] = accountId
                }.value
        return@dbQuery Campaigns.update( { Campaigns.id eq campaign.id } ) {
            it[name] = campaign.name
            it[splashUrl] = campaign.splashUrl
            it[description] = campaign.description
            it[publicNotes] = campaign.publicNotes
            it[privateNotes] = campaign.privateNotes
            it[official] = campaign.official
            it[updatedOn] = Database.now
        }
    }

    suspend fun findCampaignIdByOrigin(originId: Int): Int? = dbQuery {
        CampaignOriginDO.find { CampaignOrigins.originId eq originId }.firstOrNull()?.campaignId?.id?.value
    }

    /**
     * These functions cache data from DDB
     */
    suspend fun cacheCampaigns(campaigns: List<DdbCampaign>, accountId: Int) {
        campaigns.forEach { campaign ->
            val i = campaignFromDdb(campaign, accountId)
            if (i > 0)
                campaignOrigin(i, campaign.id.toInt())
        }
    }

    suspend fun campaignFromDdb(campaign: DdbCampaign, accountId: Int): Int = dbQuery {
        return@dbQuery CampaignOrigins.select { CampaignOrigins.originId eq campaign.id.toInt() }
            .map { it[CampaignOrigins.campaignId].value }.singleOrNull()
            ?: Campaigns.insertAndGetId {
                it[name] = campaign.name
                it[splashUrl] = campaign.splashUrl
                it[description] = ""
                it[publicNotes] = ""
                it[privateNotes] = ""
                it[official] = null
                it[createdOn] = parseDdbDate(campaign.dateCreated)
                it[updatedOn] = Database.now
                it[dmId] = accountId
            }.value
    }


    suspend fun campaignOrigin(newCampaignId: Int, vttId: Int) = dbQuery {
        CampaignOrigins.select { CampaignOrigins.campaignId eq newCampaignId }
            .mapNotNull { it }
            .singleOrNull()
            ?: CampaignOrigins.insert {
                it[originId] = vttId
                it[originName] = "DDB"
                it[campaignId] = newCampaignId
            }
    }

    private fun parseDdbDate(date: String): LocalDateTime {
        logger.debug { "Date to parse $date" }
        val m = Pattern.compile("(\\d*?)/(\\d*?)/(\\d*)").matcher(date)
        if (m.find()) {
            logger.debug { "Let's do ${m.group(3)}-${("0" + m.group(1)).takeLast(2)}-${("0" + m.group(2)).takeLast(2)}"}
            return "${m.group(3)}-${("0" + m.group(1)).takeLast(2)}-${("0" + m.group(2)).takeLast(2)}T12:00:00".toLocalDateTime()
        }
        return Database.now
    }

}
