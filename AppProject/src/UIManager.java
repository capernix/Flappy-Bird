import java.awt.*;

public class UIManager {
    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 32);
    private static final Font MENU_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 16);
    
    public static void drawScore(Graphics2D g, double score, double highScore) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw score
        g.setFont(SCORE_FONT);
        g.setColor(Color.WHITE);
        String scoreText = String.valueOf((int) score);
        FontMetrics fm = g.getFontMetrics();
        int scoreX = GameConfig.BOARD_WIDTH - fm.stringWidth(scoreText) - 20;
        g.drawString(scoreText, scoreX, 40);
        
        // Draw high score
        g.setFont(SMALL_FONT);
        String highScoreText = "Best: " + (int) highScore;
        fm = g.getFontMetrics();
        int highScoreX = GameConfig.BOARD_WIDTH - fm.stringWidth(highScoreText) - 20;
        g.drawString(highScoreText, highScoreX, 60);
    }
    
    public static void drawGameOver(Graphics2D g, double score, double highScore) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Semi-transparent overlay
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);
        
        // Game over text
        g.setFont(MENU_FONT);
        g.setColor(Color.WHITE);
        String gameOverText = "Game Over";
        FontMetrics fm = g.getFontMetrics();
        int textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(gameOverText)) / 2;
        g.drawString(gameOverText, textX, GameConfig.BOARD_HEIGHT / 2 - 50);
        
        // Score
        String scoreText = "Score: " + (int) score;
        fm = g.getFontMetrics();
        textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(scoreText)) / 2;
        g.drawString(scoreText, textX, GameConfig.BOARD_HEIGHT / 2 - 20);
        
        // High score
        if (score >= highScore) {
            g.setColor(Color.YELLOW);
            String newRecordText = "New Record!";
            fm = g.getFontMetrics();
            textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(newRecordText)) / 2;
            g.drawString(newRecordText, textX, GameConfig.BOARD_HEIGHT / 2 + 10);
        }
        
        // Instructions
        g.setColor(Color.WHITE);
        g.setFont(SMALL_FONT);
        String restartText = "Press SPACE to restart";
        fm = g.getFontMetrics();
        textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(restartText)) / 2;
        g.drawString(restartText, textX, GameConfig.BOARD_HEIGHT / 2 + 50);
    }
    
    public static void drawMenu(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Title
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.WHITE);
        String titleText = "Flappy Bird";
        FontMetrics fm = g.getFontMetrics();
        int textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(titleText)) / 2;
        g.drawString(titleText, textX, GameConfig.BOARD_HEIGHT / 2 - 50);
        
        // Instructions
        g.setFont(SMALL_FONT);
        String instructionText = "Press SPACE to start";
        fm = g.getFontMetrics();
        textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(instructionText)) / 2;
        g.drawString(instructionText, textX, GameConfig.BOARD_HEIGHT / 2 + 20);
    }
    
    public static void drawPauseMenu(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Semi-transparent overlay
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);
        
        // Pause text
        g.setFont(MENU_FONT);
        g.setColor(Color.WHITE);
        String pauseText = "PAUSED";
        FontMetrics fm = g.getFontMetrics();
        int textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(pauseText)) / 2;
        g.drawString(pauseText, textX, GameConfig.BOARD_HEIGHT / 2);
        
        // Instructions
        g.setFont(SMALL_FONT);
        String instructionText = "Press SPACE to resume";
        fm = g.getFontMetrics();
        textX = (GameConfig.BOARD_WIDTH - fm.stringWidth(instructionText)) / 2;
        g.drawString(instructionText, textX, GameConfig.BOARD_HEIGHT / 2 + 40);
    }
} 