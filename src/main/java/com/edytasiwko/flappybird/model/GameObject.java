package com.edytasiwko.flappybird.model;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected int width;
    protected int height;

    public GameObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
