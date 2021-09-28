package me.adam.processing.circle;

import java.util.Timer;
import java.util.TimerTask;

import static me.adam.processing.Utils.getRandomInt;
import static me.adam.processing.circle.Circle.createCircle;
import static me.adam.processing.circle.Circle.getAllCircles;

public class Control {
    public static void initialize() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                Object[] circles = getAllCircles().toArray();
                Circle circle = (Circle) circles[getRandomInt(0, circles.length)];

                explode(circle);
                circle.delete();
            }
        }, 1000, Math.toIntExact(Math.round(Math.random() * 60)) * 1000);
    }


    public static void explode(Circle circle) {
        for (int i = 0; i<4; i++) {
            createCircle(getAllCircles().size() + 2, circle.getX(), circle.getY(), circle.getRadius() / 2, circle.getR(), circle.getG(), circle.getB());
        }
    }
}
