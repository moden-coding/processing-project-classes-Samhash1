import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    Spaceship first;
    ArrayList<Bullet> missles;
    int highscore;
    // ArrayList<Invador> enemy;
    ArrayList<Invador> otherside = new ArrayList<>();
    double timer;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void Createenemy() {
        for (int Y = 5; Y <= 195; Y += 65) {
            for (int X = 100; X <= 1400; X += 80) {
                if (X >= 1300) {
                    break;
                }
                otherside.add(new Invador(X, Y, this));
            }
        }
    }

    public void setup() {
        first = new Spaceship(100, 675, 130, this);
        missles = new ArrayList<>();
        // third = new Invador(100, 20, this);
        Createenemy();
        ReadHighScore();

    }
   public void ReadHighScore(){
    try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

        // we read the file until all lines have been read
        while (scanner.hasNextLine()) {
            // we read one line
            String row = scanner.nextLine();
            // we print the line that we read
           highscore=Integer.valueOf(row);
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
   }


    public void settings() {
        size(1400, 800);
    }

    public void draw() {
        background(0, 0, 0);
        first.display();
        first.update();
        timer= millis();
        fill(255);
        textSize(50);
        text( ""+ timer, width -150, 780 );
        
        for ( int bIndex =0; bIndex < missles.size(); bIndex++) {
            Bullet b = missles.get(bIndex);
            for (int i =0; i < otherside.size(); i ++) {
               Invador enemy = otherside.get(i);
                if (enemy.checktouch(b)) {
                    missles.remove(b);
                    otherside.remove(enemy);
                }
            }
        }
        for (int i = 0; i < otherside.size(); i++) {
            Invador One = otherside.get(i);
            One.display();

            // if (second != null && One.checktouch(second)) {
            //     otherside.remove(One);

            // }
        }

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

    public void keyPressed() {
        if (keyCode == LEFT) {
            first.moveLeft();
        } else if (keyCode == RIGHT) {
            first.moveRight();
        }
        if (key == ' ') {
            Bullet temp = new Bullet(first.shipgetx() - 3, 645, this);
            missles.add(temp);
        }
    }

    public void keyReleased() {
        if (keyCode == LEFT) {
            first.stopLeft();
        } else if (keyCode == RIGHT) {
            first.stopRight();
        }
    }

}
