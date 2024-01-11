package com.edytasiwko.flappybird.model.bird;

import com.edytasiwko.flappybird.model.GameObject;

public final class Bird extends GameObject {
    private double speed = 30;

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public void move() {
        y = y - speed;
        if (speed > -30) speed = speed - 1.5;
    }

    public void jump() {
        if (speed + 25 > 20) {
            speed = 20;
        } else {
            speed += 25;
        }
    }
}
