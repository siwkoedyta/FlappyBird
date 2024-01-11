import gameObjects.GameObject;
import gameObjects.Obstacle;

import java.util.List;

public class CollisionDetector {
    private final int width;
    private final int height;

    public CollisionDetector(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void tick(GameObject bird, List<Obstacle> obstacles) {
        if(bird.getX() < 0 || (bird.getX() + bird.getWidth()) > width || bird.getY() < 0 || (bird.getY() + bird.getHeight() > height)) {
            throw new GameOver();
        }

        for (GameObject obstacle : obstacles) {
            if (bird.getX() < obstacle.getX() + obstacle.getWidth() &&
                    bird.getX() + bird.getWidth() > obstacle.getX() &&
                    bird.getY() < obstacle.getY() + obstacle.getHeight() &&
                    bird.getY() + bird.getHeight() > obstacle.getY()) {
                throw new GameOver();
            }
        }
    }

}
