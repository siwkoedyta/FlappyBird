package com.edytasiwko.flappybird.model.tree;

import java.util.Optional;

public class TreeBottomFactory implements TreeFactory {
    private final int width;
    private final int height;

    public TreeBottomFactory(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Optional<Tree> create(int gapCenter, int gapSize, double speed) {
        int treeHeight = height - gapCenter - gapSize / 2;
        if (treeHeight <= 0) return Optional.empty();

        return Optional.of(Tree.Builder.bottom()
                .x(width)
                .y(gapCenter + gapSize / 2)
                .width(160)
                .height(treeHeight)
                .speed(speed)
                .build());
    }
}
