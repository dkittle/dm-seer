package ca.kittle.integrations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static ca.kittle.util.EnvUtil.stringFromEnvironment;

public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    private final DbConfig dbConfig = getConfig();

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(jdbcConnectString(), username(), password());
    }

    public String jdbcConnectString() {
        return "jdbc:postgresql://" +
                dbConfig.host +
                ":" +
                dbConfig.port +
                "/" +
                dbConfig.db;
    }

    public String username() {
        return dbConfig.username;
    }

    public String password() {
        return dbConfig.password;
    }

    private DbConfig getConfig() {
        return new DbConfig(
                getHostname(),
                getPort(),
                getDb(),
                getUsername(),
                getPassword()
        );
    }

    private String getPassword() { return stringFromEnvironment("RDS_PASSWORD", ""); }

    private String getUsername() {
        return stringFromEnvironment("RDS_USERNAME", "postgres");
    }

    private String getDb() {
        return stringFromEnvironment("RDS_DB_NAME", "dmseer");
    }

    private String getPort() {
        return stringFromEnvironment("RDS_PORT", "5432");
    }

    private String getHostname() {
        return stringFromEnvironment("RDS_HOSTNAME", "localhost");
    }


    record DbConfig(String host, String port, String db, String username, String password) {
    }

}
