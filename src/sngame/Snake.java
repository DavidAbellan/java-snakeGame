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
public class Snake {
    //Guarda donde están todos las partes del cuerpo de la serpiente
    private final int [] x = new int [Board.getAllDots()];
    private final int [] y = new int [Board.getAllDots()];
    
    //Guarda la dirección de la serpiente
    private boolean movinRight= false;
    private boolean movinLeft = false;
    private boolean movinUp = false;
    private boolean movinDown = false;
    
    private int joints = 0; //guarda la longitud en puntos de la serpiente
                           // empieza en tres 
    public int getSnakeX( int index  ) {
        return x[index];
    }
    public int getSnakeY(int index ) {
        return y[index];
    }
    public void setSnakeX(int i) {
        x[0]= i;
        
    }
    public void setSnakeY(int i){
        y[0]= i;
    
    }
 public boolean isMovingLeft() {
    return movinLeft;
}

public void setMovingLeft(boolean movingLeft) {
    this.movinLeft = movingLeft;
}

public boolean isMovingRight() {
    return movinRight;
}

public void setMovingRight(boolean movingRight) {
    this.movinRight = movingRight;
}

public boolean isMovingUp() {
    return movinUp;
}
public void setMovingUp(boolean movingUp) {
    this.movinUp = movingUp;
}

public boolean isMovingDown() {
    return movinDown;
}

public void setMovingDown(boolean movingDown) {
    this.movinDown = movingDown;
}

public int getJoints() {
    return joints;
}
public void setJoints(int j) {
    joints = j;
}
public void move() {
    //mueve los puntos de la serpiente en cadena , uno detrás de otro
    for (int i = joints ; i > 0 ; i --){
        x[i] = x[(i-1)];
        y[i] = y[(i-1)];
     }
    //direcciones de la serpiente
    if(movinLeft) {
        x[0] -= Board.getDotSize();
    }
    if(movinRight) {
        x[0] += Board.getDotSize();
        
    }
    if (movinDown) { 
        y[0] += Board.getDotSize();
    }
    if (movinUp) { 
        y[0] -= Board.getDotSize();
        
    }
   
    
}}
