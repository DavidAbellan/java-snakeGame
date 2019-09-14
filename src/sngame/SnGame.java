/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sngame;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author barry
 */
public class SnGame extends JFrame{
      SnGame() {
         add(new Board());
         setResizable(false);
         pack();
         setTitle("Snake chiss Game");
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         EventQueue.invokeLater(new Runnable() {
                @Override 
                    public void run() {
                       JFrame frame = new SnGame();
                       frame.setVisible(true);
                       
                        }} );
        
    }
    }
    

