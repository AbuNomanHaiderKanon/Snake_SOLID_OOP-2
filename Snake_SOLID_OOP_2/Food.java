import java.awt.*;
import java.util.Random;
public class Food {
    private Tile tile;
    private final int tileSize; // Add tileSize variable

    public Food(int tileSize, int boardWidth, int boardHeight) {
        this.tileSize = tileSize; // Initialize tileSize
        this.tile = new Tile(0, 0);
        placeFood(new Snake(0, 0, tileSize, boardWidth, boardHeight));
    }

    public Tile getTile() {
        return tile;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fill3DRect(tile.x * tileSize, tile.y * tileSize, tileSize, tileSize, true);
    }

    public void placeFood(Snake snake) {
        Random random = new Random();
        do {
            tile.x = random.nextInt(snake.getTileSize());
            tile.y = random.nextInt(snake.getTileSize());
        } while (snake.getBody().contains(tile));
    }
    public void reset(Snake snake) {
        // Reset the food to a new position
        placeFood(snake);
    }
}
