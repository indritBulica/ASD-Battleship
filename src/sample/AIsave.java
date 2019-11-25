package sample;

import java.awt.*;

public class AIsave
{
    //private int x,y;
    private Point point = new Point(); 
    private Direction direction;
    private boolean water;

    public int getX()
    {
        return this.point.x;
    }

    public int getY()
    {
        return this.point.y;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public boolean isWater()
    {
        return water;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public void setWater(boolean water)
    {
        this.water = water;
    }

// --Commented out by Inspection START (23.11.2019 10:53):
    public AIsave(int xPosition, int yPosition){
// --Commented out by Inspection START (23.11.2019 10:53):
        this.point.x = xPosition;
        this.point.y = yPosition;
    }
//// --Commented out by Inspection STOP (23.11.2019 10:53)
//
//// --Commented out by Inspection START (23.11.2019 10:53):
    public AIsave(int xPosition, int yPosition, Direction direction)
// --Commented out by Inspection STOP (23.11.2019 10:53)
// --Commented out by Inspection START (23.11.2019 10:53):
    {
        this.point.x = xPosition;
        this.point.y = yPosition;
        this.direction = direction;
    }
////
    public AIsave(int xPosition, int yPosition, boolean water)
    {
        this.point.x = xPosition;
// --Commented out by Inspection STOP (23.11.2019 10:53)
// --Commented out by Inspection STOP (23.11.2019 10:53)
        this.point.y = yPosition;
        this.water = water;
        direction=null;
    }

    public AIsave(int xPosition, int yPosition, Direction direction, boolean water)
    {
        this.point.x = xPosition;
        this.point.y = yPosition;
        this.direction = direction;
        this.water = water;
    }
}
