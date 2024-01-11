package com.edytasiwko.flappybird.controller;

import com.edytasiwko.flappybird.model.Game;
import com.edytasiwko.flappybird.model.GameOverException;
import com.edytasiwko.flappybird.view.GameRenderer;
import com.edytasiwko.flappybird.view.GameWindowFacade;

public class GameController {
    private final int width = 720;
    public final int height = 1280;

    private final int fps = 30;
    private final long frameDurationMs = 1000L / fps;

    private final GameWindowFacade gameWindow;
    private final GameRenderer gameRenderer;

    private Game game;
    private boolean gameOver = false;

    public GameController() {
        this.gameWindow = new GameWindowFacade(width, height);
        this.gameRenderer = new GameRenderer();
        this.game = new Game(width, height);

        gameWindow.subscribeSpacePressed(this::onSpacePressed);
    }

    public void start() {
        long lastRefresh = System.currentTimeMillis();

        while (true) {
            if (gameOver) {
                gameRenderer.render(gameWindow, game.getScore().getScore());
            } else {
                try {
                    game.tick();
                    gameRenderer.render(gameWindow, game);
                } catch (GameOverException e) {
                    gameOver = true;
                }
            }

            long now = System.currentTimeMillis();
            long delay = frameDurationMs - (now - lastRefresh);
            lastRefresh += frameDurationMs;
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Game interrupted", e);
                }
            }
        }
    }

    private void onSpacePressed() {
        if (game.isGameOver()) {
            game = new Game(width, height);
            gameOver = false;
        } else {
            game.jump();
        }
    }
}