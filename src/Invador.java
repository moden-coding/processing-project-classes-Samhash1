
import processing.core.*;

public class Invador {
    private int x; // x-coordinate of the invader
    private int y;  // y-coordinate of the invader
    private PImage photo; // Allows for a photo to be imported representing invador
    private PApplet canvas;
    private int speed;
    private int width; // Speed at which the invader moves horizontally
    int sizexy = 60; // Size of the invader (height and width)

    // Constructor to initialize an invader at a specific position
    public Invador(int x, int y, PApplet c) {
        this.x = x; // Set the initial x position
        this.y = y;  // Set the initial y position
        this.canvas = c;
        this.photo = canvas.loadImage("Enemy.png"); // Load the image for the invader from the file 
        this.speed = 2;  // Set the speed of movement (horizontal)
        this.width = 40; // Set the width of the invader
    }
     // Method to display the invader on the screen
    public void display() {
        canvas.image(photo, x, y, sizexy, sizexy); // Draw the invader image at position (x, y) with a size of sizexy
    }

    public boolean checktouch(Bullet defender) {
        float distancefromobgect = canvas.dist(x, y, defender.getX(), defender.getY());
        return distancefromobgect < sizexy;
    }

    public void move(int direction) { // CHAT GPT
        x += direction * speed; // Use shared direction
    }

   
    public int getX() {
        return x;        // Function to get x position of invador
    }

    public int getWidth() {
        return width;    // Function to get width of invador for moving function
    }
}


