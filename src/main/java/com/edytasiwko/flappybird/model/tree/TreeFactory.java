package com.edytasiwko.flappybird.model.tree;

import java.util.Optional;

public interface TreeFactory {
    Optional<Tree> create(int gapCenter, int gapSize, double speed);
}
