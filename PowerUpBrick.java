// --- PowerUpBrick.java ---
import java.awt.Color;

public class PowerUpBrick extends Brick {
    
    public PowerUpBrick(int x, int y, int width, int height) {
        // Requires 2 hits
        super(x, y, width, height, 2); 
    }

    @Override
    public int onHit() {
        if (this.health > 0) {
            this.health--;
        }
        // Returns high score (50) only when broken.
        return (health == 0) ? 50 : 0; 
    }

    @Override
    public Color getColor() {
        if (health == 2) {
            return Color.MAGENTA; // Full health (PowerUp waiting)
        } else {
            return Color.PINK;    // 1 hit remaining
        }
    }
}