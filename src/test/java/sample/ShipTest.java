package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private int xTest = 400;
    private int yTest = 500;
    private int lengthTest = 2;
    private Direction directionTest = Direction.RIGHT;
    private int divXTest = 420;
    private int divYTest = 520;
    private Ship shipTest = new Ship(xTest,yTest,lengthTest,directionTest,divXTest,divYTest);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getX() {
        int actualX = shipTest.getX();
        assertEquals(xTest, actualX);
    }

    @Test
    void getY() {
        int actualY = shipTest.getY();
        assertEquals(yTest, actualY);
    }

    @Test
    void getDivX() {
        int actualDivX = shipTest.getDivX();
        assertEquals(divXTest, actualDivX);
    }

    @Test
    void getDivY() {
        int actualDivY = shipTest.getDivY();
        assertEquals(divYTest, actualDivY);
    }

    @Test
    void getLength() {
        int actualLength = shipTest.getLength();
        assertEquals(lengthTest, actualLength);
    }

    @Test
    void getDirection() {
        Direction actualDirection = shipTest.getDirection();
        assertEquals(directionTest, actualDirection);
    }

    @Test
    void getShipParts() {
    }

    @Test
    void attack() {
    }

    @Test
    void checkIfDestroyed() {
    }
}