
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
public class Mines extends Default
{
    private Image image;
    //Pre:Variables must exist
    //Post:constructs variables
    public Mines(int x, int y)
    {
        super(x,y);
        hitBox = new Rectangle(this.x, this.y, 30, 30);
        image = Toolkit.getDefaultToolkit().getImage("Images//Mines.png");
    }
    //Pre: None
    //Post:creates hitBox
    public void update()
    {
        hitBox = new Rectangle(this.x, this.y, 30, 30);       
    }
    //Pre:Import, image, and hitBox must exist
    //Post:draws hitBox and image
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(image, this.x, this.y, null);
        g2d.draw(hitBox);
    }    
    //Pre:hitBox must exist
    //Post:returns hitBox
    public Rectangle gethitBox()
    {
        return hitBox;
    }    
}
