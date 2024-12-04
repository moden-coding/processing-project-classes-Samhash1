import processing.core.*;
     
public class App extends PApplet{
    Spaceship first;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        first = new Spaceship(50 , 70, 130,  this);
    }

    public void settings(){
       size(1400, 800);
    }

    public void draw(){
       background(0,0,0);
        first.display();
    }
    public void keyPressed(){  
        if (keyCode == LEFT) {
            first.move(-10, 0); 
        }
       }
       public void move(int dx, int dy) {
        x += dx; // Move the x position
        y += dy; // Move the y position
    }
}
