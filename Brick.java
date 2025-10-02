import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Brick {
    protected int health;
    public Rectangle rect;

    public Brick(int x, int y, int width, int height, int initialHealth) {
        this.rect = new Rectangle(x, y, width, height);
        this.health = initialHealth;
    }

    public abstract int onHit(); 
    public abstract Color getColor();

    public boolean isBroken() {
        return health <= 0;
    }
    
    public void draw(Graphics2D g) {
        if (!isBroken()) {
            g.setColor(getColor()); 
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.black);
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
}