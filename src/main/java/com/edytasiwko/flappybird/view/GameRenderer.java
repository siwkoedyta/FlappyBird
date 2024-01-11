package com.edytasiwko.flappybird.view;

import com.edytasiwko.flappybird.model.Game;
import com.edytasiwko.flappybird.model.GameObject;

public class GameRenderer {
    private final ImageFactory imageFactory = ImageFactory.instance();
    private final GameObjectAdapter gameObjectAdapter = new GameObjectAdapter();

    public void render(GameWindowFacade window, int score) {
        window.renderImage(imageFactory.loadBackground(window.getWidth(), window.getHeight()), 0, 0);

        int gameOverWidth = (int) (window.getHeight() / 2 * 0.65);
        int gameOverHeight = window.getHeight() / 2;
        window.renderImage(imageFactory.loadGameOver(gameOverWidth, gameOverHeight), window.getWidth() / 2 - gameOverWidth / 2, 0);
        window.renderText(String.valueOf(score), window.getWidth() / 2 + gameOverWidth / 8, (int) (gameOverHeight * 0.68), 50);
        window.renderFinalize();
    }

    public void render(GameWindowFacade window, Game game) {
        window.renderImage(imageFactory.loadBackground(window.getWidth(), window.getHeight()), 0, 0);
        renderGameObject(window, game.getBird());
        game.getTrees().forEach(t -> renderGameObject(window, t));
        window.renderText(String.valueOf(game.getScore().getScore()), 10, 60, 50);
        window.renderFinalize();
    }

    private void renderGameObject(GameWindowFacade window, GameObject obj) {
        window.renderImage(gameObjectAdapter.get(obj), (int) obj.getX(), (int) obj.getY());
    }
}
