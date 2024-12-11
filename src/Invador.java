import processing.core.*;

public class Invador {
   
    private int x;
    private int y;
    private PImage photo;
    private PApplet canvas;
   
    public Invador(int x, int y, PApplet c){
        this.x = x;
        this.y = y;
        canvas = c;
        photo = canvas.loadImage("Enemy.png");
    }
    public void display() {
    
        canvas.image(photo, x, y, 60, 60);

    }









}


