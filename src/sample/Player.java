package sample;

import java.util.ArrayList;


public class Player
{
    Field playfield = new Field();

    private ArrayList<Point> savedAttackPositions = new ArrayList();

    public void saveAttack(Point newAttackPosition)
    {
        this.savedAttackPositions.add(newAttackPosition);
    }

    boolean isAttackPossible(Point positionToCheck)
    {
        if (this.savedAttackPositions.contains(positionToCheck)){
            return false;
        }
        return true;
    }

    public void deleteAllAttacks()
    {
        this.savedAttackPositions = new ArrayList();
    }



    //Ab hier ist AI (ist nicht implementiert)

/*
    public boolean AISet()
    {
        if (!isHuman)
        {
            return false;
        } else
        {
            this.AISetting(2);
            this.AISetting(2);
            this.AISetting(2);
            this.AISetting(2);
            this.AISetting(3);
            this.AISetting(3);
            this.AISetting(3);
            this.AISetting(4);
            this.AISetting(4);
            this.AISetting(5);
            return true;
        }
    }

    private void AISetting(int length)
    {
        int x, y;
        Direction direction;
        Random random = new Random();
        do
        {
            x = random.nextInt((9 - 0) + 1) + 0;
            y = random.nextInt((9 - 0) + 1) + 0;
            direction = Direction.RIGHT;
            switch (random.nextInt((3 - 0) + 1) + 0)
            {
                case 0:
                    direction = Direction.RIGHT;
                    break;
                case 1:
                    direction = Direction.UP;
                    break;
                case 2:
                    direction = Direction.LEFT;
                    break;
                case 3:
                    direction = Direction.DOWN;
                    break;
            }
        } while (this.playfield.setShip(x, y, length, direction, 0, 0));
    }

    public boolean simpleAIAttack(Player enemy)
    {
        int x, y;
        Random random = new Random();
        do
        {
            x = random.nextInt((9 - 0) + 1) + 0;
            y = random.nextInt((9 - 0) + 1) + 0;
        } while (this.attackPossible(x, y));
        this.SaveAttack(x, y);
        return enemy.area.attack(x, y);
    }

    public boolean complexAIAttack(Player enemy)
    {
        Random random = new Random();
        boolean result;
        int x, y;
        Direction direction;
        if (this.AIsave == null)
        {

            do
            {
                x = random.nextInt((9 - 0) + 1) + 0;
                y = random.nextInt((9 - 0) + 1) + 0;
            } while (this.attackPossible(x, y));
            this.SaveAttack(x, y);
            result = enemy.area.attack(x, y);
            if (enemy.area.isDestroyed(x, y) != null)
            {
                return true;
            } else if (!result)
            {
                return false;
            } else
            {
                AIsave = new ArrayList<>();
                AIsave.add(new AIsave(x, y, false));
            }
        } else if (AIsave.get(0).getDirection() == null)
        {
            direction = Direction.DOWN;
            x = AIsave.get(0).getX();
            y = AIsave.get(0).getY();
            do
            {
                switch (random.nextInt((3 - 0) + 1) + 0)
                {
                    case 0:
                        direction = Direction.RIGHT;
                        x++;
                        break;
                    case 1:
                        direction = Direction.UP;
                        y--;
                        break;
                    case 2:
                        direction = Direction.LEFT;
                        x--;
                        break;
                    case 3:
                        direction = Direction.DOWN;
                        y++;
                        break;
                }
            } while (this.attackPossible(x, y));
            result = enemy.area.attack(x, y);
            this.SaveAttack(x, y);
            if (result)
            {
                AIsave.add(new AIsave(x, y, direction, true));
                return true;
            } else
            {
                return false;
            }
        } else
        {
            x = AIsave.get(0).getX();
            y = AIsave.get(0).getY();
            switch (AIsave.get(0).getDirection())
            {
                case RIGHT:
                    x += (int) AIsave.size();
                    break;
                case LEFT:
                    x -= (int) AIsave.size();
                    break;
                case DOWN:
                    y += (int) AIsave.size();
                    break;
                case UP:
                    y -= (int) AIsave.size();
                    break;
            }
            if (this.attackPossible(x, y))
            {
                this.SaveAttack(x, y);
                result = enemy.area.attack(x, y);
                if (result)
                {
                    AIsave.add(new AIsave(x, y, AIsave.get(0).getDirection(), false));
                    return result;
                } else
                {
                    switch (AIsave.get(0).getDirection())
                    {
                        case RIGHT:
                            x--;
                            break;
                        case LEFT:
                            x++;
                            break;
                        case DOWN:
                            y--;
                            break;
                        case UP:
                            y++;
                            break;
                    }
                    if (this.attackPossible(x, y))
                    {
                        AIsave a = AIsave.get(0);
                        direction = Direction.LEFT;
                        switch (AIsave.get(0).getDirection())
                        {
                            case RIGHT:
                                direction = Direction.LEFT;
                                break;
                            case LEFT:
                                direction = Direction.RIGHT;
                                break;
                            case DOWN:
                                direction = Direction.UP;
                                break;
                            case UP:
                                direction = Direction.DOWN;
                                break;
                        }
                        a.setDirection(direction);
                        AIsave = new ArrayList<>();
                        AIsave.add(a);
                        AIsave.add(new AIsave(x, y, direction, false));
                        return true;
                    } else
                    {
                        AIsave = null;
                        return false;
                    }

                }
            }


        }


        return false;
    }
*/
}
