package Ogunseye;

import processing.core.PApplet;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

public class Points extends PApplet {
         // TWO MAIN ARRAYLIST
    // list  with big circle
    ArrayList<Circle> minicircles;
    // list with tiny circles
    ArrayList<Circle> tinyercircles;

         // MY COUNTER METHODS
    // counts how any circles created
    int spawnedcircle = 0;
    // counts circle removed
    int circleremoved = 0;
    // counts cleared screen
    int clearedscreen = 0;
    // initalize background to be later be changeable
    int backgroundcolor = 255;

         // THIS IS TO CONTROL THE  MOVING TEXT FOR IMPOSSIBLE MODE
    //textcolor is set to black
    int textcolor = 0;
    //enures middle of scrrent
    int textX = width/2;
    // make sure text is not visible in main screen until the velocity is called to increase
    int textY = -33;
    //velocity of the text
    int textVy ;

        //IMPOSSIBLE MODE
    // this boolean variable is used for the impossible mode mouse it will allow to  remove the tinier
    // circle once impossible mode become true. It is impossible because it's continually create a new circles.
    boolean impossiblemode = false;
    // changes size of the diameter




    public void settings() {
        size(1280, 720);
    }

    //This is my set function area.Wher I can intialize and set my things
    public void setup() {
        surface.setLocation(50, 50);
        //very important don't  remove
        minicircles = new ArrayList<>();
        tinyercircles = new ArrayList<>();
    }

    // Draw / display function
    public void draw() {
        // backgorund color is set to variable to be later editable
        background(backgroundcolor);

        textSize( 30);
        fill(textcolor);
        text("SPAWNED : " + spawnedcircle, 20, 40);
        text("DESTROYED : " + circleremoved, 20, 75);
        text("CLEARED : " + clearedscreen, 20, 110);

        text("PRESS 'R' TO RESET", 1005, 40);
        text("PRESS 'I' FOR IMPOSSIBLE MODE", 20, 700);

        text("LEFT CLICK TO REMOVE TINYER CIRCLES BEFORE THEY LEAVE THE SCREEN ", textX+30, textY);

        // adds velocity to the text
        textY += textVy;

        //always prints everything in the loops in both circle list
        for (Circle currentscircle : minicircles) {
            currentscircle.display();
            currentscircle.move();
        }

        for (Circle microcircle : tinyercircles) {
            microcircle.display();
            microcircle.confetti();
        }

    }

    // the purpose of these methods is to remove all circle
    public void RemoveCircle(ArrayList<Circle> maincirclelist){
        // The purpose of this is to look through each thing in the circle
        for (int i = maincirclelist.size()-1; i >= 0; i-- ){
            Circle thiscircle = maincirclelist.get(i);
            //for each circle check the distance between the mouse and this circle
            float distancechecker = dist(mouseX,mouseY,thiscircle.getX(), thiscircle.getY());

            // if statement to check if the mouse is on the circle
            if (distancechecker < thiscircle.getDiameter()/2){
                maincirclelist.remove(thiscircle);
                circleremoved++;
                backgroundcolor =  thiscircle.getCirclecolor();
                // this wil make sure if removing the tiny circle it won't create new circle
                if (maincirclelist == minicircles){
                        Tinyercircles(thiscircle);
                }
                else if (maincirclelist != minicircles){
                }

            }

        }
    }

    //Creates random tiny circles based on the circle that has been removed
    public void Tinyercircles( Circle thatcircle) {
        int tinyDiameter ;
        int confetti = (int)(Math.random()*7)+4;
        // if impossible mode is on make bigger tiny circle
        if (impossiblemode == true) {
            tinyDiameter = 15;;
        }
        else{
            tinyDiameter = 9;
        }

        // for loop to add a random amount of circle based on that removed circle
        for (int i = confetti; i > 0; i--) {
            Circle microcircle = new Circle(this, thatcircle.getX(), thatcircle.getY(),
                    tinyDiameter, thatcircle.getCirclecolor());
            tinyercircles.add(microcircle);
        }

    }

    //This method removes all circle in that list
    public void Clearfunction(ArrayList<Circle> emptythislist){
        for (int i = emptythislist.size()-1; i >= 0; i-- ) {
            // we are not creating but calling each cirlce to this circle
            Circle empty = emptythislist.get(i);
            emptythislist.remove(empty);
        }

    }

            //MAIN INTERACTIVE SECTION
    //mouse pressed to create and remove circle
    public void mousePressed() {
        // if mouse right  is clicked create circle
        if (mouseButton == LEFT) {
            int currentcolor = color((int) (Math.random()*256) + 1,(int)
                    (Math.random()*256)+ 1,(int)(Math.random()*256) + 1 );
            int diameter = (int)(Math.random()*90)+55;
            Circle mycurrentcircle = new Circle(this, mouseX, mouseY,diameter, currentcolor);
            minicircles.add(mycurrentcircle);
            spawnedcircle++;

        }
        // if mouse left is clicked remove circle
        else if (mouseButton ==  RIGHT) {
            RemoveCircle(minicircles);

            // if impossible mode is on then user can click and change the tiny circle
            if (impossiblemode == true) {
                RemoveCircle(tinyercircles);
            }
        }
        // if mouse center is clear all circles big and small
        else if (mouseButton == CENTER) {
            Clearfunction(minicircles);
            Clearfunction(tinyercircles);
            clearedscreen++;
        }
    }


    //key pressed function for either i or r
    @Override
    public void keyPressed( ) {
        // hard reset function clears and impossible mode is off
        if (keyCode == 'R' || keyCode == 'r'){
            Clearfunction(minicircles);
            Clearfunction(tinyercircles);
            clearedscreen = 0;
            spawnedcircle = 0;
            circleremoved = 0;
            backgroundcolor = 255;
            background(backgroundcolor);
            impossiblemode = false;

        }
        // impossible mode key
        else if (keyCode == 'I' || keyCode == 'i' ){
            if (impossiblemode == true) {
                System.out.println("Impossible mode off");
                impossiblemode = false;
            }
            else {
                System.out.println("Impossible mode on");
                Clearfunction(minicircles);
                Clearfunction(tinyercircles);
                background(backgroundcolor);
                textVy = 3;
                impossiblemode = true;
            }

        }
    }


    public static void main (String [] args ) {
        PApplet.main("Ogunseye.Points");
    }

}
