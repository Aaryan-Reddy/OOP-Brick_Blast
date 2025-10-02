import java.awt.Color;

public class MultiHitBrick extends Brick {
    
    public MultiHitBrick(int x, int y, int width, int height) {
        // Starts with 3 health
        super(x, y, width, height, 3); 
    }

    @Override
    public int onHit() {
        if (this.health > 0) {
            this.health--;
        }
        // Returns 10 points only when completely broken
        return (health == 0) ? 10 : 0; 
    }

    @Override
    public Color getColor() {
        if (health == 3) {
            return Color.RED;      
        } else if (health == 2) {
            return Color.ORANGE;   
        } else { // health == 1
            return Color.WHITE;   
        }
    }
}