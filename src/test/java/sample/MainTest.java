package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void launch() {
    }

    @Test
    void testLaunch() {
    }

    @Test
    void init() {
    }

    @Test
    void stop() {
    }

    @Test
    void getHostServices() {
    }

    @Test
    void getParameters() {
    }

    @Test
    void notifyPreloader() {
    }

    @Test
    void getUserAgentStylesheet() {
    }

    @Test
    void setUserAgentStylesheet() {
    }

    @Test
    void start() {

    }

    @Test
    void executeDBQuery() throws SQLException {
        Connection connection;
        String statement = null;
        String DB_URL = "jdbc:h2:mem:battleshipDB";
        String USER = "sa";
        connection = DriverManager.getConnection(DB_URL, USER, "");
        executeDBQuery(connection, "CREATE TABLE IF NOT EXISTS BShips " +
                "(bid INTEGER not NULL, " +
                " source VARCHAR(255), " +
                " PRIMARY KEY ( bid ))");
        assertNotNull(connection);
        connection.close();
    }
    @Test
    private void executeDBQuery(Connection connection, String statement) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(statement);
        stmt.close();
    }
}