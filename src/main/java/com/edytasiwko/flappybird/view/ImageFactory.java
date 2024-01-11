package com.edytasiwko.flappybird.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class ImageFactory {
    private static ImageFactory instance = null;

    private Map<String, Image> cache = new HashMap<>();

    private ImageFactory() {
    }

    public static ImageFactory instance() {
        if (instance == null) {
            instance = new ImageFactory();
        }
        return instance;
    }

    public Image loadBackground(int width, int height) {
        return getOrLoad("background.png", width, height);
    }

    public Image loadGameOver(int width, int height) {
        return getOrLoad("game_over.png", width, height);
    }

    public Image loadBird(int width, int height) {
        return getOrLoad("bird.png", width, height);
    }

    public Image loadTreeBottom(int width, int height) {
        return getOrLoad("tree_bottom.png", width, height);
    }

    public Image loadTreeTop(int width, int height) {
        return getOrLoad("tree_top.png", width, height);
    }

    private Image getOrLoad(String name, int width, int height) {
        String cacheName = name + "_" + width + "x" + height;
        Image image = cache.get(cacheName);
        if (image == null) {
            image = load(name).getScaledInstance(width, height, Image.SCALE_FAST);
            cache.put(cacheName, image);
        }
        return image;
    }

    private Image getOrLoad(String name) {
        Image image = cache.get(name);
        if (image == null) {
            image = load(name);
            cache.put(name, image);
        }
        return image;
    }

    private BufferedImage load(String name) {
        try {
            URL imageUrl = getClass().getClassLoader().getResource("images/" + name);
            if (imageUrl == null) {
                throw new RuntimeException("Image not found: " + name);
            }
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Image load error", e);
        }
    }

}
