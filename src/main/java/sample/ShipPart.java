package sample;

import org.apache.log4j.Logger;

import java.awt.*;

public class ShipPart {
    /*Jeder Teil vom Schiff (in unserem Fall ist jeder Teil genau 40pixel lang) hat die Eigenschaften von der Klasse
    ShipPart*/
    private static final Logger logger = Logger.getLogger(ShipPart.class);
    public Point _pixel;
    private boolean damage = false;

    public ShipPart(Point pixel) {
        _pixel = pixel;

        /*Dient der Ausgabe für uns, zum testen*/
        logger.info("Schiffteil an X = "+_pixel.getX()+ " Y = "+_pixel.getY() +" Schaden = "+this.damage);
    }

    /*ist dieser Teil vom Schiff zerstört?*/
    public boolean isDamaged() {
        return damage;
    }

    /*Wird in der Ship klasse, in der attack Methode aufgerufen!*/
    public void destroy() {
        this.damage = true;
    }

    public int getX() {
        return _pixel.x;
    }

    public int getY() {
        return _pixel.y;
    }
}