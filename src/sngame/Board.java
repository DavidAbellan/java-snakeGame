/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sngame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author barry
 */
@SuppressWarnings("serial")
/**
 *
 * @author barry
 */
public class Board extends JPanel implements ActionListener{
  
 //tamaño de la ventana
    private final static int WWITHD = 1000;
    private final static int WHEIGHT = 980;
    
    //tamaño del pixel (manzana & serpiente)
    
    private final static int PIXEL = 25;
    
    //píxeles totales del juego 
    
    private final static int TOTALPIXEL = (WWITHD * WHEIGHT) / (PIXEL * PIXEL);
    
    
    //comprobar si el juego está en marcha
    
    private boolean inGame = true;
    
    //temporizador
    
    private Timer timer;
    
    //velocidad del juego, cuanto más grande la serpiente más rápido va
    
    private static int speed = 45;
    
    private Snake snake = new Snake();
    private Food food = new Food();
    
    public Board()  {
        addKeyListener(new Keys());
        setBackground(Color.black);
        setFocusable(true);
        
        setPreferredSize(new Dimension(WWITHD, WHEIGHT));
        
        initializeGame();
        
    }
    //Para pintar componentes
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    void draw(Graphics g) { 
        //Si el juego está en on , la serpiente se mueve
        if(inGame == true) {
            g.setColor(Color.green);
            g.fillRect(food.getFoodX(), food.getFoodY(), PIXEL, PIXEL); //pintar comida
            
            //pintar serpiente
            for (int i = 0; i < snake.getJoints(); i++) {
                //Cabeza
                if (i == 0) {
                g.setColor(Color.RED);
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXEL, PIXEL);
                // Cuerpo
                 } else {
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXEL, PIXEL);
                } 
            
            }
            //Sincroniza gráficos juntos
            Toolkit.getDefaultToolkit().sync();
        }else {
            //Si no estamos vivos el juego termina
            endGame(g);
            
        
        }
    }
    void initializeGame() {
        //inicializar tamaño serpiente
        snake.setJoints(3);
        //crear cuerpo de la serpiente
        for (int i = 0; i < snake.getJoints(); i++) {
        snake.setSnakeX(WWITHD / 2);
        snake.setSnakeY(WHEIGHT / 2);
        
    }
        //empieza a moverse a la derecha
        snake.setMovingRight(true);
        
        //crear la primera manzana
        
        food.createFood();
        
        //crear el timer para guardar la velocidad de juego / hace el juego moverse
        
        timer = new Timer(speed, this);
        timer.start();
        
        //Si la serpiente está cerca de la comida 
    }   
    void checkFoodCollisions() {

    if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
            && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {

        System.out.println("intersection");
        // Add a 'joint' to our snake
        snake.setJoints(snake.getJoints() + 1);
        // Create new food
        food.createFood();
    } }
    //para colisiones de la serpiente consigo misma o el borde
    void checkCollisions() {

    // si colisiona consigo misma
    for (int i = snake.getJoints(); i > 0; i--) {

        // no puede intersectar a si misma si no es mayor de 5
        if ((i > 5)
                && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                        .getSnakeY(0) == snake.getSnakeY(i)))) {
            inGame = false; // el juego termina
        }
    }
     // si colisiona con los bordes 
    if (snake.getSnakeY(0) >= WHEIGHT) {
        inGame = false;
    }

    if (snake.getSnakeY(0) < 0) {
        inGame = false;
    }

    if (snake.getSnakeX(0) >= WWITHD) {
        inGame = false;
    }

    if (snake.getSnakeX(0) < 0) {
        inGame = false;
    }

    // Para el tiempo cuando el juego termina
    if (!inGame) {
        timer.stop();
    }
}
    void endGame(Graphics g) {

    // Crea mensaje de Game over
    String message = "Game over";

    // Crea una instancia de fuente
    Font font = new Font("Times New Roman", Font.BOLD, 14);
    FontMetrics metrics = getFontMetrics(font);

  
    g.setColor(Color.red);
    g.setFont(font);

    // Dibuja el mensaje
    g.drawString(message, (WWITHD - metrics.stringWidth(message)) / 2,
            WHEIGHT / 2);

    System.out.println("Game Ended");

}
    // Corre constantemente en el juego
@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == true) {

        checkFoodCollisions();
        checkCollisions();
        snake.move();

        System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                + " " + food.getFoodX() + ", " + food.getFoodY());
    }
    // Repinta, renderiza
    repaint();
}
private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

            inGame = true;
            snake.setMovingDown(false);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setMovingUp(false);

            initializeGame();
        }
    }
}
private boolean proximity(int a, int b, int closeness) {
    return Math.abs((long) a - b) <= closeness;
}

public static int getAllDots() {
    return TOTALPIXEL;
}

public static int getDotSize() {
    return PIXEL;
}
}
    
        
        
    
    
    
    
    
    
