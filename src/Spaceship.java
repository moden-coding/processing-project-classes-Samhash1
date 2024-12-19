import processing.core.*;

public class Spaceship {
    private int x;  // Creating x position varible
    private int y;  // function to show y pos
    // private int size;  // Size varible
    private PApplet canvas;
    boolean left, right = false;   // Move function for varible
    private PImage photo;  // Import photo
    private int speed = 6; // Speed varible

    public Spaceship(int x, int y, int size, PApplet c) {  // Constructor for space shi
        this.x = x;  // X position varible
        this.y = y;  // Y position varible
        // this.size = size;
        canvas = c;
        photo = canvas.loadImage("spaceship.png"); // Load space ship photo

    }

    public void display() {
    
        canvas.image(photo, x, y, 95, 95);  // Function to display the imported image at certain positions and dimentions

    }

    public void moveLeft() {
        left = true;                 // Smooth move function for left movemet
    }

    public void moveRight() {
        right = true;            // Smooth move function for right movemet
    }

    public void stopLeft() {
        left = false;             // Smooth move function for stopping left movemet
    }

    public void stopRight() {
        right = false;            // Smooth move function for stopping right movemet
    }

    public void update() {      // Running the movement function
        if (left == true) {           
            x -= speed;          // Moving x
        } else if (right == true) {   
            x += speed;       // Moving  x
        }

        
    }
    public int shipgetx(){
        return x + 95 / 2;    // Return ships position for bullet firing function
     }     
}
