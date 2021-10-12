package me.adam.processing;

import me.adam.processing.circle.Circle;
import me.adam.processing.circle.Control;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Timer;
import java.util.TimerTask;

import static me.adam.processing.circle.Circle.createCircle;
import static me.adam.processing.circle.Circle.getAllCircles;
import static me.adam.processing.circle.Control.*;

public class MainClass extends PApplet {

    public void settings() {
        fullScreen();
        smooth(8);
    }

    public void setup() {

        for (int i = 1; i <= 48; i++) {
            String num = String.valueOf(i);
            if (num.length() == 1) num = "0" + num;
            PImage img = loadImage("C:\\Users\\rabu6\\Desktop\\Comp Sci A\\Processing Project\\src\\me\\adam\\processing\\resources\\exp_" + num +".png");
            explosionSequence.add(img);
        }

        createCircle(1, 200, 200, 50, 255, 0, 0);
        createCircle(2, 800, 100, 150, 0, 255, 0);
        createCircle(3, 600, 800, 50, 0, 0, 255);

        Control.initialize();

        frameRate(120);
    }

    public void draw() {
        background(20);

        if (time == 0) {
            runExplosionSequence(getNextCircle());
        }

        for (Circle circle : getAllCircles()) {
            PVector location = circle.getLocation();

            checkCircleBoundingBox(circle, location);
            updateCircle(circle);
            int[] color = circle.getColor();
            fill(color[0], color[1], color[2]);
            ellipse(location.x, location.y, circle.getRadius(), circle.getRadius());
            drawCircleId(circle);
        }

        drawText(String.valueOf(time), 50, 50);

    }







    // DRAW UTILS

    public void runExplosionSequence(Circle circle) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                for (PImage img : explosionSequence) {
                    image(img, circle.getX() - circle.getRadius() * 3, circle.getY() - circle.getRadius() * 2);
                }
                timer.cancel();
            }
        }, 0, 200);
    }

    public void drawText(String text, float x, float y) {
        fill(255);
        text(text, x, y);
        textSize(30);
    }

    public void drawCircleId(Circle circle) {
        fill(255);
        text(String.valueOf(circle.getId()), circle.getX() - 7, circle.getY() + 7);
        textSize(24);
    }

    public void checkCircleBoundingBox(Circle circle, PVector location) {
        if (location.x + (circle.getRadius() / 2) > width) {
            circle.getVelocity().x = circle.getVelocity().x * -0.8f;
            location.x = width - (circle.getRadius() / 2);
        } else if (location.x - (circle.getRadius() / 2) < 0) {
            circle.getVelocity().x = circle.getVelocity().x * -0.8f;
            location.x = 0 + (circle.getRadius() / 2);
        }

        if (location.y + (circle.getRadius() / 2) > height) {
            circle.getVelocity().y = circle.getVelocity().y * -0.8f;
            location.y = height - (circle.getRadius() / 2);
        }
    }

    public void updateCircle(Circle circle) {
        circle.getLocation().add(circle.getVelocity());
        circle.getVelocity().add(circle.getGravity());
    }

    public static void main(String[] args) {
        PApplet.main(new String[] {"me.adam.processing.MainClass" });
    }
}


