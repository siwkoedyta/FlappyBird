import gameObjects.Bird;
import gameObjects.GameOverImage;
import gameObjects.Obstacle;
import gameObjects.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Renderer {
    private final JFrame frame ;
    private final Canvas canvas;
    private int score;
    private BufferedImage backgroundImage;
    private Game game;
    private Color customColor = new Color(
            Integer.parseInt("3c", 16),
            Integer.parseInt("1a", 16),
            Integer.parseInt("0b", 16),
            Integer.parseInt("ff", 16)
    ); 


    public Renderer(int width, int height, Consumer<KeyEvent> onKeyPressed,Game game) {
        this.score = 0;
        this.game = game;

        frame = new JFrame("Flappy Bird");
        frame.pack();
        frame.setSize(width, height);
//        frame.setLocationRelativeTo(null); //polozenie okna na środek ekranu
        frame.setResizable(false); //uniemożliwia zmianę rozmiaru okna przez użytkownika
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true); //okno jako widoczne
        frame.requestFocus();

        canvas = new Canvas();

        frame.add(canvas);

        canvas.createBufferStrategy(3);  //metoda 3 buforów,rysowanie na jednym, dwa do przechowywania poprzednich klatek

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onKeyPressed.accept(e);
            }
        });

        try {
            // Wczytaj obraz tła z pliku
            URL imageUrl = getClass().getClassLoader().getResource("images/background.png");
            if (imageUrl != null) {
                backgroundImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Collection<GameObject> objects) {
        var bufferStrategy = canvas.getBufferStrategy();
        var graphics = bufferStrategy.getDrawGraphics();

        graphics.drawImage(backgroundImage, 0, 0, frame.getWidth(), frame.getHeight(), null);

        objects.forEach(o -> render(graphics, o));

        renderScore(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    private void render(Graphics graphics, GameObject object) {
        if (object instanceof Bird) {
            BufferedImage birdImage = ((Bird) object).getBirdImage();
            graphics.drawImage(birdImage, object.getX(), object.getY(), object.getWidth(), object.getHeight(), null);
        } else if (object instanceof Obstacle) {
            BufferedImage obstacleImage = ((Obstacle) object).getObstacleImage();
            graphics.drawImage(obstacleImage, object.getX(), object.getY(), object.getWidth(), object.getHeight(), null);
        } else if (object instanceof GameOverImage) {
            BufferedImage gameOverImage = ((GameOverImage) object).getGameOverImage();
            graphics.drawImage(gameOverImage, object.getX(), object.getY(), object.getWidth(), object.getHeight(), null);
        } else {
            graphics.setColor(object.getColor());
            graphics.fillRect(object.getX(), object.getY(), object.getWidth(), object.getHeight());
        }
    }

    private void renderScore(Graphics graphics) {
        if (!game.isGameOver()) {
            graphics.setColor(customColor);
            graphics.setFont(new Font("Arial", Font.PLAIN, 20));
            graphics.drawString("Score: " + score, 10, 30);
        } else {
            graphics.setColor(customColor);
            graphics.setFont(new Font("Arial", Font.BOLD, 50));
            graphics.drawString(String.valueOf(score), 310, 410);
        }
    }

    public void updateScore(int newScore) {
        this.score = newScore;
        frame.repaint();
    }

}
