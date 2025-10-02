import java.awt.Rectangle;

public class Paddle {
    private int x;
    private final int y = 550;
    private final int width = 100;
    private final int height = 8;
    private final int moveSpeed = 20;

    public Paddle(int startX) {
        this.x = startX;
    }

    public void moveRight() {
        x = Math.min(x + moveSpeed, 600); 
    }

    public void moveLeft() {
        x = Math.max(x - moveSpeed, 10);  
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getWidth() { return width; }
}