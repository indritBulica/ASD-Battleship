package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:h2:mem:battleshipDB";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    private static final Logger logger = Logger.getLogger(Main.class);


    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Main started");

        initializeDB();
        Game game = new Game(primaryStage);
    }


    private void executeDBQuery(Connection connection, String statement) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(statement);
        stmt.close();
        logger.debug("Executed SQL Query");
    }

    private void initializeDB() {
        Connection conn = null;
        try {
            //STEP 2: Open a connection
            logger.info("Initializing database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            executeDBQuery(conn, "CREATE TABLE IF NOT EXISTS BShips " +
                    "(bid INTEGER not NULL, " +
                    " source VARCHAR(255), " +
                    " PRIMARY KEY ( bid ))");
            //STEP 3: Execute a query
            executeDBQuery(conn, "CREATE TABLE IF NOT EXISTS ImageShip " +
                    "(iid INTEGER not NULL, " +
                    " bShipId INTEGER, " +
                    " x INTEGER, " +
                    " y INTEGER, " +
                    " length INTEGER, " +
                    " PRIMARY KEY ( iid ), " +
                    " FOREIGN KEY ( bShipId ) REFERENCES BShips( bid ))");

            // STEP 4: Clean-up environment
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            logger.error(se.getMessage());
            logger.debug(se.getStackTrace());
        } catch (Exception e) {
            //Handle errors for Class.forName
            logger.error(e.getMessage());
            logger.debug(e.getStackTrace());
        } finally {
            //finally block used to close resources
            try {
                if (conn != null) conn.close();
                logger.debug("Closed Database.");
            } catch (SQLException se) {
                logger.error(se.getMessage());
                logger.debug(se.getStackTrace());
            } //end finally try
        } //end try
    }


}