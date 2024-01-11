package gameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Obstacle extends GameObject {
    private double speed = 5;
    private BufferedImage obstacleImage;
    private boolean isTop;

    public Obstacle(int x, int y, int width, int height, boolean isTop) {
        super(x, y, width, height, Color.RED);
        this.isTop = isTop;

        try {
            String imageName = isTop ? "obstacle_top.png" : "obstacle_bottom.png";
            URL imageUrl = getClass().getClassLoader().getResource("images/" + imageName);
            if (imageUrl != null) {
                obstacleImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getObstacleImage() {
        return obstacleImage;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isTop() {
        return isTop;
    }

    public void tick() {
        x -= speed;
    }
}