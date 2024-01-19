import javax.swing.*;

public class SnakeGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            SnakeGamePanel snakeGamePanel = new SnakeGamePanel(1000, 600);

            frame.add(snakeGamePanel);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);

            snakeGamePanel.requestFocusInWindow(); // Ensure the game receives keyboard input focus
        });
    }
}






