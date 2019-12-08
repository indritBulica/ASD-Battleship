package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;

public class Game {
    private static final Logger logger = Logger.getLogger(Game.class);
    private final Database database;
    Stage primaryStage;
    private Player player1 = new Player();
    private Player player2 = new Player();
    private double pressedX;
    private double pressedY;
    private int gameRound = 1;
    private boolean shipsComplete = false; //zu testzwecken auf true später muss auf false gestellt werden
    private Button buttonSaveShipsLeft = new Button("Schiffe speichern");
    private Button buttonSaveShipsRight = new Button("Schiffe Speichern");
    private Button buttonNewGame = new Button("Neues Spiel");
    private Button buttonExit = new Button("EXIT");
    private Button buttonReset = new Button("Neustart");
    private Button buttonSeeShips1 = new Button("Zeige meine Schiffe");
    private Button buttonSeeShips2 = new Button("Zeige meine Schiffe");
    private Button buttonContinue = new Button("Hier gehts weiter");
    private ImageView startmenu = new ImageView("file:res/start.png");
    private ImageView wonleft = new ImageView("file:res/spieler1_gewonnen.png");
    private ImageView wonright = new ImageView("file:res/spieler2_gewonnen.png");
    private ImageView maskleftfield = new ImageView("file:res/Insel_Unten_1.png");
    private ImageView maskrightfield = new ImageView("file:res/Insel_Unten_2.png");
    private Rectangle indicate1 = new Rectangle(439, 481, 442, 7);
    private Rectangle indicate2 = new Rectangle(919, 481, 442, 7);
    private Media bomb = new Media(new File("res/bomb.wav").toURI().toString());
    private MediaPlayer bombplay = new MediaPlayer(bomb);
    private Media miss = new Media(new File("res/miss.wav").toURI().toString());
    private MediaPlayer missplay = new MediaPlayer(miss);
    private Media music = new Media(new File("res/music.wav").toURI().toString());
    private MediaPlayer musicplay = new MediaPlayer(music);
    private Media winner = new Media(new File("res/winner.wav").toURI().toString());
    private MediaPlayer winnerplay = new MediaPlayer(winner);
    private Image[] bships = {
            new Image("file:res/1x2_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x3_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x4_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x5_Schiff_Horizontal_1_Fertig.png")
    };
    //Schiffe SPieler 1
    ImageShip[] imageShip1 = {
            new ImageShip(1520, 640, 2, bships[0]),
            new ImageShip(1520, 640, 2, bships[0]),
            new ImageShip(1520, 640, 2, bships[0]),
            new ImageShip(1520, 640, 2, bships[0]),
            new ImageShip(1520, 720, 3, bships[1]),
            new ImageShip(1520, 720, 3, bships[1]),
            new ImageShip(1520, 720, 3, bships[1]),
            new ImageShip(1520, 800, 4, bships[2]),
            new ImageShip(1520, 800, 4, bships[2]),
            new ImageShip(1520, 880, 5, bships[3])
    };
    //Schiffe Spieler 2
    ImageShip[] imageShip0 = {
            new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
            new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
            new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
            new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
            new ImageShip(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
            new ImageShip(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
            new ImageShip(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
            new ImageShip(1800 - 1520 - 3 * 40, 800, 4, bships[2]),
            new ImageShip(1800 - 1520 - 3 * 40, 800, 4, bships[2]),
            new ImageShip(1800 - 1520 - 3 * 40, 880, 5, bships[3])
    };
    private Pane battleshipContainer = new Pane();


    Game(Stage primaryStage, Database database) {
        this.primaryStage = primaryStage;
        this.database = database;
        createGUI(primaryStage);
    }

    private void drawGUI() {
        logger.debug("GUI setup");
        musicplay.setCycleCount(500);
        musicplay.play();

        for (int i = 0; i < imageShip0.length; i++) {
            battleshipContainer.getChildren().add(imageShip0[i].getImageView());
            battleshipContainer.getChildren().add(imageShip1[i].getImageView());
        }

        battleshipContainer.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                pressedX = event.getSceneX();
                pressedY = event.getSceneY();
                attacks((int) Math.round(pressedX), (int) Math.round(pressedY));
            }
        });


        buttonSaveShipsLeft.setLayoutX(1800 - 1520 - 3 * 40);
        buttonSaveShipsLeft.setLayoutY(500);
        buttonSaveShipsLeft.setPrefSize(120, 10);

        buttonSaveShipsLeft.setOnAction(event -> {
                    saveShips(imageShip0, player1, 440 + 40, 440 + 440);
                    shipsComplete();
                }
        );


        buttonSaveShipsRight.setLayoutX(1520);
        buttonSaveShipsRight.setLayoutY(500);
        buttonSaveShipsRight.setPrefSize(120, 10);
        buttonSaveShipsRight.setOnAction(
                event -> {
                    saveShips(imageShip1, player2, 2 * 440 + 40 + 40, 440 + 440 + 40 + 440);
                    shipsComplete();
                }
        );


        startmenu.setVisible(true);
        buttonSeeShips1.setLayoutX(1520);
        buttonSeeShips1.setLayoutY(550);
        buttonSeeShips1.setPrefSize(120, 10);
        buttonSeeShips1.setOnAction(
                event -> changeMask()
        );

        buttonSeeShips2.setLayoutX(160);
        buttonSeeShips2.setLayoutY(550);
        buttonSeeShips2.setPrefSize(120, 10);
        buttonSeeShips2.setOnAction(
                event -> changeMask()
        );

        indicate1.setFill(Color.RED);
        indicate2.setFill(Color.RED);

        battleshipContainer.getChildren().add(buttonSeeShips1);
        battleshipContainer.getChildren().add(buttonSeeShips2);
        battleshipContainer.getChildren().addAll(buttonSaveShipsLeft, buttonSaveShipsRight, maskleftfield, maskrightfield,
                startmenu, indicate1, indicate2);

        buttonReset.setVisible(false);
        maskleftfield.setVisible(false);
        maskrightfield.setVisible(false);
        buttonSeeShips1.setVisible(false);
        buttonSeeShips2.setVisible(false);
        indicate1.setVisible(false);
        indicate2.setVisible(false);
        changeMask();
    }

