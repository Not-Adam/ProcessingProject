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
        createCircle(1, 200, 200, 300, 255, 0, 0);
        createCircle(2, 800, 100, 150, 0, 255, 0);
        createCircle(3, 600, 800, 300, 0, 0, 255);
        frameRate(60);
    }

    public void draw() {
        background(0);

        for (Circle circle : getAllCircles()) {
            PVector location = circle.getLocation();
            PVector velocity = circle.getVelocity();
            PVector gravity = circle.getGravity();

            if ((location.x > width) || (location.x < 0)) {
                velocity.x = velocity.x * -1;
            }
            if (location.y > height) {
                velocity.y = velocity.y * -0.85f;
                location.y = height;
            }

            location.add(velocity);
            velocity.add(gravity);

            int[] color = circle.getColor();
            fill(color[0], color[1], color[2]);
            ellipse(location.x, location.y, circle.getRadius(), circle.getRadius());
            drawId(circle);
        }
    }

    public void drawId(Circle circle) {
        fill(255);
        text(String.valueOf(circle.getId()), circle.getX() - 7, circle.getY() + 7);
        textSize(24);
    }

    public static void main(String[] args) {
        PApplet.main(new String[] {"me.adam.processing.MainClass" });
    }
}


