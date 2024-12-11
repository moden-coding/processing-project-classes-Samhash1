import processing.core.*;

public class Spaceship {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    boolean left, right = false;
    private PImage photo;
    private int speed = 6;

    public Spaceship(int x, int y, int size, PApplet c) {
        this.x = x;
        this.y = y;
        this.size = size;
        canvas = c;
        photo = canvas.loadImage("spaceship.png");

    }

    public void display() {
    
        canvas.image(photo, x, y, 95, 95);

    }

    public void moveLeft() {
        left = true;
    }

    public void moveRight() {
        right = true;
    }

    public void stopLeft() {
        left = false;
    }

    public void stopRight() {
        right = false;
    }

    public void update() {
        if (left == true) {
            x -= speed;
        } else if (right == true) {
            x += speed;
        }

        
    }
    public int shipgetx(){
        return x + 95 / 2;
     }
}
