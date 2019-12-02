package sample;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    Player() {
    }

    Field playfield = new Field();

    private ArrayList<Point> savedAttackPositions = new ArrayList();

    public void saveAttack(Point newAttackPosition) {
        this.savedAttackPositions.add(newAttackPosition);
    }

    boolean isAttackPossible(Point positionToCheck) {
        return !this.savedAttackPositions.contains(positionToCheck);
    }

    public void deleteAllAttacks() {
        this.savedAttackPositions = new ArrayList();
    }


}
