import processing.core.PApplet;

public class Spaceship {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
   
    public Spaceship( int x, int y, int size, PApplet c) {
        this.x = x; 
        this.y = y;
        this.size = size; 
        canvas =c;
        
    }
   
   public void display(){
    canvas.fill(200,0,0);
    canvas.circle(x, y, size);
   
   }

  
    }



