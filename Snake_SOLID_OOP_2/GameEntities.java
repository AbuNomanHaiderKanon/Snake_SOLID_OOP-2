import java.awt.*;
public class GameEntities {
    private final Snake snake;
    private final Food food;

    public GameEntities(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    public void draw(Graphics g) {
        snake.draw(g);
        food.draw(g);
        drawGrid(g);
        drawScore(g);
    }

    public void move() {
        if (snake.eat(food)) {
            food.placeFood(snake);
        }
        snake.move();
    }

    public boolean isGameOver() {
        return snake.isGameOver();
    }

    public boolean isSnakeOutOfBounds() {
        return snake.isOutOfBounds();
    }

    public void updateDirection(int keyCode) {
        snake.updateDirection(keyCode);
    }

    private void drawGrid(Graphics g) {

    }

    private void drawScore(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        if (snake.isGameOver()) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + snake.getBodySize(), snake.getTileSize() - 20, snake.getTileSize());
        } else {
            g.drawString("Score: " + snake.getBodySize(), snake.getTileSize() - 20, snake.getTileSize());
        }
    }
    public void reset() {
        // Reset the game entities to their initial state
        snake.reset();
        food.reset(snake);
    }
}
