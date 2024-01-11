package gameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Bird extends GameObject {
    private int speed = 10;
    private BufferedImage birdImage;


    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height, Color.GREEN);

        try {
            URL imageUrl = getClass().getClassLoader().getResource("images/bird.png");
            if (imageUrl != null) {
                birdImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getBirdImage() {
        return birdImage;
    }

    public void tick() {
        var dy = speed;
        y = y - dy;
        speed = speed - 1;
    }

    public void jump() {
        speed = 10;
    }
}
