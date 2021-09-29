package me.adam.processing.circle;

import java.util.Timer;
import java.util.TimerTask;

import static me.adam.processing.Utils.getRandomInt;
import static me.adam.processing.circle.Circle.createCircle;
import static me.adam.processing.circle.Circle.getAllCircles;

public class Control {
    public static int time;

    public static void initialize() {
        int period = Math.toIntExact(Math.round(Math.random() * 20)) * 1000;
        time = period / 1000;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                Object[] circles = getAllCircles().toArray();
                Circle circle = (Circle) circles[getRandomInt(0, circles.length)];

                explode(circle);
                circle.delete();
                time = period/1000;
            }
        }, period, period);

        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {
                time--;
            }
        }, 0, 1000);
    }

    public static void explode(Circle circle) {
        for (int i = 0; i<4; i++) {
            createCircle(getAllCircles().size() + 2, circle.getX(), circle.getY(), circle.getRadius() / 2, circle.getR(), circle.getG(), circle.getB());
        }
    }


}
