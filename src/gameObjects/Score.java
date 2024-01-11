package gameObjects;

import gameObjects.GameObject;

import java.awt.*;

public class Score extends GameObject {
    private int score = 0;

    public Score(int x, int y, int width, int height) {
        super(x, y, width, height, Color.BLACK);
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}