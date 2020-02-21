

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Shield extends Default
{
    private Image img;
    private int velX;
    private int velY;
    //Pre:variables must exist
    //Post:contructs variables
    public Shield(int x, int y)
    {
        super(x,y);
        hitBox = new Rectangle(this.x, this.y, x, y);
        img = Toolkit.getDefaultToolkit().getImage("Images//Shield.png");
    }
    //Pre:variables must exist
    //Post:updates velocity and creates hitbox with enough 
    public void update()
    {
        x+=velX;
        y+=velY;
        velY = 0;
        velX = 0;
        hitBox = new Rectangle(this.x-12, this.y-8, 50, 50);
    }
    //Pre:Mario object must exist
    //Post:follows mario 
    public void move(Mario player)
    {
        if (x < player.x)
        {
            velX += 5;
        }
        else if (x > player.x)
        {
            velX -= 5;
        }
        if (y < player.y)
        {
            velY += 5;
        }
        else if (y > player.y)
        {
            velY -= 5;
        }
    }
    //Pre:
    //Post:
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(img,this.x-12, this.y-8, null);
        g2d.draw(hitBox);
    }
    //Pre:hitBox must exist
    //Post:returns hitbox
    public Rectangle gethitBox()
    {
        return hitBox;
    }
    //Pre:direction must exist
    //Post:returns direction
    public char getDirection()
    {
        return direction;
    }
    //Pre:variables must exist
    //Post:returns/sets x & y
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void setVelY(int velY)
    {
        this.velY = velY;
    }
    public void setVelX(int velX)
    {
        this.velX = velX;
    }
}