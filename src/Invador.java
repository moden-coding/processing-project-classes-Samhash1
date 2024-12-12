import processing.core.*;

public class Invador {
   
    private int x;
    private int y;
    private PImage photo;
    private PApplet canvas;
    int sizexy =60;
    public Invador(int x, int y, PApplet c){
        this.x = x;
        this.y = y;
        canvas = c;
        photo = canvas.loadImage("Enemy.png");
    }
    public void display() {
    
        canvas.image(photo, x, y, sizexy, sizexy);

    }

    public boolean checktouch(Bullet defender){
     float distancefromobgect = canvas.dist(x, y, defender.getX(), defender.getY() );
        if ( distancefromobgect < sizexy ){
            return true;
        } else{
            return false;
        }
    }









}


