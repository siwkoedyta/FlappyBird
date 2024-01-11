package com.edytasiwko.flappybird.model;

import com.edytasiwko.flappybird.model.bird.Bird;
import com.edytasiwko.flappybird.model.score.Score;
import com.edytasiwko.flappybird.model.tree.Tree;
import com.edytasiwko.flappybird.model.tree.TreesRefresher;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bird bird;
    private final List<Tree> trees;
    private final Score score;

    private final TreesRefresher treesRefresher;
    private final CollisionDetector collisionDetector;

    private boolean gameOver = false;

    public Game(int width, int height) {
        bird = new Bird(100, height / 2, width / 10, (int) (width * 0.76 / 10));
        trees = new ArrayList<>();
        score = new Score();
        treesRefresher = new TreesRefresher(width, height);
        collisionDetector = new CollisionDetector(width, height);
    }

    public void jump() {
        bird.jump();
    }

    public void tick() {
        bird.move();
        treesRefresher.refreshTrees(trees);
        if (collisionDetector.check(bird, trees)) {
            gameOver = true;
            throw new GameOverException();
        }

        int prevScore = score.getScore();
        score.checkPoints(bird, trees);

        if (prevScore != score.getScore()) speedUpTrees();
    }

    public Bird getBird() {
        return bird;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public Score getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void speedUpTrees() {
        trees.forEach(t -> t.incrementSpeed(0.1));
    }
}