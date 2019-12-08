package sample;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // JDBC driver name and database URL. PLEASE change the path to your project!
    static final String DB_URL = "jdbc:h2:~/../../Development/FH/Battleship/battleshipDB";
    // Memory DB : "jdbc:h2:mem:battleshipDB";
    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    private static final Logger logger = Logger.getLogger(Database.class);
    Connection connection;
    Statement statement;

    public Database() {
        initializeDB();
    }

    private void initializeDB() {

        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("Initializing database..." + connection.getClientInfo() +", "+ connection.getSchema());
        } catch (SQLException se) {
            logger.error(se.getMessage());
        }
    }

        public void executeQuery (String query) throws SQLException {
            if (this.connection == null) {
                logger.error("Database not initialized!");
                throw new SQLException("Database not initialized");
            }
            try {
                this.statement = connection.createStatement();
                statement.execute(query);
            }finally {
                statement.close();
            }
            logger.debug("Executed SQL Query: '" + statement + "'");
        }
    }

