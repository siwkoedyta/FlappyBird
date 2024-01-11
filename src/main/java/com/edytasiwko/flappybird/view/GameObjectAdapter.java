package com.edytasiwko.flappybird.view;

import com.edytasiwko.flappybird.model.bird.Bird;
import com.edytasiwko.flappybird.model.GameObject;
import com.edytasiwko.flappybird.model.tree.Tree;

import java.awt.*;

public class GameObjectAdapter {
    private final ImageFactory imageFactory = ImageFactory.instance();

    public Image get(GameObject gameObject) {
        return switch (gameObject) {
            case Bird b -> imageFactory.loadBird(b.getWidth() + 5, b.getHeight() + 5);
            case Tree t -> switch (t.getTreeType()) {
                case TOP -> imageFactory.loadTreeTop(t.getWidth(), t.getHeight());
                case BOTTOM -> imageFactory.loadTreeBottom(t.getWidth(), t.getHeight());
            };
            default -> throw new RuntimeException("Image not implemented");
        };
    }
}
