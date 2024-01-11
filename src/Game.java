import gameObjects.Bird;
import gameObjects.GameObject;
import gameObjects.GameOverImage;
import gameObjects.Obstacle;
import gameObjects.Score;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game implements Runnable {
    private static final int WIDTH = 500;
    public static final int HEIGHT = 1000;

    private final Bird bird;
    private final List<Obstacle> obstacles;
    private final Score score;
    private final Renderer renderer;
    private final CollisionDetector collisionDetector;
    private boolean gameOver = false;
    private boolean obstaclePassed = false;


    public boolean isGameOver() {
        return gameOver;
    }

    public Game() {
        bird = new Bird(100, 100, 70, 50);
        obstacles = new ArrayList<>();
        score = new Score(10, 30, 100, 30);
        renderer = new Renderer(WIDTH, HEIGHT, this::handleKeyPressed,this);
        collisionDetector = new CollisionDetector(WIDTH, HEIGHT);
    }

    @Override
    public void run() {
        long pastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;

        double delta = 0;
        double timer = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - pastTime) / ns;
            pastTime = now;

            while (delta > 0) {
                tick();

                if (!gameOver) {
                    List<GameObject> combinedObjects = new ArrayList<>();
                    combinedObjects.add(bird);
                    combinedObjects.addAll(obstacles);
                    renderer.updateScore(score.getScore());
                    renderer.render(combinedObjects);

                } else {
                    renderer.render(List.of(new GameOverImage(45, 0, 400, 600)));
                }
                frames++;

                delta--;
            }
        }
    }

    private void tick() {
        try {
            bird.tick();
            obstacles.forEach(Obstacle::tick);
            removeOffscreenObstacles();
            addNewObstacles();
            collisionDetector.tick(bird, obstacles);

            // Sprawdzamy, czy ptak minął przeszkodę
            if (!obstacles.isEmpty() && bird.getX() > obstacles.get(0).getX() + obstacles.get(0).getWidth()) {
                // Sprawdzamy, czy punkt za daną przeszkodą został już przyznany
                if (!obstaclePassed) {
                    score.incrementScore();
                    obstaclePassed = true;  // Oznaczamy, że punkt został już dodany
                }
            } else {
                obstaclePassed = false;  // Resetujemy flagę, gdy ptak nie znajduje się za przeszkodą
            }
        } catch (GameOver e) {
            gameOver = true;
        }
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getKeyChar() == ' ') {
            bird.jump();
        }
    }

    private void removeOffscreenObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                iterator.remove();
            }
        }
    }

    private void addNewObstacles() {
        if (obstacles.isEmpty() || obstacles.get(obstacles.size() - 1).getX() < WIDTH - 300) {
            int gapHeight = 300;
            int maxDifference = 100;

            int upperObstacleHeight = (int) (Math.random() * (HEIGHT - gapHeight - maxDifference));
            int lowerObstacleHeight = HEIGHT - upperObstacleHeight - gapHeight;

            obstacles.add(new Obstacle(WIDTH, 0, 120, upperObstacleHeight, true));  // Upper obstacle
            obstacles.add(new Obstacle(WIDTH, upperObstacleHeight + gapHeight, 120, lowerObstacleHeight, false));  // Lower obstacle
        }
    }
}