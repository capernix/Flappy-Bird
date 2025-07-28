import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class FlappyBird extends JPanel implements ActionListener {
    // Game components
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private GameStateManager gameStateManager;
    private InputManager inputManager;
    
    // Images
    private Image backgroundImg;
    private Image topPipeImg;
    private Image birdImg;
    private Image bottomPipeImg;
    
    // Timers
    private Timer gameLoop;
    private Timer placePipesTimer;
    
    FlappyBird() {
        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setFocusable(true);
        
        // Initialize game components
        gameStateManager = new GameStateManager();
        inputManager = new InputManager();
        
        // Add input listeners
        addKeyListener(inputManager);
        addMouseListener(inputManager);
        
        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        // Initialize bird
        bird = new Bird(birdImg, GameConfig.BIRD_X, GameConfig.BIRD_Y, 
                       GameConfig.BIRD_WIDTH, GameConfig.BIRD_HEIGHT);
        pipes = new ArrayList<Pipe>();

        // Pipe placing timer
        placePipesTimer = new Timer(GameConfig.PIPE_SPAWN_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        
        // Game loop timer
        gameLoop = new Timer(GameConfig.GAME_LOOP_DELAY, this);
                
        // Start the game loop immediately (needed for input handling)
        gameLoop.start();
    }

    public void placePipes() {
        // Calculate random pipe position
        int randomPipeY = (int)(0 - GameConfig.PIPE_HEIGHT/4 - Math.random()*(GameConfig.PIPE_HEIGHT/2));
        
        // Create top pipe
        Pipe topPipe = new Pipe(topPipeImg, GameConfig.BOARD_WIDTH, randomPipeY, 
                               GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT, true);
        pipes.add(topPipe);

        // Create bottom pipe
        Pipe bottomPipe = new Pipe(bottomPipeImg, GameConfig.BOARD_WIDTH, 
                                  randomPipeY + GameConfig.PIPE_HEIGHT + GameConfig.PIPE_OPENING_SPACE, 
                                  GameConfig.PIPE_WIDTH, GameConfig.PIPE_HEIGHT, false);
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Draw background
        g2d.drawImage(backgroundImg, 0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, null);
        
        // Draw bird with rotation
        bird.draw(g2d);
    
        // Draw pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.draw(g2d);
        }
        
        // Draw UI based on game state
        switch (gameStateManager.getCurrentState()) {
            case MENU:
                UIManager.drawMenu(g2d);
                break;
            case PLAYING:
                UIManager.drawScore(g2d, gameStateManager.getScore(), gameStateManager.getHighScore());
                break;
            case GAME_OVER:
                UIManager.drawGameOver(g2d, gameStateManager.getScore(), gameStateManager.getHighScore());
                break;
            case PAUSED:
                UIManager.drawPauseMenu(g2d);
                break;
        }
    }

    public void move() {
        if (gameStateManager.getCurrentState() != GameState.PLAYING) {
            return;
        }
        
        // Bird physics
        bird.applyGravity();
        bird.update();
        
        // Keep bird within screen bounds
        if (bird.getY() < 0) {
            bird.setY(0);
        }
        
        // Update pipes
        for(int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);
            pipe.update();
            
            // Check if bird passed pipe
            if (pipe.hasPassedBird(bird.getX())) {
                gameStateManager.addScore(GameConfig.SCORE_PER_PIPE);
            }
            
            // Check collision
            if (collision(bird, pipe)) {
                gameStateManager.gameOver();
                return;
            }
            
            // Remove off-screen pipes
            if (pipe.isOffScreen()) {
                pipes.remove(i);
            }
        }
        
        // Check if bird hit the ground
        if (bird.getY() > GameConfig.BOARD_HEIGHT) {
            gameStateManager.gameOver();
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

    // Game loop - runs 60 times per second
    @Override
    public void actionPerformed(ActionEvent e) {
        handleInput();
        move();
        repaint();
        
        // Stop only the pipe timer if game is over, keep game loop running for input
        if (gameStateManager.isGameOver()) {
            placePipesTimer.stop();
        }
    }
    
    private void handleInput() {
        // Handle space key for flapping and game control
        if (inputManager.isSpaceJustPressed()) {
            System.out.println("Space pressed! Current state: " + gameStateManager.getCurrentState());
            switch (gameStateManager.getCurrentState()) {
                case MENU:
                    System.out.println("Starting game...");
                    startGame();
                    break;
                case PLAYING:
                    bird.flap();
                    break;
                case GAME_OVER:
                    restartGame();
                    break;
                case PAUSED:
                    resumeGame();
                    break;
            }
        }
        
        // Handle escape key for pause
        if (inputManager.isEscapeJustPressed()) {
            if (gameStateManager.getCurrentState() == GameState.PLAYING) {
                pauseGame();
            } else if (gameStateManager.getCurrentState() == GameState.PAUSED) {
                resumeGame();
            }
        }
        
        // Reset just-pressed flags after handling input
        inputManager.resetJustPressed();
    }
    
    private void startGame() {
        gameStateManager.setCurrentState(GameState.PLAYING);
        gameLoop.start();
        placePipesTimer.start();
    }
    
    private void pauseGame() {
        gameStateManager.setCurrentState(GameState.PAUSED);
        placePipesTimer.stop();
    }
    
    private void resumeGame() {
        gameStateManager.setCurrentState(GameState.PLAYING);
        gameLoop.start();
        placePipesTimer.start();
    }
    
    private void restartGame() {
        // Reset bird
        bird.reset(GameConfig.BIRD_X, GameConfig.BIRD_Y);
        
        // Clear pipes
        pipes.clear();
        
        // Reset game state
        gameStateManager.resetGame();
        
        // Start timers
        gameLoop.start();
        placePipesTimer.start();
    }

}



