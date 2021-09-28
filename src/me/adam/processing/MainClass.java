package me.adam.processing;

import processing.core.*;

import static me.adam.processing.Circle.createCircle;
import static me.adam.processing.Circle.getAllCircles;

public class MainClass extends PApplet {

    public void settings() {
        fullScreen();
        smooth(8);
    }

    public void setup() {
        createCircle(1, 200, 200, 50, 255, 0, 0);
        createCircle(2, 800, 100, 150, 0, 255, 0);
        createCircle(3, 600, 800, 50, 0, 0, 255);
    }

    public void draw() {
        background(0);

        for (Circle circle : getAllCircles()) {
            PVector location = circle.getLocation();
            PVector velocity = circle.getVelocity();
            PVector gravity = circle.getGravity();

            location.add(velocity);

            velocity.add(gravity);

            if ((location.x > width) || (location.x < 0)) {
                velocity.x = velocity.x * -1;
            }
            if (location.y > height) {
                velocity.y = velocity.y * -0.95f;
                location.y = height;
            }

            int[] color = circle.getColor();
            fill(color[0], color[1], color[2]);
            ellipse(location.x, location.y, circle.getRadius(), circle.getRadius());
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] {"me.adam.processing.MainClass" });
    }
}


