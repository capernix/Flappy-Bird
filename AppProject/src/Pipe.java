import java.awt.*;

public class Pipe {
    private int x, y, width, height;
    private Image img;
    private boolean passed = false;
    private boolean isTopPipe;
    
    public Pipe(Image img, int x, int y, int width, int height, boolean isTopPipe) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isTopPipe = isTopPipe;
    }
    
    public void update() {
        x += GameConfig.PIPE_VELOCITY;
    }
    
    public void draw(Graphics2D g) {
        g.drawImage(img, x, y, width, height, null);
    }
    
    public boolean isOffScreen() {
        return x + width < 0;
    }
    
    public boolean hasPassedBird(int birdX) {
        if (!passed && birdX > x + width) {
            passed = true;
            return true;
        }
        return false;
    }
    
    // Getters and setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isPassed() { return passed; }
    public boolean isTopPipe() { return isTopPipe; }
    
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
} 