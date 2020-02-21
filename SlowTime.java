
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
public class SlowTime extends PowerUp
{
    private Image img; 
    //Pre:Variables must exist
    //Post:contructs variables
    public SlowTime(int x, int y, int powerType)
    {
        super(x,y, powerType);
        powerType = 1;
        hitBox = new Rectangle(this.x, this.y, 30, 30);
        img = Toolkit.getDefaultToolkit().getImage("Images//SlowTime.png");
    }
    //Pre:hitBox must exist
    //Post:creates hitbox
    public void update()
    {
        hitBox = new Rectangle(this.x, this.y, 30, 30);        
    }
    //Pre:import, image, and hitbox must exist
    //Post:draws hitBox and image
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(img, this.x, this.y, null);
        g2d.draw(hitBox);
    }
    //Pre:hitBox must exist
    //Post:returns hitbox
    public Rectangle gethitBox()
    {
        return hitBox;
    }   
 
}

