import processing.core.*;
 
public class Bullet {
    private int x;
    private int y;
    


    private PImage photo;
    private PApplet canvas;
   


    public Bullet(int x, int y, PApplet c){
        this.x = x;
        this.y = y;
        canvas = c;
        photo = canvas.loadImage("Bullet1.png");
    }
    public void display() {
    
        canvas.image(photo, x, y, 10, 15);

    }
    public void move(){
        y -= 5;
    }
     public int getY(){
        return y;
     }
     public int getX(){
        return x;
     }

}
