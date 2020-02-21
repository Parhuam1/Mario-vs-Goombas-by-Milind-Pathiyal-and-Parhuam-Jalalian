
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;


public class EndFrame 
{
    private static JFrame f;
    private static JPanel p;
    private static JButton b;
    private static JButton b2;
    private static JLabel l;
    private static JLabel l2;
    private static JLabel l3; 
    //Pre:None
    //Post:Creates frame and centers it in the middle of screen once player is dead
    public static void displayFrame()
    {
        f = new JFrame("Game Over");
        f.setVisible(true);
        f.setResizable(false);
        int height = 670;
        int width = 630;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ex = (screen.width / 2) - (width / 4); // Center horizontally.
        int ey = (screen.height / 2) - (height / 4); // Center vertically.
        f.setBounds(ex, ey, width / 2, height / 2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p = new JPanel();
        
        b = new JButton("Try Again?");
        b2 = new JButton("End Game.");
        l = new JLabel(("Score: " + PlayBoard.returnKills()+ " || Number of PowerUps picked up: " +
        PlayBoard.returnPowerPoints()), JLabel.CENTER);
        l2 = new JLabel(("High Score: " + PlayBoard.returnhighScore()), JLabel.CENTER);
        
        
        p.add(b);
        //Pre:requires labels
        //Post:restarts playBoard
        b.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e)
            {
                  f.setVisible(false); 
                  f.dispose();  
                  PlayBoard.restart();
            }
        });
        
        p.add(b2);
        //Pre:requires labels
        //Post:quits jpanel
        b2.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e)
            {
                   System.exit(0);                 //exits on 0
            }
        });
        
        
        p.add(l);
        p.add(l2);
        
        f.add(p);
               
    } 
    
    
}