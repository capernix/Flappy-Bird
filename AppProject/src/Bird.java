import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bird {
    private int x, y, width, height;
    private Image img;
    private double velocityY = 0;
    private double rotation = 0;
    private double rotationVelocity = 0;
    
    public Bird(Image img, int x, int y, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void update() {
        // Update rotation based on velocity
        rotationVelocity = Math.min(velocityY * 0.5, 15); // Cap rotation speed
        rotation += rotationVelocity;
        
        // Clamp rotation between -30 and 90 degrees
        rotation = Math.max(-30, Math.min(90, rotation));
    }
    
    public void flap() {
        velocityY = GameConfig.BIRD_FLAP_VELOCITY;
        rotation = -30; // Snap to upward rotation
    }
    
    public void applyGravity() {
        velocityY += GameConfig.GRAVITY;
        y += velocityY;
    }
    
    public void draw(Graphics2D g) {
        // Save the original transform
        AffineTransform originalTransform = g.getTransform();
        
        // Calculate center point for rotation
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        
        // Apply rotation transform
        g.rotate(Math.toRadians(rotation), centerX, centerY);
        
        // Draw the bird
        g.drawImage(img, x, y, width, height, null);
        
        // Restore the original transform
        g.setTransform(originalTransform);
    }
    
    // Getters and setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getVelocityY() { return velocityY; }
    
    public void setY(int y) { this.y = y; }
    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocityY = 0;
        this.rotation = 0;
        this.rotationVelocity = 0;
    }
} 