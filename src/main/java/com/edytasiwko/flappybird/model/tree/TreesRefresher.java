package com.edytasiwko.flappybird.model.tree;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TreesRefresher {
    private final int horizontalGap = 450;
    private final int minVerticalGap = 250;

    private final int width;
    private final int height;
    private final TreeFactory treeTopFactory;
    private final TreeFactory treeBottomFactory;

    public TreesRefresher(int width, int height) {
        this.width = width;
        this.height = height;
        this.treeTopFactory = new TreeTopFactory(width);
        this.treeBottomFactory = new TreeBottomFactory(width, height);
    }

    public void refreshTrees(List<Tree> presentTrees) {
        presentTrees.removeIf(this::isOffScreen);
        presentTrees.addAll(createNewTrees(presentTrees));
        presentTrees.forEach(Tree::move);
    }

    private List<Tree> createNewTrees(List<Tree> trees) {
        if (trees.isEmpty() || trees.getLast().getX() < width - horizontalGap) {
            int gapCenter = (int) (Math.random() * height);
            int gapSize = minVerticalGap + (int) (Math.random() * height / 2);

            double speed = trees.isEmpty() ? 3.0 : trees.getLast().getSpeed();

            return Stream.of(
                            treeTopFactory.create(gapCenter, gapSize, speed),
                            treeBottomFactory.create(gapCenter, gapSize, speed)
                    ).filter(Optional::isPresent)
                    .map(Optional::get).toList();
        } else {
            return List.of();
        }
    }

    private boolean isOffScreen(Tree t) {
        return t.getX() + t.getWidth() < 0;
    }
}
