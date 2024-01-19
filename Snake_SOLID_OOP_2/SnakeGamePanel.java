import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGamePanel extends JPanel implements ActionListener, KeyListener {

    private final int boardWidth;
    private final int boardHeight;
    private final int tileSize = 25;

    private final GameEntities gameEntities;
    private final Timer gameLoop;
    private boolean drawGrid;

    public SnakeGamePanel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);

        setFocusable(true);

        gameEntities = new GameEntities(new Snake(5, 5, tileSize, boardWidth, boardHeight),
                new Food(tileSize, boardWidth, boardHeight));
        gameLoop = new Timer(100, this);
        gameLoop.start();
        drawGrid = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameEntities.draw(g);

        if (drawGrid) {
            drawGrid(g);
        }
        if (gameEntities.isGameOver()) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press any key to replay", boardWidth / 2, boardHeight / 2);
        }
    }

    public void move() {
        gameEntities.move();
        if (gameEntities.isGameOver() || gameEntities.isSnakeOutOfBounds()) {
            gameLoop.stop();
        }


    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.equals(tile2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameEntities.isGameOver()) {
            gameEntities.reset();
            gameLoop.restart();
        } else {
            // Otherwise, update the direction of the snake
            gameEntities.updateDirection(e.getKeyCode());
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void drawGrid(Graphics g) {

    }

}
