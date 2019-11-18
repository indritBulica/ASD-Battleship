package sample;

public class AIsave
{
    private int x,y;
    private Direction direction;
    private boolean water;

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
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

    public AIsave(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public AIsave(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public AIsave(int x, int y, boolean water)
    {
        this.x = x;
        this.y = y;
        this.water = water;
        direction=null;
    }

    public AIsave(int x, int y, Direction direction, boolean water)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.water = water;
    }
}
