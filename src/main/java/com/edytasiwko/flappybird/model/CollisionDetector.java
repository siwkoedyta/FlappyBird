package com.edytasiwko.flappybird.model;

import java.util.Collection;

public class CollisionDetector {
    private final int width;
    private final int height;

    public CollisionDetector(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean check(GameObject bird, Collection<? extends GameObject> obstacles) {
        if (bird.getX() < 0 || (bird.getX() + bird.getWidth()) > width || bird.getY() < 0 || (bird.getY() + bird.getHeight() > height)) {
            return true;
        }

        for (GameObject obstacle : obstacles) {
            if (bird.getX() < obstacle.getX() + obstacle.getWidth() &&
                    bird.getX() + bird.getWidth() > obstacle.getX() &&
                    bird.getY() < obstacle.getY() + obstacle.getHeight() &&
                    bird.getY() + bird.getHeight() > obstacle.getY()) {
                return true;
            }
        }
        return false;
    }

}
