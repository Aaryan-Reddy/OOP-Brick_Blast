import java.awt.Color;

public class SimpleBrick extends Brick {
    
    public SimpleBrick(int x, int y, int width, int height) {
        super(x, y, width, height, 1); 
    }

    @Override
    public int onHit() {
        this.health--;
        return 5; 
    }

    @Override
    public Color getColor() {
        return Color.white;
    }
}