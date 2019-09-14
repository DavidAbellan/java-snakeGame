/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sngame;

/**
 *
 * @author barry
 */
public class Food {
 private Snake snake = new Snake();
    //guarda donde está la comida
    private int foodX;
    private int foodY;
    //para determinar la posición random de la comida
    private final int RANDOMPOSITION = 40;
    
    public void createFood() {
        // determina la posición de la manzana
        int location = (int)(Math.random() * RANDOMPOSITION);
        foodX = ((location * Board.getDotSize()));
        location = (int) (Math.random() * RANDOMPOSITION);
        foodY = ((location * Board.getDotSize()));
        
        if((foodX == snake.getSnakeX(0)) && ((foodY == snake.getSnakeY(0))))
            createFood();
    }
    public int getFoodX() {
        return foodX;
    }
    public int getFoodY() {
        return foodY;
    }
}
   

