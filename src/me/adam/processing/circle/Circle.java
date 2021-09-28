package me.adam.processing.circle;

import me.adam.processing.Utils;
import processing.core.PVector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Circle {
    private static final Set<Circle> CIRCLES = new HashSet<>();
    private static final Map<Integer, Circle> ID_CIRCLE = new HashMap<>();

    public static boolean createCircle(int id, float x, float y, int radius, int r, int g, int b) {
        if (ID_CIRCLE.containsKey(id))
            return false;

        Circle circle = new Circle(id, x, y, radius, r, g, b);

        CIRCLES.add(circle);
        ID_CIRCLE.put(id, circle);
        return true;
    }

    public static Circle getCircle(Integer id) {
        return ID_CIRCLE.get(id);
    }

    public static Set<Circle> getAllCircles() {
        return CIRCLES;
    }



    private final int id;

    private PVector location;
    private PVector velocity;
    private PVector gravity;

    private final int radius;
    private final float m;

    private final int r;
    private final int g;
    private final int b;

    private Circle(int id, float x, float y, int radius, int r, int g, int b) {
        this.id = id;
        this.radius = radius;
        this.m = radius*.1f;
        this.r = r;
        this.g = g;
        this.b = b;
        this.location = new PVector(x, y);
        this.velocity = new PVector(Utils.getRandomFloat(-10, 10), Utils.getRandomFloat(-10, 10));
        this.gravity = new PVector(0,3.0f);
    }



    public Integer getId() {
        return this.id;
    }

    public float[] getPosition() {
        return new float[] {this.location.x, this.location.y};
    }
    public float getX() {
        return this.location.x;
    }
    public float getY() {
        return this.location.y;
    }

    public PVector getLocation() {
        return this.location;
    }
    public PVector getVelocity() {
        return this.velocity;
    }
    public PVector getGravity() {
        return this.gravity;
    }

    public int getRadius() {
        return this.radius;
    }

    public int[] getColor() {
        return new int[] {this.r, this.g, this.b};
    }
    public int getR() {
        return this.r;
    }
    public int getG() {
        return this.g;
    }
    public int getB() {
        return this.b;
    }



    public boolean delete() {
        if (!ID_CIRCLE.containsKey(this.getId()))
            return false;
        CIRCLES.remove(this);
        ID_CIRCLE.remove(this.getId());
        return true;
    }
}
