package sample;

public class AttackPositions
{
    private int x;
    private int y;
/*AttackPositions wird nur in der PlayerClass verwendet. Es hat nur den Konstruktor AttackPositions(x,y). Also
wenn die Klasse aufgerufen wird, müssen immer die beiden Übergabeparameter verwendet werden*/

    public AttackPositions(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
