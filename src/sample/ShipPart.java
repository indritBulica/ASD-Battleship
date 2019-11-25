package sample;

import java.awt.*;

public class ShipPart
{
    /*Jeder Teil vom Schiff (in unserem Fall ist jeder Teil genau 40pixel lang) hat die Eigenschaften von der Klasse
    ShipPart*/

    public Point _pixel;
    private boolean damage = false;

    public ShipPart(Point pixel)
    {
        _pixel = pixel;

        /*Dient der Ausgabe für uns, zum testen*/
        System.out.printf("Schiffteil an X = %f Y = %f Schaden = %s",_pixel.getX(), _pixel.getY(), this.damage);
    }

    /*ist dieser Teil vom Schiff zerstört?*/
    public boolean isDamaged()
    {
        return damage;
    }

    /*Wird in der Ship klasse, in der attack Methode aufgerufen!*/
    public void destroy()
    {
        this.damage = true;
    }

    public int getX() {
       return _pixel.x;
    }

    public int getY() {
       return _pixel.y;
    }
}