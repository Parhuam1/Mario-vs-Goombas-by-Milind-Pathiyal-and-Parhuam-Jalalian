
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
public class PowerUp extends Default
{
    private Image img;
    private int powerType;
    //Pre:variables must exist
    //Post:constructs variables
    public PowerUp(int x, int y, int powerType)
    {
        super(x,y);
        this.powerType = powerType;
        hitBox = new Rectangle(this.x, this.y, 30, 30);
    }
    //Pre:hitBox must exist
    //Post:creates hitbox
    public void update()
    {
        hitBox = new Rectangle(this.x, this.y, 30, 30);        
    }
    //Pre:import, hitbox, and image must exist
    //Post:draws image and hitBox
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(img, this.x, this.y, null);
        g2d.draw(hitBox);
    }
    //Pre:hitBox must exist
    //Post: returns hitBox
    public Rectangle gethitBox()
    {
        return hitBox;
    } 
    //Pre:powerType must exist
    //Post:return powerType
    public int getPower()
    {
        return powerType;
    }
    //Pre:powerType must exist
    //Post:sets powerType
    public void setPower(int powerType)
    {
        this.powerType = powerType;
    }
}
