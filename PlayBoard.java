
 
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Timer;


public class PlayBoard extends JPanel implements ActionListener 
{
    private boolean explode = false;
    private boolean makeBubble = false;
    private boolean luck = false;
    private boolean powerRole;
    private boolean spawnMore = false;
    private boolean endFrameLoop;
    
    private Mario player;
    private AudioPlayer bgMusic;
    private Timer mainTimer;
    
    private ArrayList<Goomba> enemies;
    private ArrayList<PowerUp> powers;
    private ArrayList<Mines> mines;
    private ArrayList<Shield> bubbles;
    private HashMap<String, AudioPlayer> sfx;
    private int lastDiedX;
    private int lastDiedY;
    private int powerType = 0;
    private int deathCount = 0;
    
    private static int kills = 0;
    private static int powerPoints = 0;
    private static boolean restartGame;
    private static boolean dontReset = false;
    private static int highScore = 0;    
    private static JFrame f = new JFrame();

    //Pre:None
    //Post:Creates game
    static void main(String[] args) 
    {
        f = new JFrame("Survive!");        
        f.add(new PlayBoard());
        f.setSize(1600, 900);
        f.setVisible(true);
        f.setResizable(false);
        int height = 900;
        int width = 1600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ex = (screen.width / 2) - (width / 2); // Center horizontally.
        int ey = (screen.height / 2) - (height / 2); // Center vertically.
        f.setBounds(ex, ey, width, height);//Centers frame in the middle of screen
    }         
    //Pre:None
    //Post:Construct variables 
    public PlayBoard() 
    {
        this.setFocusable(true);
        enemies = new ArrayList<Goomba>();
        powers = new ArrayList<PowerUp>();
        mines = new ArrayList<Mines>();  
        bubbles = new ArrayList<Shield>();
        setBackground(Color.BLACK);
        //Music
        bgMusic = new AudioPlayer("/Music/Megalovania.wav");
        bgMusic.play();
        sfx = new HashMap<String, AudioPlayer>();     
        sfx.put("MonsterDeath", new AudioPlayer("/SFX/MonsterDeath.wav"));
        sfx.put("Nuke", new AudioPlayer("/SFX/Nuke.wav"));
        sfx.put("Time", new AudioPlayer("/SFX/SlowTime.wav"));
        sfx.put("Mines", new AudioPlayer("/SFX/Mines.wav"));
        sfx.put("Explode", new AudioPlayer("/SFX/Explode.wav"));
        sfx.put("BubbleShield", new AudioPlayer("/SFX/BubbleShield.wav"));
        sfx.put("Pop", new AudioPlayer("/SFX/Pop.wav"));
        sfx.put("Fail", new AudioPlayer("/SFX/Fail.wav"));
        sfx.put("MoreMonsters", new AudioPlayer("/SFX/MoreMonsters.wav"));
        //Places mario and enemies on map
        player = new Mario(800,450);
        makeEnemies(5);  
        mainTimer = new Timer(8, this);
        mainTimer.start();
        addKeyListener(new Default(this.player));
    }
    //Pre:None
    //Post:Restarts the game on cue
    public static void restart()
    {
         setKills();//Returns kill count
         setPowerPoints();//Returns power point count
         f.setVisible(false); 
         f.dispose();
         String[] args = {};
         PlayBoard.main(args);//Calls main method
    }
    //Pre: int powerPoints and kills must exist
    //Post:returns cumulative # of kills and power points to endFrame
    public static int returnPowerPoints()
    {
        return powerPoints;
    }
    public static void setPowerPoints()
    {
       powerPoints = 0;
    }      
    public static int returnKills()
    {
        return kills;
    }
    public static void setKills()
    {
        kills = 0;
    }
    //Pre:Variables must exist
    //Post:Updates highest score
    public static int returnhighScore()
    {  
        int lastScore = 0;
        if(dontReset == false)//highScore will be set to 0 when window is closed
            highScore = 0;
        for(int i = 0; i < kills; i++)
        {
            lastScore++; //adds score
        }
        if(lastScore > highScore) //if current score is greater than old score
        {
            highScore = lastScore;//set new high score
            dontReset = true;
            return highScore;
        }
        else
             return highScore;            
    }   
    //Pre:int amount must exist
    //Post:Randomly places goombas in the four corners of the window
    public void makeEnemies(int amount)
    {
        //x and y coordinates are randomly set so goombas don't spawn on each other
        for(int i = 0; i <= amount; i++)
        {
            int location = (int) (Math.random()*4+1);
            if(location == 1) //top left
            {
                int x = (int)(Math.random()*-200+1);
                int y = (int)(Math.random()*-200+1);
                Goomba enemy = new Goomba(x, y);
                enemies.add(enemy);
            }
              if(location == 2) //bottom left
            {
                int x = (int)(Math.random()*-200+1);
                int y = (int)(Math.random()*800+600);
                Goomba enemy = new Goomba(x, y);
                enemies.add(enemy);
            }
              if(location == 3) //top right
            {
                int x = (int)(Math.random()*1400+1200);
                int y = (int)(Math.random()*-200+1);
                Goomba  enemy = new Goomba(x, y);
                enemies.add(enemy);
            }
            if(location == 4) //bottom right
            {
                int x = (int)(Math.random()*1400+1200);
                int y = (int)(Math.random()*800+600);
                Goomba  enemy = new Goomba(x, y);
                enemies.add(enemy);
            }
        }
    }
    //Pre:spawnMore must exist
    //Post:If a goomba is killed, spawn x more(int a) in a random location and set spawnMore to false
    public void spawn(boolean spawnMore)
    {
        int a = 2; //this variable can be changed to increase difficulty
        if(spawnMore == true)
        {
            int location = (int) (Math.random()*4+1);
            if(location == 1) //top left
            {    
                for(int i = 0; i < a; i++)
                {
                    int x = (int)(Math.random()*-200+1);
                    int y = (int)(Math.random()*-200+1);    
                    Goomba  enemy = new Goomba(x, y);
                    enemies.add(enemy);
                    spawnMore = false;
                }
            }
            else if(location == 2) //bottom left
            {
               for(int i = 0; i < a; i++)
               {
                    int x = (int)(Math.random()*-200+1);
                    int y = (int)(Math.random()*1000+800);
                    Goomba  enemy = new Goomba(x, y);               
                    enemies.add(enemy);
                    spawnMore = false;
               }
            }
            else if(location == 3) //top right
            {
                 for(int i = 0; i < a; i++)
                {
                    int x = (int)(Math.random()*1400+1200);
                    int y = (int)(Math.random()*-200+1);
                    Goomba enemy = new Goomba(x, y);
                    enemies.add(enemy);
                    spawnMore = false;
                }
            }
            else //bottom right
            {
               for(int i = 0; i < a; i++)
                {
                    int x = (int)(Math.random()*1400+1200);
                    int y = (int)(Math.random()*1000+800);
                    Goomba  enemy = new Goomba(x, y);
                    enemies.add(enemy);
                    spawnMore = false;
               }
            }
        }
    } 
    //Pre:powerRole, x, y, and classes must exist
    //Post:if goomba is killed, powerups are spawned on window
    public void spawnPowers(boolean powerRole, int x, int y)
    {
        if(powerRole == true)
        {
            int chance =(int)(Math.random()* 3 + 1); //33% drop rate
            if(chance == 1)
              {
                int role = (int) (Math.random()*5+1); 
                if(role == 1) //MakeMines 
                { 
                    PowerUp minez = new MakeMines(x,y,powerType); 
                    minez.setPower(0);
                    powers.add(minez);
                    spawnMore = false;                
                }
                else if(role == 2) //SlowTime  
                {
                    PowerUp sTime = new SlowTime(x,y,powerType);
                    sTime.setPower(1);
                    powers.add(sTime);
                    spawnMore = false;               
                }
                else if(role == 3) //Nuke 
                {  
                    PowerUp nuke = new Nuke(x,y, powerType);
                    nuke.setPower(2);
                    powers.add(nuke);
                    spawnMore = false;                
                }
                else if(role == 4) //Bubble 
                {  
                    PowerUp bubble = new BubbleShield(x,y, powerType);
                    bubble.setPower(3);
                    powers.add(bubble);
                    spawnMore = false;                
                }
                else if(role == 5) //Dice
                {
                    PowerUp dice = new Dice(x,y, powerType);
                    dice.setPower(4);
                    powers.add(dice);
                    spawnMore = false;  
                }
            }        
        }
    }
    //Pre:None
    //Post:Sets mines in random location   
    public void mines()
    {
        for(int i = 0; i < 10; i++)
        {
            int x = (int)(Math.random()*1200+300);
            int y = (int)(Math.random()*800+100);
            Mines mine = new Mines(x, y);
            mines.add(mine);
        }
    }
    //Pre:None
    //Post:Sets bubbles in the center of the window
    public void bubbles()
    {
        for(int i = 0; i < 2; i++)
        {
            int x = 800;
            int y = 450;
            Shield bub = new Shield(x, y);
            bubbles.add(bub);
        }
    }
    //Pre:JFrame and Images must exist
    //Post:Draws hitbox & image
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if (!player.isDead())
            player.draw(g2d);     //keep drawing mario until mario collides with goomba
        for(int i = 0; i < enemies.size()-1; i++)
        {
            enemies.get(i).draw(g2d);
        }
        for(int i = 0; i < powers.size()-1; i++)
        {
            if(!player.isDead())    
                powers.get(i).draw(g2d);    //draw powerups while player is still alive
        }
        for(int i = 0; i < mines.size()-1; i++)
        {
            if(!player.isDead())
                mines.get(i).draw(g2d);
        }
          for(int i = 0; i < bubbles.size()-1; i++)
        {
            if(!player.isDead())
                bubbles.get(i).draw(g2d);
        } 
    }    
    //@Override
    //Pre:JPanel & Variables must exist
    //Post:Creates animation in end scene, plays music, spawns goombas, remove goombas and mario
    public void actionPerformed(ActionEvent e) 
    {
        player.update();
        for(int i = 0; i < enemies.size()-1; i++) //Animation
        {
            Goomba enemy = enemies.get(i);
            enemy.move(player);
            if (player.isDead())
            {
               enemy.setVelY(10);//drops enemies in last frame
               enemy.setVelX(0);
            }            
        }          
        for(int i = enemies.size()-1; i >= 0; i--) //Environment
        {
            Goomba enemy = enemies.get(i);
            enemy.update();         
            if(enemy.gethitBox().intersects(player.gethitBox()))
            {
                player.setDead(true);
                bgMusic.close();                
            } 
            if(makeBubble)
            {
                bubbles();                
                makeBubble = false; 
                break;
            }
            while(explode)
            {                       
                  enemies.remove(i);
                  if(enemies.size() == 0)
                  {
                       makeEnemies(kills/10); //without it, no goombas will spawn
                       explode = false;
                  }
                  sfx.get("MonsterDeath").play();
                  kills++; 
                  break;
            }
            player.detectCollide(enemy);
        }        
        ArrayList<Fireball> projectile = player.getFireballs();
        for (int i = projectile.size() - 1; i >= 0; i --)
        {
            Fireball bullet = projectile.get(i);
            bullet.update();            
            // Checks for intersection with any Goomba 
            for (int j = enemies.size() - 1; j >= 0; j--)
            {
                Goomba enemy = enemies.get(j);
                if (bullet.gethitBox().intersects(enemy.gethitBox()))
                {
                    int x = (int)(Math.random()*1200+600);  //1600, 900
                    int y = (int)(Math.random()*600+100);
                    enemies.remove(j);
                    sfx.get("MonsterDeath").play();
                    kills++;
                    spawnMore = true;                    
                    powerRole = true;
                    if(spawnMore)
                        spawn(spawnMore);
                    if(powerRole)                    
                        spawnPowers(powerRole, x, y);  
                    projectile.remove(i);
                    break;
                } 
            }
        }
         for(int i = powers.size()-1; i >= 0; i--)
        {
            PowerUp power = powers.get(i);
            power.update();
            if(power.gethitBox().intersects(player.gethitBox())) 
            {              
                    if(power.getPower() == 0)
                    {                         
                        sfx.get("Mines").play();
                        mines();                       
                    }
                    if(power.getPower() == 1)
                    {
                        sfx.get("Time").play();
                        for (Goomba enemy : enemies)
                        {
                            enemy.setSlow(true);
                        }
                    }
                    if(power.getPower() == 2)
                    {
                        sfx.get("Nuke").play();
                        explode = true;
                    }
                    if(power.getPower() == 3)
                    {
                        sfx.get("BubbleShield").play();
                        makeBubble = true;
                    }
                    if(power.getPower() == 4) //Dice
                    {
                         int role = (int) (Math.random()*6+1);
                         if(role == 1)
                         {
                            sfx.get("Mines").play();
                            mines();
                         }
                         else if(role == 2)
                         {
                            sfx.get("Time").play();
                            for (Goomba enemy : enemies)
                            {
                                enemy.setSlow(true);
                            }
                         }
                         else if(role == 3)
                         {
                            sfx.get("Nuke").play();
                            explode = true;
                         }
                         else if(role == 4)
                         {
                            sfx.get("BubbleShield").play();
                            makeBubble = true;
                         }
                         else if(role == 5)
                         {
                            sfx.get("Fail").play();
                         }
                         else 
                         {
                            sfx.get("MoreMonsters").play();
                            makeEnemies(5);
                         }                        
                    } 
                    powers.remove(i);
                    powerPoints++;
            }
        }
        for(int j = mines.size() - 1; j >= 0; j--)
        {
               Mines mine = mines.get(j);
               mine.update();                           
               for (int k = enemies.size() - 1; k >= 0; k--)
               {
                   Goomba enemy = enemies.get(k);
                   if(mine.gethitBox().intersects(enemy.gethitBox()))
                   {
                       enemies.remove(k);     
                       sfx.get("Explode").play();
                       kills++;
                       spawnMore = true;
                       if(spawnMore)
                            spawn(spawnMore);
                       mines.remove(j);
                       break;
                   }
               }
        }
        for(int j = bubbles.size() - 1; j >= 0; j--)
        {
               Shield bub = bubbles.get(j);
               bub.move(player);
               bub.update();
                for (int k = enemies.size() - 1; k >= 0; k--)
               {
                   Goomba enemy = enemies.get(k);
                   if(bub.gethitBox().intersects(enemy.gethitBox()))
                   {
                       enemies.remove(k);     
                       sfx.get("Pop").play();
                       kills++;
                       spawnMore = true;
                       if(spawnMore)
                            spawn(spawnMore);
                       bubbles.remove(j);
                       break;
                   }
               }
        }
        repaint();
    }  
} 
 
    


