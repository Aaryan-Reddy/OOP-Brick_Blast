import java.awt.Rectangle;

public class Ball {
    private int posX, posY;
    private int dirX, dirY;
    private final int diameter = 20;
    private boolean isFiredUp = false; // NEW: Super-Ball state

    public Ball(int startX, int startY, int startDirX, int startDirY) {
        this.posX = startX;
        this.posY = startY;
        this.dirX = startDirX;
        this.dirY = startDirY;
    }

    public void move() {
        posX += dirX;
        posY += dirY;
    }
    
    // Getters and Setters (Encapsulation)
    public Rectangle getRect() { return new Rectangle(posX, posY, diameter, diameter); }
    public int getX() { return posX; }
    public int getY() { return posY; }
    public int getDirX() { return dirX; }
    public int getDirY() { return dirY; }
    public void setDirX(int dirX) { this.dirX = dirX; }
    public void setDirY(int dirY) { this.dirY = dirY; }
    public void reverseDirX() { dirX = -dirX; }
    public void reverseDirY() { dirY = -dirY; }

    public void reset(int startX, int startY, int startDirX, int startDirY) {
        this.posX = startX;
        this.posY = startY;
        this.dirX = startDirX;
        this.dirY = startDirY;
        this.isFiredUp = false; 
    }
    
    // NEW Power-Up Methods
    public boolean isFiredUp() { return isFiredUp; }
    public void setFiredUp(boolean state) { isFiredUp = state; }
}