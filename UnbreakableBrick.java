// --- UnbreakableBrick.java ---
import java.awt.Color;

public class UnbreakableBrick extends Brick {

    public UnbreakableBrick(int x, int y, int width, int height) {
        super(x, y, width, height, 1); 
    }

    // Polymorphism: onHit() does nothing, guaranteeing it never breaks.
    @Override
    public int onHit() {
        return 0; 
    }

    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }
}
