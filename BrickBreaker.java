/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package brickbreaker;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class BrickBreaker {
    
  
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setSize(800,700);
        frame.setTitle("Brick Breaker");
        frame.add(new methodsBrickBreaker());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        
    }
    
}
