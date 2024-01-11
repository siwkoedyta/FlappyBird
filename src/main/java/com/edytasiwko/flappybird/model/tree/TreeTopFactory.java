package com.edytasiwko.flappybird.model.tree;

import java.util.Optional;

public class TreeTopFactory implements TreeFactory {
    private final int width;

    public TreeTopFactory(int width) {
        this.width = width;
    }

    public Optional<Tree> create(int gapCenter, int gapSize, double speed) {
        int treeHeight = gapCenter - gapSize / 2;
        if (treeHeight <= 0) return Optional.empty();

        return Optional.of(Tree.Builder.top()
                .x(width)
                .y(0)
                .width(160)
                .height(treeHeight)
                .speed(speed)
                .build());
    }
}
