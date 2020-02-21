
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
public class Fireball extends Default
{
    private int velX = 0;
    private int velY = 0;
    private Image image;
    //Pre:variables must exist
    //Post:constructs variables
    public Fireball(int x, int y, char direction)
    {
        super(x,y);
        this.direction = direction;
        hitBox = new Rectangle(this.x, this.y, 15, 15);
        image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
    }
    //Pre:variabes must exist
    //Post:updates velocity, hitBox, and moves accordingly
    public void update()
    {
        x+=velX;
        y+=velY;
        hitBox = new Rectangle(this.x, this.y, 15, 15);
        move();        
    }
    //Pre:import and hitBox must exist
    //Post:draws hitBox and image
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(image, this.x, this.y, null);
        g2d.draw(hitBox);
    }
    //Pre:direction and images must exist
    //Post:fireball moves with high velocity in a designated straight line
    public void move()
    {
        if(direction == 'N')
        {
            velY = -25;
            velX = 0;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
        if(direction == 'W')
        {
            velY = 0;
            velX = -25;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
        if(direction == 'E')
        {
            velY = 0;
            velX = 25;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
        if(direction == 'S')
        {
            velY = 25;
            velX = 0;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
    }
    //Pre:hitBox must exist
    //Post:retuns hitBox
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
}
