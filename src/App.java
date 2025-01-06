
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.*;

public class App extends PApplet {
    // Declare objects for spaceship, bullets, invaders, and other game variables
    Spaceship first;
    ArrayList<Bullet> missles; // List of bullets fired by the spaceship
    ArrayList<Invador> otherside = new ArrayList<>(); // List of enemy invaders
    double timer; // Timer to track game duration
    int screen;   // Variable to track the current scree
    double timetracker; // Timer tracker for calculating game time
    double highscore; // Stores the highest score (time) achieved
    int direction = 1; // Direction of the invader's movement 
    // Main method to run the game
    public static void main(String[] args) {
        PApplet.main("App");
    }
    // Create enemy invaders in the game
    public void Createenemy() {
          // Loops to create a grid of enemies
        for (int Y = 5; Y <= 195; Y += 65) {
            for (int X = 100; X <= 1400; X += 80) {
                if (X >= 1300)  // Prevent the creation of enemies beyond the screen width
                    break;
                otherside.add(new Invador(X, Y, this)); // Add each invader to the otherside list
            }
        }
    }
 
    // Setting up obgects, states, and other functions
    public void setup() {
        screen = 0;  // Start screen
        first = new Spaceship(100, 675, 130, this); // Create the player's spaceship
        missles = new ArrayList<>(); // Initialize the list of missiles
        Createenemy();   // Call method to create the invaders
        timetracker = millis(); //Set type for timer and start it

    }
       // Read the high score from a file function
    public void ReadHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) { // Choosing file to read
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                highscore = Double.parseDouble(row);  // Read the high score from the file and set the varible to whats on the file
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());  // Error message function and Handling file reading errors
        }
    }
    // Set the canvas size for the game window
    public void settings() {
        size(1400, 800);  //Fuction to set size
    }
       // Main game loop for constant reapeated tasks and method for start sceen and screen and score functionality
    public void draw() {
        if (screen == 0) {
            background(0, 0, 255); // Show the start screen
            textSize(50); // Show the start screen
            fill(0, 255, 0); // Green text
            text("SPACE INVADERS!", 450, 150); //Title
            textSize(22);
            text("Welcome to the Space Invaders Game!", 450, 250);  // Instructions
            text("The goal is to shoot all invaders down as fast as you can.", 450, 300); // Instructions
            text("Control your ship using the left/right arrow keys.", 450, 350); // Instructions
            text("Press space to shoot bullets and start the game. Good luck!", 450, 400); // Instructions
        } else if (screen == 1) {  // Gameplay screen function
            gameplay();
        } else if (screen == 3) { // Endgame screen fucntion
            endgame();
            ReadHighScore();     // Update the high score after the game ends
            if (highscore==0||timer < highscore) { 
                highscore = timer; // Update the high score if the current game time is lower
                saveScore(); // Save the new high score
            }
        }
    }
   // Save the new high score method function
    public void saveScore() {
       
        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.println(highscore);  // Write the high score varile to the file
            writer.close(); // Closes the writer and saves the file
           
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");  // Handle file writing errors
            e.printStackTrace();
        }

    }
   // Main game method( helps to have it in a method so you can switch screens )
    public void gameplay() {
        background(0);  // Black background
        first.display();   // Display the  spaceship 
        first.update(); // Update spaceship position 
        timer = millis() - timetracker; // Calculate game time
        timer = ((int) timer / 100) / 10.0; // Format the timer to show seconds and then one point of milliseconds
        fill(255); // White text
        textSize(26);
        boolean shouldReverse = false; //Function for reversing of movement (note for some of the lines of code below I had chat GPT help me with logic which will be marked with a *)

        text("Time: " + timer, width - 179, 750);  // Display the current time

       
        if (otherside.size() == 0) { // Check if all invaders are destroyed
            screen = 3; // Transition to the endgame screen
        }
        for (int bIndex = 0; bIndex < missles.size(); bIndex++) { // Check for collisions between bullets and invaders with out concorent modification error
            Bullet b = missles.get(bIndex);
            for (int i = 0; i < otherside.size(); i++) {
                Invador enemy = otherside.get(i);
                if (enemy.checktouch(b)) {    // Check if the bullet hits the invader
                    missles.remove(b); // Remove the bullet that hit
                    otherside.remove(enemy);   // Remove the invader
                    break;
                }
            }
        }
         // Move and display invaders
        for (Invador invader : otherside) {
            invader.display();
            invader.move(direction);  // Move the invader in the current direction (CHAT GPT ) 

           
            if (invader.getX() <= -10 || invader.getX() >= width - 30 - invader.getWidth()) { // CHAT GPT helped me with calculations
                shouldReverse = true;  // Reverse direction when an invader hits the screen edges
            }
        }
        if (shouldReverse ) { // (Chat GPT) 
            direction *= -1;  // Reverse the invaders direction
        }
       // Display and move bullets
        for (int i = 0; i < missles.size(); i++) {
            Bullet bullet = missles.get(i);
            bullet.display();
            bullet.move();

            if (bullet.getY() < -45) { // Remove bullets that go off-screen for saving RAM 
                missles.remove(i);
                i--;
            }
        }
    }
     // Handle keyboard input
    public void keyPressed() {
        if (keyCode == LEFT) {  // Move the spaceship left
            first.moveLeft();
        } else if (keyCode == RIGHT) {
            first.moveRight();  // Move the spaceship right
        }
        if (key == ' ') { // Fire a bullet or start/restart the game
            if (screen == 0) {
                screen = 1;  // Start the game
            } else if (screen == 3) {
                restartGame();   // Restart the game after it ends
                screen = 1;
                ReadHighScore();

            } else {
                missles.add(new Bullet(first.shipgetx() - 3, 645, this));   // Fire a new bullet/declaring it
            }
        }
    }
     // Handle key release to stop spaceship movement ALL FOR SMOOTH MOVMENT
    public void keyReleased() {
        if (keyCode == LEFT) {
            first.stopLeft();   // Stop moving left
        } else if (keyCode == RIGHT) {
            first.stopRight();  // Stop moving right
        }
    }
   // Display the endgame screen method
    public void endgame() {
        background(0, 0, 255); // Blue background
        textSize(50);
        fill(0, 255, 0); // Green text
        text("Time: " + timer, 450, 250); // Display the game time
        text("Lowest time: " + highscore, 450, 300); // Time record using file reading
        text(" Hit Space to play again!", 450, 350); // Instructions
      

    }
    // Restart the game function to help game go back to old state(exept for score)
    public void restartGame() {
        
        timetracker = millis(); // Reset the timer
        missles.clear(); // Clear all existing bullets just incase
        otherside.clear();  // Clear all existing invaders clean up
        Createenemy(); // Create a new set of invaders
        first = new Spaceship(100, 675, 130, this);  // Reset the  spaceship stutus
        ReadHighScore(); // Update the high score just incase
    }

}
