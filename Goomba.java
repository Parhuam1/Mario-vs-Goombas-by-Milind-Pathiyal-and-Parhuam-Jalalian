
import java.awt.*;
import java.util.*; 

public class Goomba extends Default
{
    private Image img;
    private int velX;
    private int velY;
    private ArrayList<PowerUp> powers;
    private boolean slow;
    private int timer;
    //Pre:variables must exist
    //Post:creates hitBox, list of powers, x y
    public Goomba(int x, int y)
    {
        super(x,y);
        hitBox = new Rectangle(this.x, this.y, 24, 34);
        powers = new ArrayList<PowerUp>();
    }
    //Pre:variables must exist
    //Post:draws hitBox and updates velocity
    public void update()
    {
        timer --;
        if (timer <= 0)
            slow = false;        
        if (slow)
        {
           velY = 0;
           velX = 0;
        }
        else
        {
           x+=velX;
           y+=velY;
        }
        hitBox = new Rectangle(this.x, this.y, 24, 34);
    }
    //Pre: Mario object must exist
    //Post:Goomba will move in the diretion mario is moving and updates image accordingly to movement
    public void move(Mario player)
    {
        int xRandOffset = player.x + (int)(Math.random() * 35);
        int yRandOffset = player.y + (int)(Math.random() * 35); 
        if (x < xRandOffset)
        {
            velX = 1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Goomba_RIGHT.png");
        }
        else if (x > xRandOffset)
        {
            velX = -1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Goomba_LEFT.png");
        }
        if (y < yRandOffset)
        {
            velY = 1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Goomba_DOWN.png");
        }
        else if (y > yRandOffset)
        {
            velY = -1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Goomba_UP.png");
        }
    }
    //Pre:import, hitBox, and powerup must exist
    //Post:draws image, hitBox, and powerups
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(img,this.x, this.y, null);
        g2d.draw(hitBox);
        for(PowerUp a: powers)
             a.draw(g2d);
    }
    //Pre:hitBox must exist
    //Post:returns hitbox
    public Rectangle gethitBox()
    {
        return hitBox;
    }
    //Pre:direction must exist
    //Post:retunrs direction
    public char getDirection()
    {
        return direction;
    }
    //Pre:variables must exist
    //Post:returns/sets location
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
    //Pre:Goomba object must exist
    //Post: removes goomba from window
    public void remove(Goomba a)
    {
        a.remove(this);
    }
    //Pre: boolean variable must be true
    //Post: sets or gets boolean slow
    public void setSlow(boolean slow)
    {
        this.slow = slow;
        timer = 375;
    }    
    public boolean getSlow()
    {
        return slow;
    }
}
