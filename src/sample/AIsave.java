package sample;

public class AIsave
{
    private int x,y;
    private Point point = new Point(); 
    private Direction direction;
    private boolean water;

    public int getX()
    {
        return this.point.xPosition;
    }

    public int getY()
    {
        return this.point.yPosition;
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

    public AIsave(int xPosition, int yPosition){
        this.point.xPosition = xPosition;
        this.point.yPosition = yPosition;
    }

    public AIsave(int xPosition, int yPosition, Direction direction)
    {
        this.point.xPosition = xPosition;
        this.point.yPosition = yPosition;
        this.direction = direction;
    }

    public AIsave(int xPosition, int yPosition, boolean water)
    {
        this.point.xPosition = xPosition;
        this.point.yPosition = yPosition;
        this.water = water;
        direction=null;
    }

    public AIsave(int xPosition, int yPosition, Direction direction, boolean water)
    {
        this.point.xPosition = xPosition;
        this.point.yPosition = yPosition;
        this.direction = direction;
        this.water = water;
    }
}
