package ca.kittle.integrations

import ca.kittle.util.EnvUtil
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Database {
    val DATABASE_ERROR = "Error with data storage"

    @Throws(SQLException::class)
    fun connect(): Connection {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = jdbcConnectString()
        dataSource.username = username
        dataSource.password = password
        return dataSource.connection
    }

    fun jdbcConnectString(): String {
        return "jdbc:postgresql://$hostname:$port/$db"
    }

    private val password: String
        private get() = EnvUtil.stringFromEnvironment("RDS_PASSWORD", "")
    private val username: String
        private get() = EnvUtil.stringFromEnvironment("RDS_USERNAME", "postgres")
    private val db: String
        private get() = EnvUtil.stringFromEnvironment("RDS_DB_NAME", "dmseer")
    private val port: String
        private get() = EnvUtil.stringFromEnvironment("RDS_PORT", "5432")
    private val hostname: String
        private get() = EnvUtil.stringFromEnvironment("RDS_HOSTNAME", "localhost")

}
