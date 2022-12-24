package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.Campaign
import ca.kittle.models.CampaignOrigins
import ca.kittle.models.Campaigns
import ca.kittle.models.VttAccounts
import ca.kittle.models.integrations.DdbCampaign
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*
import java.util.regex.Pattern

class CampaignRepository {
    private val logger = KotlinLogging.logger {}

    suspend fun cacheCampaigns(campaigns: List<DdbCampaign>, accountId: Int) {
        campaigns.forEach { campaign ->
            val i = campaignFromDdb(campaign, accountId)
            if (i > 0)
                campaignOrigin(i, campaign.id.toInt())
        }
    }

    suspend fun campaignFromDdb(campaign: DdbCampaign, accountId: Int): Int = dbQuery {
        CampaignOrigins.select { CampaignOrigins.originId eq campaign.id.toInt() }
            .mapNotNull { it }
            .singleOrNull()
            ?: return@dbQuery Campaigns.insertAndGetId {
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
        return@dbQuery 0
    }

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

    suspend fun campaignOrigin(campaignId: Int, vttId: Int): Int = dbQuery {
        CampaignOrigins.select { CampaignOrigins.campaign eq campaignId }
            .mapNotNull { it }
            .singleOrNull()
            ?: return@dbQuery CampaignOrigins.insertAndGetId {
                it[originId] = vttId
                it[originName] = "DDB"
                it[campaign] = campaignId
            }.value
        return@dbQuery 0
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
//    fun campaign(id: Long): Campaign? {
//        logger.info { "Get a specific campaign" }
//        val query = "SELECT * from campaigns where campaigns.id=?"
//
//        db.connect().use { conn ->
//            conn.prepareStatement(query).use { stmt ->
//                stmt.setString(1, id)
//                stmt.executeQuery().use { rs ->
//                    while(rs.next())
//                        return rs.getInt("total") == 0
//                }
//            }
//        }

//        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setLong(1, id);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next())
//                return Optional.of(new Campaign(rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getBoolean("official")));
//        } catch (SQLException e) {
//            logger.error("Problem getting campaign", e);
//        }
//        return Optional.empty()
//    }

//    fun campaignByName(name: String?): Optional<Campaign?>? {
//        CampaignRepository.logger.info("Get a specific campaign by name")
//        val query = "SELECT * from campaigns where LOWER(campaigns.name)=?"

//        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, name.toLowerCase());
//            ResultSet rs = statement.executeQuery();
//            if (rs.next())
//                return Optional.of(new Campaign(rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getBoolean("official")));
//        } catch (SQLException e) {
//            logger.error("Problem getting campaign", e);
//        }
//        return Optional.empty()
//    }

}
