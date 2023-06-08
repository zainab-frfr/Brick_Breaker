/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.*;

/**
 *
 * @author zaina
 */
public class bricks {
    int width;
    int height;
    int[][] bricks;
    
    public bricks(int rows, int columns){
        this.bricks = new int[rows][columns];
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                bricks[i][j]=1;
            }
        }
        
        width = 600/columns;
        height = 200/rows;
    }
    
    public void paint(Graphics2D g){
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if(bricks[i][j]!=0){
                    g.setColor(Color.ORANGE);
                    g.fillRect(100+(width*j), 100+(height*i), width, height);
                    
                    g.setStroke(new BasicStroke(4));
                    g.setColor(Color.BLACK);
                    g.drawRect(100+(width*j), 100+(height*i), width, height);
                }
                
            }
            
        }
    }
    
    public void setBrickValue(int val, int row, int col){
        bricks[row][col] = val;
    }
}
