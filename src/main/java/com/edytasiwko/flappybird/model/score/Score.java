package com.edytasiwko.flappybird.model.score;

import com.edytasiwko.flappybird.model.bird.Bird;
import com.edytasiwko.flappybird.model.tree.Tree;

import java.util.List;

public class Score {
    private int score = 0;
    private int firstTreeHeight = 0;

    public int getScore() {
        return score;
    }

    public void checkPoints(Bird bird, List<Tree> trees) {
        if (!trees.isEmpty()) {
            Tree firstTree = trees.getFirst();
            if (firstTreeHeight != firstTree.getHeight() && (int) bird.getX() > (int) (firstTree.getX() + firstTree.getWidth())) {
                score++;
                firstTreeHeight = firstTree.getHeight();
            }
        }
    }
}