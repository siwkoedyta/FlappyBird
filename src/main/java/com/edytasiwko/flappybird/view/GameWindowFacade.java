package com.edytasiwko.flappybird.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameWindowFacade {
    private final int width;
    private final int height;

    private final JFrame frame;
    private final Canvas canvas;

    private final Color fontColor = new Color(
            Integer.parseInt("3c", 16),
            Integer.parseInt("1a", 16),
            Integer.parseInt("0b", 16),
            Integer.parseInt("ff", 16)
    );


    public GameWindowFacade(int width, int height) {
        this.width = width;
        this.height = height;

        frame = new JFrame("Flappy Bird");
        frame.pack();
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();

        canvas = new Canvas();

        frame.add(canvas);
        canvas.createBufferStrategy(2);
    }

    public void renderImage(Image image, int x, int y) {
        var graphics = canvas.getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, x, y, null);
    }

    public void renderText(String text, int x, int y, int size) {
        var graphics = canvas.getBufferStrategy().getDrawGraphics();
        graphics.setColor(fontColor);
        graphics.setFont(new Font("Arial", Font.PLAIN, size));
        graphics.drawString(text, x, y);
    }

    public void renderFinalize() {
        var bufferStrategy = canvas.getBufferStrategy();
        var graphics = bufferStrategy.getDrawGraphics();
        graphics.dispose();
        bufferStrategy.show();
    }

    public void subscribeSpacePressed(Runnable action) {
        canvas.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyChar() == ' ') action.run();
                    }
                }
        );
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
