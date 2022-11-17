package ca.kittle.integrations;

import ca.kittle.util.EnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static ca.kittle.util.EnvUtil.stringFromEnvironment;

public class DbConnection {

    private static final Logger logger = LoggerFactory.getLogger(DbConnection.class);

    private static Connection connection;
    public static Statement statement;


    public Statement connect() {
        String url = "jdbc:postgresql://172.31.38.225:5432/messages";

        try {
            connection = DriverManager.getConnection(url,"postgress","Garibaldi!");
            logger.info("Connected to Postgres");
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }

    public void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem releasing statement", e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Problem releasing connection", e);
        }
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
        return stringFromEnvironment("RDS_USERNAME", "dm-seer-app");
    }

    private String getDb() {
        return stringFromEnvironment("RDS_DB_NAME", "dm-seer");
    }

    private String getPort() {
        return stringFromEnvironment("RDS_PORT", "5432");
    }

    private String getHostname() {
        return stringFromEnvironment("RDS_HOSTNAME", "localhost");
    }


    record DbConfig(String host, String port, String db, String username, String password) {
        public String jdbcConnectString() {
            return "jdbc:postgresql://" +
                    host +
                    ":" +
                    port +
                    "/" +
                    db;
        }
    }

}
