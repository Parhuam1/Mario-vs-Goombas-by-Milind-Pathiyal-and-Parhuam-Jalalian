import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.HashMap;
public class Mario extends Default
{
    private int velX = 0;
    private int velY = 0;
    private Image img;
    private ArrayList<KeyEvent> pressed;
    private ArrayList<Fireball> projectile;
    private boolean dead;
    private boolean playingDeathSound;
    private HashMap<String, AudioPlayer> sfx;
    //Pre:variables must exist
    //Post:constructs variables, sound effects, and hitBox
    public Mario(int x, int y) 
    {
        super(x,y);
        direction = 'N';
        projectile = new ArrayList<Fireball>();
        hitBox = new Rectangle(this.x, this.y, 28, 38);
        //hitBox.setColor(Color.black);
        img = Toolkit.getDefaultToolkit().getImage("Images//Mario_STANDING.png");
        sfx = new HashMap<String, AudioPlayer>();
        sfx.put("fireball", new AudioPlayer("/SFX/fireball.wav"));
        sfx.put("Death", new AudioPlayer("/SFX/Death.wav"));
    }
    
    //Pre: variables must exist
    //Post:creates hitBox and updates velocity and cues death sound when player is dead
    public void update() 
    {
        y += velY;
        x += velX; 
        hitBox = new Rectangle(this.x, this.y, 28, 38);
        if (dead && !playingDeathSound)
        {
            sfx.get("Death").play();
            playingDeathSound = true;
            EndFrame.displayFrame();
        }
    }    
    
    //Pre:None
    //Post:returns location of player
      public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    //Pre: Fireball class must exist
    //Post:returns Fireball object
    public ArrayList<Fireball> getFireballs()
    {
        return projectile;
    }
    
    //Pre:dead must exist
    //Post:returns/sets if player is dead  
    public void setDead(boolean dead)
    {
       this.dead = dead;
    }
    public boolean isDead()
    {
        return dead;
    }
    
    //Pre:enemy and direction must exist
    //Post:player stops moving when goomba lands on player
    public void detectCollide(Goomba enemy)
    {
        if(this.hitBox.intersects(enemy.gethitBox()))
        {
            velY = 0;
            velX = 0;
        }
    }
    
    //Pre:import & Fireball must exist
    //Post:ejects Fireball projectiles from player
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(img, this.x, this.y, null);
        g2d.draw(hitBox);
        for(Fireball a: projectile)
             a.draw(g2d);   
    }
    
    //Pre:keyListener & images must exist
    //Post:sets velocity depending on which button pressed and updates with designated image
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_UP.png");
            velY = -5;
            direction = 'N';
        }
        if (key == KeyEvent.VK_A)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_LEFT.png");
            velX = -5;
            direction = 'W';
        }
        if (key == KeyEvent.VK_S)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_DOWN.png");
            velY = 5;
            direction = 'S';
        }
        if (key == KeyEvent.VK_D)
        {   
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_RIGHT.png");
            velX = 5;
            direction = 'E';
        }
        if(key == KeyEvent.VK_SPACE)
        {
            projectile.add(new Fireball(this.x,this.y,this.getDirection()));
            sfx.get("fireball").play();
        }
    }
    
    //Pre:keyListener must exist
    //Post:once key is released, player stops moving
    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
            velY = 0;
        else if (key == KeyEvent.VK_A)
            velX = 0;
        else if (key == KeyEvent.VK_S)
            velY = 0;
        else if (key == KeyEvent.VK_D)
            velX = 0;
    }
    
    //Pre:hitBox must exist
    //Post:returns hitBox
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

