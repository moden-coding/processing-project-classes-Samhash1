import processing.core.*;
 
public class Bullet {
    private int x; // X postition of bullet
    private int y; // Y position of bullet
    


    private PImage photo;  // Varible to import image of bullet
    private PApplet canvas;
   


    public Bullet(int x, int y, PApplet c){ // Contructor for bullet
        this.x = x;   // x pos
        this.y = y;  // y pos
        canvas = c;
        photo = canvas.loadImage("Bullet1.png");  // Import image
    }
    public void display() {  //Function to display bullet
    
        canvas.image(photo, x, y, 10, 15);   // Setting up the photo

    }
    public void move(){
        y -= 5;           // Y move speed
    }
     public int getY(){
        return y;       // Y position function to show y pos
     }
     public int getX(){
        return x;      // X position function to show x pos
     }

}
