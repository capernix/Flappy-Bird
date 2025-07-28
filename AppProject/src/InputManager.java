import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputManager extends MouseAdapter implements KeyListener {
    private boolean spacePressed = false;
    private boolean escapePressed = false;
    private boolean enterPressed = false;
    private boolean spaceJustPressed = false;
    private boolean escapeJustPressed = false;
    
    // Mouse support for mobile-like experience
    @Override
    public void mouseClicked(MouseEvent e) {
        spaceJustPressed = true;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                System.out.println("Space key detected!");
                if (!spacePressed) {
                    spaceJustPressed = true;
                }
                spacePressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                if (!escapePressed) {
                    escapeJustPressed = true;
                }
                escapePressed = true;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    // Getters for continuous input (for holding keys)
    public boolean isSpacePressed() { return spacePressed; }
    public boolean isEscapePressed() { return escapePressed; }
    public boolean isEnterPressed() { return enterPressed; }
    
    // Getters for single press detection
    public boolean isSpaceJustPressed() { return spaceJustPressed; }
    public boolean isEscapeJustPressed() { return escapeJustPressed; }
    
    // Reset just-pressed flags (call this after handling input)
    public void resetJustPressed() {
        spaceJustPressed = false;
        escapeJustPressed = false;
    }
    
    // Reset all input state
    public void reset() {
        spacePressed = false;
        escapePressed = false;
        enterPressed = false;
        spaceJustPressed = false;
        escapeJustPressed = false;
    }
} 