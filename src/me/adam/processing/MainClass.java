package me.adam.processing;

import me.adam.processing.circle.Circle;
import me.adam.processing.circle.Control;
import processing.core.*;

import static me.adam.processing.circle.Circle.*;

public class MainClass extends PApplet {

    public void settings() {
        fullScreen();
        smooth(8);
    }

    public void setup() {
        Control.initialize();
        createCircle(1, 200, 200, 50, 255, 0, 0);
        createCircle(2, 800, 100, 150, 0, 255, 0);
        createCircle(3, 600, 800, 50, 0, 0, 255);
        frameRate(120);
    }

    public void draw() {
        background(0);

        for (Circle circle : getAllCircles()) {
            PVector location = circle.getLocation();

            checkBoundingBox(circle, location);
            update(circle);
            int[] color = circle.getColor();
            fill(color[0], color[1], color[2]);
            ellipse(location.x, location.y, circle.getRadius(), circle.getRadius());
            drawId(circle);
        }
    }







    // DRAW UTILS

    public void drawId(Circle circle) {
        fill(255);
        text(String.valueOf(circle.getId()), circle.getX() - 7, circle.getY() + 7);
        textSize(24);
    }

    public void checkBoundingBox(Circle circle, PVector location) {
        if (location.x + (circle.getRadius() / 2) > width) {
            circle.getVelocity().x = circle.getVelocity().x * -0.70f;
            location.x = width - (circle.getRadius() / 2);
        } else if (location.x - (circle.getRadius() / 2) < 0) {
            circle.getVelocity().x = circle.getVelocity().x * -0.70f;
            location.x = 0 + (circle.getRadius() / 2);
        }

        if (location.y + (circle.getRadius() / 2) > height) {
            circle.getVelocity().y = circle.getVelocity().y * -0.70f;
            location.y = height - (circle.getRadius() / 2);
        }
    }

    public void update(Circle circle) {
        circle.getLocation().add(circle.getVelocity());
        circle.getVelocity().add(circle.getGravity());
    }

    public static void main(String[] args) {
        PApplet.main(new String[] {"me.adam.processing.MainClass" });
    }
}


