package me.adam.processing;

public class Utils {
    public static float getRandomFloat(int min, int max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    public static int getRandomInt(int min, int max) {
        System.out.println(Math.toIntExact((long) ((Math.random() * (max - min)) + min)));
        return 0;
    }
}
