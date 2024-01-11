package com.edytasiwko.flappybird.model.tree;

import com.edytasiwko.flappybird.model.GameObject;

public class Tree extends GameObject {
    private double speed;
    private TreeType treeType;

    private Tree(double x, double y, int width, int height, double speed, TreeType treeType) {
        super(x, y, width, height);
        this.speed = speed;
        this.treeType = treeType;
    }

    public void move() {
        x -= speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void incrementSpeed(double delta) {
        this.speed += delta;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public static class Builder {
        private TreeType treeType;
        private double x = 0.0;
        private double y = 0.0;
        private int width = 100;
        private int height = 100;
        private double speed = 1;

        private Builder() {
        }

        public static Builder top() {
            Builder builder = new Builder();
            builder.treeType = TreeType.TOP;
            return builder;
        }

        public static Builder bottom() {
            Builder builder = new Builder();
            builder.treeType = TreeType.BOTTOM;
            return builder;
        }

        public Builder x(double x) {
            this.x = x;
            return this;
        }

        public Builder y(double y) {
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder speed(double speed) {
            this.speed = speed;
            return this;
        }

        public Tree build() {
            return new Tree(
                    x,
                    y,
                    width,
                    height,
                    speed,
                    treeType
            );
        }


    }
}