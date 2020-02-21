import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Default extends KeyAdapter
{
	public int x, y;
	public Rectangle hitBox;
	public char direction;
	private Mario player;
	//Pre:variables must exist
    //Post:creates a default system for objects
	public Default(int x, int y) 
	{
		this.x = x;
		this.y = y;
		direction = 'N';
		hitBox = new Rectangle(this.x, this.y, 35, 35);
	}
    public void keyPressed(KeyEvent e) 
    {
        player.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) 
    {
        player.keyReleased(e);
    }
    public Default(Mario player) 
    {
        this.player = player;
    }
}