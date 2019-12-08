package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Point positionToCheck;
    Point newAttackPosition;
    ArrayList<Point> savedAttackPositions;

    @BeforeEach
    void setUp() {
        positionToCheck = new Point(4,4);
        savedAttackPositions = new ArrayList<>();
        savedAttackPositions.add(new Point(4,4));
        newAttackPosition = new Point(4,4);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAttack() {

        assertTrue(this.savedAttackPositions.add(newAttackPosition));
    }

    @Test
    void isAttackPossible() {
        assertTrue(this.savedAttackPositions.contains(positionToCheck));
    }

    @Test
    void deleteAllAttacks() {
        this.savedAttackPositions = new ArrayList();
        assertTrue(savedAttackPositions.isEmpty());
    }
}