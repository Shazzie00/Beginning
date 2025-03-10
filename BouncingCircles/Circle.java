package Ogunseye;

import processing.core.PApplet;

public class Circle {
    private PApplet sketch;
    // This insatnce varibales are for my circle shape
    private float x;
    private float y;
    private float diameter;
    //moving circle
    private float vX;
    private float vY;
    //circle color
    private int circlecolor;

    //This Papplet sketch calles the sketch function of proccesing. And I request tht paramter of x and y coor which is
    // Set for my muse locations
    public Circle (PApplet sketch,float x, float y, float diameter, int circlecolor ){
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.circlecolor = circlecolor;
        this.vX =  (int)(Math.random()*9) + (-5);
        this.vY =  (int)(Math.random()*9)+ (-5);
    }

    // return and allow constructor to be accesible to get location
    public float getX (){return this.x;}
    public float getY (){return this.y;}
    // get diameter and color
    public float getDiameter (){return this.diameter;}
    public int getCirclecolor () {return this.circlecolor;}


    // add movemnts and checks boundaries
    public void move(){
        x += vX;
        y += vY ;
        if (x > sketch.width - (diameter /2 ) || x < (diameter/2) ) {
            vX = -vX;
        } else if (y > sketch.height - (diameter /2) || y < (diameter/2) ) {
            vY = -vY;

        }
    }

    // This method makes the tinyer circles fall down
    public void confetti (){
        x += vX;
        y += vY ;
    }

    // This method is  to show my circle
    public void display() {
        sketch.fill(circlecolor);
        // this represents the prarameter for my circle shapes. thier x and y
        sketch.circle(x, y, diameter);
    }



}