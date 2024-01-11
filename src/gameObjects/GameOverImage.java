package gameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameOverImage extends GameObject {
    private BufferedImage GameOverImage;

    public GameOverImage(int x, int y, int width, int height) {
        super(x, y, width, height, Color.GRAY);

        try {
            URL imageUrl = getClass().getClassLoader().getResource("images/background_game_over_wood.png");
            if (imageUrl != null) {
                GameOverImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getGameOverImage() {
        return GameOverImage;
    }
}
