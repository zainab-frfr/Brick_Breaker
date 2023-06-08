/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author zaina
 */
public class methodsBrickBreaker extends JPanel implements KeyListener, ActionListener{
    int ballPosX = 400;
    int ballPosY = 600;
    int dirX = -2;
    int dirY = -1;
   
    int sliderX = 379;
    
    boolean play = true;
    int score = 0;
    
    Timer timer;
    int delay = 1;
    
    bricks b;
    
    int totalBricks = 21;
    
    public methodsBrickBreaker(){
        
        //creating bricks
        b = new bricks(3,7);
        
        //applying key listener to the whole JPanel
        addKeyListener(this);
        
        //is true by default 
        setFocusable(true);
        //shift and tab keys can't be used
        setFocusTraversalKeysEnabled(false);
        
        //Sceduling repainting of ball and bricks to show movement
        timer = new Timer(delay,this);
        timer.start();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        //addKeyListener(this);
        
        //setting background color
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 800, 700);
        
        //painting the instructions
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.drawString("Use the arrow keys to move left and right", 50, 50);
        g.drawString("Don't let the ball fall!",50,70);
        
        //painting the score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.drawString("Score: "+score, 650, 50);
        
        //painting all the bricks when game starts
        Graphics2D g2D = (Graphics2D)g;
        b.paint(g2D);
        
        //painting ball
        g.setColor(Color.red);
        g.drawOval(this.ballPosX, this.ballPosY,20, 20);
        g.setColor(Color.PINK);
        g.fillOval(this.ballPosX, this.ballPosY,20, 20);
        
        //painting slider
        g.setColor(Color.BLACK);
        g.fillRect(sliderX,625, 65, 20);
        
        //printing you win if all bricks broken
        if(totalBricks == 0){
            play=false;
            g.setColor(Color.CYAN);
            g.setFont(new Font("Times New Roman", Font.BOLD,30));
            g.drawString("You Win! Score: "+score, 250, 350);
            g.drawString("Press Enter to play again", 250, 375);
        }
        
        //printing you lose it ball goes below slider
        if(this.ballPosY>625){
            play = false;
            g.setColor(Color.CYAN);
            g.setFont(new Font("Times New Roman", Font.BOLD,30));
            g.drawString("You Lose! Score: "+score, 250, 350);
            g.drawString("Press Enter to play again", 250, 375);
            
        }
        
        g.dispose();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //addKeyListener(this);
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(sliderX+65+20<=800){
                sliderX+=20;
            }
            else{
                int dif = 800-(sliderX+65);
                sliderX+=dif;
            }
        }
        
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            if(sliderX-20>=0){
                sliderX-=20;
            }
            else{
                sliderX=0;
            }
        }
        
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            play = true;
            ballPosX = 400;
            ballPosY = 600;
            dirX = -2;
            dirY = -1;
            sliderX = 379;
            score = 0;
            totalBricks=21;
            b = new bricks(3,7);
            
            repaint();
        }
            
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        timer.start();
        
        if(play){
            //ball-pedal collision
            if (new Rectangle(this.ballPosX, this.ballPosY,20, 20).intersects(new Rectangle(sliderX,625, 65, 20))){ 
                dirY = -dirY;
            }
            //ball-brick collision
            else{
                for (int i = 0; i < b.bricks.length; i++) {
                    for (int j = 0; j < b.bricks[0].length; j++) {
                        
                        if(b.bricks[i][j]!=0){
                            int X = 100+(b.width*j);
                            int Y = 100+(b.height*i);        
                        
                            Rectangle rect = new Rectangle(X,Y,b.width,b.height);
                            Rectangle ball = new Rectangle(this.ballPosX,this.ballPosY,20,20);
                        
                            if(ball.intersects(rect)){
                                b.setBrickValue(0, i, j);
                                score+=5;
                                totalBricks--;
                            
                                if((this.ballPosX+20) == X || this.ballPosX==(X+b.width)){
                                    dirX = - dirX;
                                }
                                else{
                                    dirY = - dirY;
                                }
                            }
                        }    
                    }
                    
                }
            }   
            
            //moving the ball
            this.ballPosX += dirX;
            this.ballPosY += dirY;
            
            //making sure ball doesn't leave from sides
            if(this.ballPosX==0 || (this.ballPosX+20)==800){
                dirX = -dirX;
            }
            
            //making sure ball doesn't leave from top 
            if(this.ballPosY==0){
                dirY = -dirY;
            }
            
        }
  
        repaint();
    }
    
    
}
