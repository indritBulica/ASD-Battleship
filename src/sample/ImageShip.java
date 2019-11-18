package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.image.ImageView;

public class ImageShip
{
    private int x, y, length;
    private int rotate = 1;
    private int beginX, beginY;
    /*Wichtige Vektoren: Sind dafür da, dass die Bilder und Schiffe gleich rotiert sind und richtig liegen. Da wir es
     händisch hinein schreiben müssen, dass die "digitalen" ships (nicht die Bilder) auch rotiert sind quasi. Noch
     bestätigen, ob das stimmt bitte!!*/
    private int diffvectorx, diffvectory;
    private double startX, startY, moveX, moveY, setX, setY, newX, newY;

    private ImageView imageView;
    private Image image;
    private Direction direction;

    private boolean disable = false;


    private void setDiffvectorx(int diffvectorx)
    {
        this.diffvectorx = diffvectorx;
    }

    private void setDiffvectory(int diffvectory)
    {
        this.diffvectory = diffvectory;
    }

    private void setX(int x)
    {
        this.x = x;
    }

    private void setY(int y)
    {
        this.y = y;
    }

    public int getDiffvectorx()
    {
        return diffvectorx;
    }


    public int getDiffvectory()
    {
        return diffvectory;
    }


    public Direction getDirection()
    {
        return direction;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getLength()
    {
        return length;
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public int getBeginX()
    {
        return beginX;
    }

    public int getBeginY()
    {
        return beginY;
    }

    public Image getImage()
    {
        return image;
    }


    /*Konstruktor, mit dem wir die Schiffe in der Main (großer Block am Anfang) erstellen. Jedes Schiff hat die
    Eigenschaften und Funktionen, die hier drinnen stehen. z.B Es sind alle Schiffe automatisch nach rechts
    orientiert.*/
    public ImageShip(int x, int y, int length, Image image)
    {
        this.x = x;
        this.y = y;
        this.beginX = this.x;
        this.beginY = this.y;
        //System.out.println("x= " + x + "y= " + y);
        this.length = length;
        this.image = image;
        this.direction = Direction.RIGHT;


        this.imageView = new ImageView(image);
        imageView.setX(this.x);
        imageView.setY(this.y);
        this.setDiffvectorx(0);
        this.setDiffvectory(0);
        // System.out.println("x= " + this.x + "y= " + this.y);


        imageView.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                //Nur wenn Schiff lock==false (unten bei lock erklärt)
                if (!disable)
                {
                    if (event.getEventType() == MouseEvent.MOUSE_PRESSED && event.getButton().equals(MouseButton.PRIMARY))
                    {
                        //Koordinaten vom Mouseclick
                        startX = event.getSceneX();
                        startY = event.getSceneY();

                        //Koordinaten vom Bild was geklickt wurde (schätze ich, bitte noch bestätigen!)
                        moveX = ((ImageView) (event.getSource())).getTranslateX();
                        moveY = ((ImageView) (event.getSource())).getTranslateY();

                    }
                    if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && event.getButton().equals(MouseButton.PRIMARY))
                    {
                        //Differenz der Koordinaten von der Maus, wo wir losgelassen haben und angefangen haben
                        setX = event.getSceneX() - startX;
                        setY = event.getSceneY() - startY;

                        //Die Differenz zwischen Bild Anfang und wie weit die maus gedragged wurde
                        newX = moveX + setX;
                        newY = moveY + setY;

                        /*wir runden es, damit es durch 40 teilbar ist (weil alle Felder durch 40 teilbar sind)*/
                        int diffx = (int) newX % 40;
                        newX = newX - diffx;

                        int diffy = (int) newY % 40;
                        newY = newY - diffy;


                        ((ImageView) (event.getSource())).setTranslateX(newX);
                        ((ImageView) (event.getSource())).setTranslateY(newY);

                        /*Alle Faktoren werden berücksichtigt, damit die neuen Koordinaten stimmen, muss auch die
                        errechnete differenz vom rotieren mit einbezogen werden.*/
                        setX(beginX + getDiffvectorx() + (int) newX);
                        setY(beginY + getDiffvectory() + (int) newY);


                        int a[] = calculateXY(getX(), getY(), 440 + 40, 40 + 440 + 40 + 40, 440 + 440, 40 + 920);
                        if (a != null)
                        {
                            System.out.println("x= " + (a[0] + 1) + "y= " + (a[1] + 1));
                        }
                    }
                    if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton().equals(MouseButton.SECONDARY))
                    {
                        /*
                        System.out.println("echt x= " + getX() + "y= " + getY());
                        int a[] = calculateXY(getX(), getY(), 440 + 40, 40 + 440 + 40 + 40, 440 + 440, 40 + 920);
                        if (a != null)
                        {
                            System.out.println("x= " + (a[0] + 1) + "y= " + (a[1] + 1));
                        }
                        */

                        rotate();
                    }
                }
            }
        });
    }

    /*Gelocked wird, wenn saveShips in der main ein Schiff gespeichert wird oder wenn ein zerstörtes Schiff
    gezeichnet wird. Dient dafür, dass man es nicht mehr draggen kann.*/
    public void lock()
    {
        this.disable = true;
    }


    public boolean isDisable()
    {
        return this.disable;
    }

    /*Wir übergeben zwar x und y = 0 wenn wir die Methode aufrufen, bedeuetet aber nur, dass es zur
    Ursprungskoordinate zurückspringt (wird von dort alles relativ gerechnet). Ermöglicht durch this.x=xx...*/
    public void changePosition(int xx, int yy)//Position muss von den ursprugort angegeben werden und nicht von 0/0
    {

        this.imageView.setTranslateX(xx);
        this.imageView.setTranslateY(yy);
        this.x = xx + this.beginX + diffvectorx;
        this.y = yy + this.beginY + diffvectory;
        //   System.out.println("x= " + this.x + "y= " + this.y);
    }

    /*Nach dem reseten, soll das Schiff wieder zum Ursprungsort zurück*/
    public void reset()
    {
        this.disable = false;
        this.changePosition(0, 0);
    }

    //rotiert das Bild und das im code angelegte Schiff
    private void rotate()
    {

                        /*
                        System.out.println("echt x= " + getX() + "y= " + getY());
                        int a[] = calculateXY(getX(), getY(), 440 + 40, 40 + 440 + 40 + 40, 440 + 440, 40 + 920);
                        if (a != null)
                        {
                            System.out.println("x= " + (a[0] + 1) + "y= " + (a[1] + 1));
                        }
                        */

        /*Die rotate Methode rotiert immer um die Mitte eines Objektes. Das ist ein Problem bei Geraden
         Schiffen weil sie nach dem Rotieren zwischen zwei Feldern liegen würden. Hier verhindern wir
         das, durch Differezenaufsummierung, je nachdem wie oft gedreht wurde.*/
        if (getLength() % 2 == 1)
        {
            double value = imageView.getRotate();
            imageView.setRotate(value - 90);
        } else
        {
            /*rotate: Je nachdem welcher Wert rotate hat, muss man addieren oder subtrahieren (kommt
             drauf an wie oft man geklickt hat),*/
            if (rotate % 2 == 1)
            {
                double value = imageView.getRotate();
                imageView.setRotate(value - 90);
                imageView.setX(imageView.getX() + 20);
                imageView.setY(imageView.getY() - 20);
            } else
            {
                double value = imageView.getRotate();
                imageView.setRotate(value - 90);
                imageView.setX(imageView.getX() - 20);
                imageView.setY(imageView.getY() + 20);
            }
        }
        rotate++;

         /*Switch ist dafür da, um die Bilder die wir drehen und die ImageShips ("Digital angelegte
          Schiffe" die wir erstellen, nach dem Rotieren abzugleichen. Weil nur weil wir das Bild drehen,
          heißt es ja nicht, dass sich unsere ImageShips mitdrehen. Sind ja zwei verschiedene
          Entitäten. Immer Abhängig von welcher Richtung man dreht, ändern wir manuell dann die
          Direction mit den dementsprechenden Rechungen auch um.*/
        switch (direction)
        {
            case UP:
                direction = Direction.LEFT;
                if (getLength() % 2 == 1)
                {
                    setX(getX() + 40 * (getLength() / 2));
                    setY(getY() - 40 * (getLength() / 2));

                    setDiffvectorx(getDiffvectorx() + 40 * (getLength() / 2));
                    setDiffvectory(getDiffvectory() - 40 * (getLength() / 2));
                } else
                {
                    if (getLength() == 2)
                    {
                    } else
                    {
                        setX(getX() + 40);
                        setY(getY() - 40);

                        setDiffvectorx(getDiffvectorx() + 40);
                        setDiffvectory(getDiffvectory() - 40);
                    }
                }
                break;
            case DOWN:
                direction = Direction.RIGHT;
                if (getLength() % 2 == 1)
                {
                    setX(getX() - 40 * (getLength() / 2));
                    setY(getY() + 40 * (getLength() / 2));

                    setDiffvectorx(getDiffvectorx() - 40 * (getLength() / 2));
                    setDiffvectory(getDiffvectory() + 40 * (getLength() / 2));
                } else
                {
                    if (getLength() == 2)
                    {
                        setX(getX() - 40);
                        setY(getY() + 40);

                        setDiffvectorx(getDiffvectorx() - 40);
                        setDiffvectory(getDiffvectory() + 40);

                    } else
                    {
                        setX(getX() - 2 * 40);
                        setY(getY() + 2 * 40);

                        setDiffvectorx(getDiffvectorx() - 40 * 2);
                        setDiffvectory(getDiffvectory() + 40 * 2);
                    }
                }
                break;
            case LEFT:
                direction = Direction.DOWN;
                if (getLength() % 2 == 1)
                {
                    setX(getX() - 40 * (getLength() / 2));
                    setY(getY() - 40 * (getLength() / 2));

                    setDiffvectorx(getDiffvectorx() - 40 * (getLength() / 2));
                    setDiffvectory(getDiffvectory() - 40 * (getLength() / 2));
                } else
                {
                    if (getLength() == 2)
                    {
                        setY(getY() - 40);

                        setDiffvectory(getDiffvectory() - 40);
                    } else
                    {
                        setX(getX() - 40);
                        setY(getY() - 2 * 40);

                        setDiffvectorx(getDiffvectorx() - 40);
                        setDiffvectory(getDiffvectory() - 40 * 2);
                    }
                }
                break;
            case RIGHT:
                direction = Direction.UP;
                if (getLength() % 2 == 1)
                {
                    setX(getX() + 40 * (getLength() / 2));
                    setY(getY() + 40 * (getLength() / 2));

                    setDiffvectorx(getDiffvectorx() + 40 * (getLength() / 2));
                    setDiffvectory(getDiffvectory() + 40 * (getLength() / 2));

                } else
                {
                    if (getLength() == 2)
                    {
                        setX(getX() + 40);

                        setDiffvectorx(getDiffvectorx() + 40);

                    } else
                    {
                        setX(getX() + 2 * 40);
                        setY(getY() + 40);

                        setDiffvectorx(getDiffvectorx() + 2 * 40);
                        setDiffvectory(getDiffvectory() + 40);
                    }
                }
                break;
        }
        int[] a = calculateXY(getX(), getY(), 440 + 40, 40 + 440 + 40 + 40, 440 + 440, 40 + 920);
        if (a != null)
        {
            System.out.println("x= " + (a[0] + 1) + "y= " + (a[1] + 1));
        }


    }


    /*Verwenden wir beim reset button in der Main Methode, um auf RIGHT zu rotieren z.B. Es dreht solange, bis es der
     übergebenen Direction entspricht.*/
    public void rotateTo(Direction directionRotate)
    {
        while (this.direction != directionRotate)
        {
            this.rotate();
        }
    }


    //Dient nur zum testen als Ausgabe
    private int[] calculateXY(int x, int y, int p1x, int p1y, int p2x, int p2y)
    {
        int result[] = new int[2];
        if (x >= p1x && x <= p2x && y >= p1y && y <= p2y)
        {
            int vectorx, vectory;
            vectorx = x - p1x;
            vectory = y - p1y;
            result[0] = vectorx / 40;
            result[1] = vectory / 40;
            return result;
        }
        return null;
    }


}
