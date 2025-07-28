public class GameConfig {
    // Window settings
    public static final int BOARD_WIDTH = 360;
    public static final int BOARD_HEIGHT = 640;
    public static final String GAME_TITLE = "Flappy Bird";
    
    // Bird settings
    public static final int BIRD_X = BOARD_WIDTH / 8;
    public static final int BIRD_Y = BOARD_HEIGHT / 2;
    public static final int BIRD_WIDTH = 34;
    public static final int BIRD_HEIGHT = 24;
    public static final int BIRD_FLAP_VELOCITY = -9;
    
    // Physics settings
    public static final int GRAVITY = 1;
    public static final int PIPE_VELOCITY = -4;
    
    // Pipe settings
    public static final int PIPE_WIDTH = 64;
    public static final int PIPE_HEIGHT = 512;
    public static final int PIPE_SPAWN_INTERVAL = 1500; // milliseconds
    public static final int PIPE_OPENING_SPACE = BOARD_HEIGHT / 4;
    
    // Game loop settings
    public static final int TARGET_FPS = 60;
    public static final int GAME_LOOP_DELAY = 1000 / TARGET_FPS;
    
    // Scoring
    public static final double SCORE_PER_PIPE = 0.5;
    
    // Difficulty scaling (can be implemented later)
    public static final double VELOCITY_INCREASE_RATE = 0.1;
    public static final int VELOCITY_INCREASE_INTERVAL = 10; // pipes
} 