package sample;

import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;

public class Ship
{
    private ArrayList<ShipPart> shipParts = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Ship.class);
    private int length;
    private int xPosition;
    private int yPosition;
    private Direction direction;
    private int divx;
    private int divy;

    public int getX()
    {
        return xPosition;
    }

    public int getY()
    {
        return yPosition;
    }

    public int getDivx()
    {
        return divx;
    }

    public int getDivy()
    {
        return divy;
    }

    public int getLength()
    {
        return length;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public ArrayList<ShipPart> getShipParts()
    {
        return shipParts;
    }

    /*ist das unterste Level vom Schiff erstellen. Wird in der Ship Methode aufgerufen. Dort bekommt es die
    Übergabeparameter und hier können dann die ShipParts zur ArrayList hinzugefügt werden. Kette geht bis zu main
    Methode saveShips hinauf.*/
    private void generateShip(int x, int y, int length, Direction directions)
    {
        for (int i = 0; i < length; i++)
        {
            shipParts.add(new ShipPart(new Point(x, y)));
            switch (directions)
            {
                case UP:
                    y--;
                    break;

                case RIGHT:
                    x++;
                    break;

                case LEFT:
                    x--;
                    break;

                case DOWN:
                    y++;
                    break;
            }

        }
    }

    /*Kriegt die Koordinaten von setShip (Field Klasse), welche die Koordinaten von saveShip(main) übergeben bekommt.
     Also man muss mehrere Methoden "überstehen", um wirklich hier zu landen. Es wird dazwischen überprüft ob man
     setzen darf, ob Platz frei ist usw. Am Rande: Vergleichbar mit GUI am PC. Man macht was und es geht viele
     Schichten hinunter. */
    public Ship(int x, int y, int length, Direction directions, int diffvectorx, int diffvectory)
    {
        this.xPosition = x;
        this.yPosition = y;
        this.direction = directions;
        this.length = length;
        this.divx = diffvectorx;
        this.divy = diffvectory;

        generateShip(x, y, length, directions);

        /*Dient nur der Ausgabe für uns zum testen*/
       logger.info("ich generiere schiff an X= " + this.xPosition + " Y =" + this.yPosition + " richtung" + this.direction + " länge =" + this.length);
    }

    /*Die Schleife geht jeden part vom Schiff durch. Die if Bedienung checkt für jeden part (das macht es bei jedem
    Schleifendurchgang) ob die x und y Koordinate vom Part, das gerade in der For Schleife durchlaufen wird, den
    übergebenen x und y Koordinaten entspricht. (Die übergebenen, werden in der attack Methode in Field übergeben).
    Wenn es zutrifft, setzt es damage von dem Part auf true (das macht die destroy Methode in ShipPart)*/
    public boolean attack(int x, int y)
    {
        for (ShipPart shippart : this.shipParts)
        {
            if (shippart._pixel.getX() == x && shippart._pixel.getY() == y)
            {
                shippart.destroy();
                return true;
            }
        }
        return false;
    }

/*Das macht im Prinzip das gleiche wie die attack Methode. Nur diesmal schaut es, ob das ganze Schiff zerstört ist. Wie?
 Es prüft, für jeden Part (mit der For Schleife), ob es damaged ist. Wenn nicht damaged (das macht das Rufzeichen
 vorm ShipPart in der If - Bedienung) für einen part, false. Wenn für alle parts true zurückgeliefert wird, kommt bei
  der Methode true raus. Sprich, Schiff ist zerstört.*/
    public boolean checkIfDestroyed()
    {
        for (ShipPart shippart : this.shipParts)
        {
            if (!shippart.isDamaged())
            {
                return false;
            }
        }
        return true;
    }
}
