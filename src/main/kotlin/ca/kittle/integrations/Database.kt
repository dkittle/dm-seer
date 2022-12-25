package ca.kittle.integrations

import ca.kittle.models.*
import ca.kittle.util.EnvUtil
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.*
import mu.KotlinLogging
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object Database {

    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    private val logger = KotlinLogging.logger {}
    val DATABASE_ERROR = "Error with data storage"

    fun init() {
        Database.connect(hikari())
    }

    fun initDb() {
        logger.info {"Initializing database" }
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Accounts, VttAccounts, Campaigns, CampaignOrigins, Encounters, Locations, Rooms, DndSources)
            commit()
            logger.debug {"Schema created" }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = jdbcConnectString()
        config.username = username
        config.password = password
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}

fun jdbcConnectString(): String {
    return "jdbc:postgresql://$hostname:$port/$db"
}

private val password: String
    get() = EnvUtil.stringFromEnvironment("RDS_PASSWORD", "")
private val username: String
    get() = EnvUtil.stringFromEnvironment("RDS_USERNAME", "postgres")
private val db: String
    get() = EnvUtil.stringFromEnvironment("RDS_DB_NAME", "dmseer")
private val port: String
    get() = EnvUtil.stringFromEnvironment("RDS_PORT", "5432")
private val hostname: String
    get() = EnvUtil.stringFromEnvironment("RDS_HOSTNAME", "localhost")

