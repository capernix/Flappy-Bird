public class GameStateManager {
    private GameState currentState = GameState.MENU;
    private double score = 0;
    private double highScore = 0;
    private boolean gameOver = false;
    
    public void resetGame() {
        score = 0;
        gameOver = false;
        currentState = GameState.PLAYING;
    }
    
    public void gameOver() {
        gameOver = true;
        currentState = GameState.GAME_OVER;
        if (score > highScore) {
            highScore = score;
        }
    }
    
    // Getters and setters
    public GameState getCurrentState() { return currentState; }
    public void setCurrentState(GameState state) { currentState = state; }
    public double getScore() { return score; }
    public void addScore(double points) { score += points; }
    public double getHighScore() { return highScore; }
    public boolean isGameOver() { return gameOver; }
} 