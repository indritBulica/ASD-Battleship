package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class);
    Database database;



    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Starting game");
        database = new Database();
        database.executeQuery("CREATE TABLE IF NOT EXISTS BShips " +
                "(bid INTEGER not NULL, " +
                " source VARCHAR(255), " +
                " PRIMARY KEY ( bid ))");

        new Game(primaryStage, database);
    }



}