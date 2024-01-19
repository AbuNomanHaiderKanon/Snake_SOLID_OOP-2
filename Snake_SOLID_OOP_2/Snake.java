import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Snake {
    private final ArrayList<Tile> body;
    private int velocityX;
    private int velocityY;
    private boolean gameOver;
    private final int tileSize;
    private final int boardWidth;
    private final int boardHeight;

    public Snake(int startX, int startY, int tileSize, int boardWidth, int boardHeight) {
        this.tileSize = tileSize;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        body = new ArrayList<>();
        body.add(new Tile(startX, startY));
        velocityX = 1;
        velocityY = 0;
        gameOver = false;
    }
    public ArrayList<Tile> getBody() {
        return body;
    }

    public boolean eat(Food food) {
        if (collision(body.get(0), food.getTile())) {
            body.add(new Tile(food.getTile().x, food.getTile().y));
            return true;
        }
        return false;
    }

    public void move() {
        Tile head = body.get(0);
        Tile newHead = new Tile(head.x + velocityX, head.y + velocityY);

        // Check for wall collision
        if (newHead.x < 0 || newHead.x >= boardWidth / tileSize || newHead.y < 0 || newHead.y >= boardHeight / tileSize) {
            gameOver = true;
            return; // Stop moving if wall collision
        }


        body.add(0, newHead);
        body.remove(body.size() - 1);
        checkCollision();
    }

    private void checkCollision() {
        Tile head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (collision(head, body.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    public void updateDirection(int keyCode) {
        if (keyCode == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (keyCode == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (keyCode == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (keyCode == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        for (Tile part : body) {
            g.fill3DRect(part.x * tileSize, part.y * tileSize, tileSize, tileSize, true);
        }
    }

    public int getBodySize() {
        return body.size();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isOutOfBounds() {
        Tile head = body.get(0);
        return head.x * tileSize < 0 || head.x * tileSize >= boardWidth ||
                head.y * tileSize < 0 || head.y * tileSize >= boardHeight;
    }

    public int getTileSize() {
        return tileSize;
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.equals(tile2);
    }
    public void reset() {
        // Reset the snake to its initial state
        body.clear();
        body.add(new Tile(5, 5));
        velocityX = 1;
        velocityY = 0;
        gameOver = false;
    }
}
