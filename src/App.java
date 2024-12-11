import java.util.ArrayList;

import processing.core.*;
     
public class App extends PApplet{
    Spaceship first;
    Bullet second;
    ArrayList<Bullet> missles; 
    Invador third;
    ArrayList <Invador> enemy;

    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        first = new Spaceship(100 , 675, 130,  this);
        missles = new ArrayList<>();
        third = new Invador(100, 20, this);
        enemy = new ArrayList<>();
    }

    public void settings(){
       size(1400, 800);
    }

    public void draw(){
       background(0,0,0);
        first.display();
        first.update();
        third.display();
       
       
        for (int i = 0; i < missles.size(); i++) {
            Bullet second = missles.get(i);
            second.display(); 
            second.move();    
           
            if (second.getY() < -45) {
                missles.remove(i);
                i--; 
            }
        }
       
    }
         
    public void keyPressed(){
        if (keyCode == LEFT ){
           first.moveLeft();
        } else if (keyCode == RIGHT){
            first.moveRight();
        }
        if (key == ' '){
            Bullet second = new Bullet( first.shipgetx() - 3, 645, this);
            missles.add(second);
        }
    }


     public void keyReleased(){
        if (keyCode == LEFT){
          first.stopLeft();
        } else if ( keyCode == RIGHT){
          first.stopRight();
        }
     }
     
}