    private void activateMask() {
        maskleftfield.setVisible(true);
        maskrightfield.setVisible(true);
    }

    private void deactivateMask() {
        maskleftfield.setVisible(false);
        maskrightfield.setVisible(false);
    }

    private void changeMask() {
        if (gameRound % 2 == 1) {
            maskleftfield.setVisible(false);
            maskrightfield.setVisible(true);
        } else if (gameRound % 2 == 0) {
            maskleftfield.setVisible(true);
            maskrightfield.setVisible(false);
        }
    }

    private void createGUI(Stage primaryStage) {
        BackgroundImage background = new BackgroundImage(new Image("file:res/BattleshipsBackground.png", 1800, 1000,
                true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        maskleftfield.setX(439);
        maskleftfield.setY(39 + 440 + 40);
        maskrightfield.setX(439 + 440 + 40);
        maskrightfield.setY(39 + 440 + 40);


        battleshipContainer.setBackground(new Background(background));
        drawGUI();

        buttonReset.setLayoutX(440);
        buttonReset.setLayoutY(10);
        buttonReset.setPrefHeight(10);

        buttonReset.setOnAction(event -> {
            reset();
            Scene scenel = new Scene(battleshipContainer, 1800, 1000);
            primaryStage.setScene(scenel);
            primaryStage.show();
        });
        battleshipContainer.getChildren().add(buttonReset);
        buttonNewGame.setLayoutX(700);
        buttonNewGame.setLayoutY(300);
        buttonNewGame.setMinSize(400, 150);
        Font font = new Font(30);
        buttonNewGame.setFont(font);
        buttonNewGame.setOnAction(event -> {
                    reset();
                    Scene scenel = new Scene(battleshipContainer, 1800, 1000);
                    primaryStage.setScene(scenel);
                    primaryStage.show();

                }
        );

        battleshipContainer.getChildren().add(buttonNewGame);


        buttonExit.setLayoutX(700);
        buttonExit.setLayoutY(500);
        buttonExit.setMinSize(400, 150);
        buttonExit.setFont(font);
        buttonExit.setOnAction(event -> System.exit(0)
        );


        battleshipContainer.getChildren().add(buttonExit);
        buttonContinue.setOnAction(
                event -> {
                    reset();
                    buttonReset.setVisible(false);
                    battleshipContainer.getChildren().add(buttonNewGame);
                    battleshipContainer.getChildren().add(buttonExit);
                    startmenu.setVisible(true);
                    buttonNewGame.setVisible(true);
                    buttonExit.setVisible(true);
                    Scene scenel = new Scene(battleshipContainer, 1800, 1000);
                    primaryStage.setScene(scenel);
                    primaryStage.show();
                }
        );

        Scene scene = new Scene(battleshipContainer, 1800, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*Wir berechnen x und y relativ zum jeweiligen spielfeld und kriegen eine zahl zwischen 0 und 9 raus.*/
    private int[] calculateXY(int imageshipx, int imageshipy, int p1x, int p1y, int p2x, int p2y) {
        int[] result = new int[2];

        //Checkt ob die Koordinaten vom Schiff im richtigen Feld liegen
        if (imageshipx >= p1x && imageshipx <= p2x && imageshipy >= p1y && imageshipy <= p2y) {
            int vectorx;
            int vectory;
            //berechnet Relation zum Spielfeld
            vectorx = imageshipx - p1x;
            vectory = imageshipy - p1y;
            //Damit es eine Zahl zwischen 0 und 9 ist (denke ich!!)
            result[0] = vectorx / 40;
            result[1] = vectory / 40;
            return result;
        }
        return null;
    }


    private void saveShips(ImageShip[] imageShip, Player player, int p1x, int p2x) {

        /*Geht alle Schiffe duch und schaut erstmal ob */
        for (ImageShip imageship : imageShip) {
            if (!imageship.isDisable()) {
                int[] a = calculateXY(imageship.getX(), imageship.getY(), p1x, 560, p2x, 960);

                if (a != null) {
                    if (player.playfield.setShip(a[0], a[1], imageship.getLength(), imageship.getDirection(), imageship.getDiffvectorx(), imageship.getDiffvectory())) {
                        imageship.lock();

                    } else {
                        imageship.changePosition(0, 0);
                        imageship.rotateTo(Direction.RIGHT);
                    }
                } else {
                    imageship.changePosition(0, 0);
                    imageship.rotateTo(Direction.RIGHT);

                }
            }
        }
        if (player.playfield.isFleetComplete()) {
            gameRound++;
            if (player == player1) {
                changeMask();
                buttonSaveShipsLeft.setVisible(false);


            } else {
                buttonSaveShipsRight.setVisible(false);
                changeMask();
                buttonSeeShips1.setVisible(true);
                buttonSeeShips2.setVisible(true);
                indicate1.setVisible(true);


            }
            if (player1.playfield.isFleetComplete() && player2.playfield.isFleetComplete()) {
                activateMask();
            }

        }
    }

    private void attacks(int x, int y) {
        int[] a;
        if (!(player1.playfield.gameOver() || player2.playfield.gameOver()) && shipsComplete) {
            logger.info("Schiffe fertig");
            if (gameRound % 2 == 1) {
                a = calculateXY(x, y, 440 + 40, 40 + 40, 440 + 440, 440 + 40);

                if (a != null && player1.isAttackPossible(new Point(a[0], a[1]))) {
                    if (player2.playfield.attack(a[0], a[1])) {
                        drawAttack(a[0], a[1], x, y, player2);
                        player1.saveAttack(new Point(a[0], a[1]));
                        activateMask();
                        bombplay.stop();
                        bombplay.play();

                    } else {
                        drawMiss(x, y);
                        player1.saveAttack(new Point(a[0], a[1]));
                        activateMask();
                        indicate1.setVisible(false);
                        indicate2.setVisible(true);
                        missplay.stop();
                        missplay.play();
                    }
                }
                if (player2.playfield.gameOver()) {
                    logger.info("Spieler 1 hat gewonnen");
                    deactivateMask();
                    buttonSeeShips1.setVisible(false);
                    buttonSeeShips2.setVisible(false);
                    buttonReset.setVisible(false);
                    battleshipContainer.getChildren().add(wonleft);
                    wonleft.setX(50);
                    wonleft.setY(520);
                    winnerplay.stop();
                    winnerplay.play();
                    battleshipContainer.getChildren().add(buttonContinue);
                    buttonContinue.setLayoutX(160);
                    buttonContinue.setLayoutY(850);
                    buttonContinue.setVisible(true);
                }

            } else {
                a = calculateXY(x, y, 440 + 40 + 10 * 40 + 2 * 40, 40 + 40, 440 + 440 + 440 + 40, 440 + 40);
                if (a != null) {
                    if (player2.isAttackPossible(new Point(a[0], a[1]))) {
                        if (player1.playfield.attack(a[0], a[1])) {

                            drawAttack(a[0], a[1], x, y, player1);
                            player2.saveAttack(new Point(a[0], a[1]));
                            activateMask();
                            bombplay.stop();
                            bombplay.play();

                        } else {
                            drawMiss(x, y);
                            player2.saveAttack(new Point(a[0], a[1]));
                            activateMask();
                            indicate1.setVisible(true);
                            indicate2.setVisible(false);
                            missplay.stop();
                            missplay.play();
                        }

                    }
                }
                if (player1.playfield.gameOver()) {
                    logger.info("Spieler 2 hat gewonnen");
                    deactivateMask();
                    buttonSeeShips1.setVisible(false);
                    buttonSeeShips2.setVisible(false);
                    buttonReset.setVisible(false);
                    battleshipContainer.getChildren().add(wonright);
                    wonright.setX(1450);
                    wonright.setY(520);
                    winnerplay.stop();
                    winnerplay.play();
                    battleshipContainer.getChildren().add(buttonContinue);
                    buttonContinue.setLayoutX(1520);
                    buttonContinue.setLayoutY(850);
                    buttonContinue.setVisible(true);

                }
            }
        }
    }

    /*Wasserzeichen, gerundet auf die richtige Stelle setzen*/
    private void drawMiss(double x, double y) {
        int diffx = (int) x % 40;
        x -= diffx;

        int diffy = (int) y % 40;
        y -= diffy;
        ImageView miss = new ImageView("file:res/Waterhitmarker.png");
        miss.setX(x);
        miss.setY(y);
        battleshipContainer.getChildren().add(miss);
        gameRound++;
    }

    /*Feuerzeichen, gerundet auf die richtige Stelle. Wenn Schiff zerstört, richtiges destroyed Schiff setzen*/
    private void drawAttack(int xx, int yy, double xreal, double yreal, Player player) {
        ImageShip imageShipl;

        int diffx = (int) xreal % 40;
        xreal -= diffx;

        int diffy = (int) yreal % 40;
        yreal -= diffy;

        ImageView hit = new ImageView("file:res/Hit.png");
        hit.setX(xreal);
        hit.setY(yreal);
        battleshipContainer.getChildren().addAll(hit);


        Image image = new Image("file:res/1x2_Ship_Destroyed.png");
        /*Objekt ship wird entweder null oder ein Schiff zugewiesen (Siehe Klasse Ship, Methode isDestroyed). Wenn
        das Schiff zerstört ist, wird im switch case gefragt welche Länge und dementsprechen setzen wir das Schiff*/
        Ship ship = player.playfield.isDestroyed(xx, yy);

        if (ship != null) {
            logger.info("Schiff zerstört");
            switch (ship.getLength()) {
                case 0:
                    break;
                case 2:
                    image = new Image("file:res/1x2_Ship_Destroyed.png");
                    break;
                case 3:
                    image = new Image("file:res/1x3_Ship_Destroyed.png");
                    break;
                case 4:
                    image = new Image("file:res/1x4_Ship_Destroyed.png");
                    break;
                case 5:
                    image = new Image("file:res/1x5_Ship_Destroyed.png");
                    break;
            }

            int x;
            int y;
            //*40 um auf unsere Spielfeldkoordinaten zu kommen
            x = ship.getX() * 40;
            y = ship.getY() * 40;
            //Wird immer in das gegenüberliegende Feld gesetzt, deshalb stehen hier die Koordinaten vom Spieler 2
            if (player == player1) {
                x += 2 * 440 + 40 + 40;
                y += 2 * 40;

            } else {
                x += (440 + 40);
                y += (2 * 40);


            }

            /*Schiff kreiert und zum Battleshipcontainer dazugehaut und lock==true, um es nicht bewegbar zu machen*/
            imageShipl = new ImageShip(x - ship.getDivx(), y - ship.getDivy(), ship.getLength(), image);
            battleshipContainer.getChildren().add(imageShipl.getImageView());
            imageShipl.rotateTo(ship.getDirection());
            imageShipl.lock();
        }
    }

    //Alle Schiffe beider Spieler sind gesetzt, dann true
    private void shipsComplete() {
        if (player1.playfield.isFleetComplete() && player2.playfield.isFleetComplete()) {
            this.shipsComplete = true;
            logger.debug("Alle chiffe wurden gesetzt.");
        }

    }

    //Für einzelne Methoden, siehe entsprechende Klassen. Canvas wird zurückgesetzt
    private void reset() {
        logger.debug("Canvas wird zurückgesetzt.");
        for (int i = 0; i < imageShip0.length; i++) {
            imageShip1[i].rotateTo(Direction.RIGHT);
            imageShip0[i].rotateTo(Direction.RIGHT);
            imageShip0[i].reset();
            imageShip1[i].reset();

        }
        player1.playfield.removeAll();
        player2.playfield.removeAll();
        player1.deleteAllAttacks();
        player2.deleteAllAttacks();
        gameRound = 1;
        shipsComplete = false;
        buttonSaveShipsRight.setVisible(true);
        buttonSaveShipsLeft.setVisible(true);
        battleshipContainer = new Pane();
        BackgroundImage background = new BackgroundImage(new Image("file:res/BattleshipsBackground.png", 1800, 1000,
                true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        battleshipContainer.setBackground(new Background(background));
        drawGUI();
        battleshipContainer.getChildren().add(buttonReset);
        buttonReset.setVisible(true);
        startmenu.setVisible(false);
    }

}
