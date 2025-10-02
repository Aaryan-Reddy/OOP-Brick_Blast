import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    
    // --- State Variables ---
    private int paddleHitCount = 0; 
    private long startTime;
    private int elapsedTime = 0; 
    private boolean play = false;
    private int score = 0;
    
    private Timer timer;
    private final int delay = 8;
    
    private Paddle paddle; 
    private Ball ball;
    private MapGenerator map;

    public Gameplay() {
        map = new MapGenerator(3, 7); 
        paddle = new Paddle(310);
        ball = new Ball(120, 350, -1, -2); 
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.startTime = System.currentTimeMillis(); 
        timer = new Timer(delay, this);
        timer.start();
    }

    // --- Drawing Method (Modified for Ball Color) ---
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g); 
        
        g.setColor(Color.yellow); 
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(681, 0, 3, 592);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 590, 30);
        g.drawString("Time: " + elapsedTime + "s", 30, 30);

        // Draw Paddle and Ball
        g.setColor(Color.green);
        g.fillRect(paddle.getX(), 550, paddle.getWidth(), 8);
        
        // Conditional Ball Color
        if (ball.isFiredUp()) {
            g.setColor(Color.RED); 
            g.setFont(new Font("serif", Font.BOLD, 18));
            g.drawString("FIRED UP!", 300, 30);
        } else {
            g.setColor(Color.YELLOW); 
        }
        g.fillOval(ball.getX(), ball.getY(), 20, 20);

        if (map.allBricksBroken() || ball.getY() > 570) {
            play = false;
            String message = (map.allBricksBroken() ? "You WON!" : "Game Over!");
            
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(message + " Score: " + score, 200, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Total Time: " + elapsedTime + "s", 250, 330);
            g.drawString("Press ENTER to Restart", 230, 370);
        }

        g.dispose();
    }

    // --- Game Loop (Collision & Movement) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            elapsedTime = (int) ((System.currentTimeMillis() - startTime) / 1000);
            
            // 1. Ball Collision with Paddle (Modified for Super-Ball Deactivation)
            if (ball.getRect().intersects(paddle.getRect())) {
                
                if (ball.isFiredUp()) {
                    ball.setFiredUp(false); // Super-state ends when hitting the paddle
                }
                
                ball.reverseDirY(); // Normal Bounce
                
                // Speed Increase Logic
                paddleHitCount++;
                if (paddleHitCount % 4 == 0) {
                    final int MAX_SPEED = 4; 
                    if (Math.abs(ball.getDirY()) < MAX_SPEED) {
                        int currentMagnitude = Math.abs(ball.getDirY());
                        int newSpeed = (int) Math.signum(ball.getDirY()) * (currentMagnitude + 1);
                        ball.setDirY(newSpeed);
                    }
                    if (Math.abs(ball.getDirX()) < MAX_SPEED) {
                        int currentMagnitude = Math.abs(ball.getDirX());
                        int newSpeed = (int) Math.signum(ball.getDirX()) * (currentMagnitude + 1);
                        ball.setDirX(newSpeed);
                    }
                }
            }

            // 2. Ball Collision with Bricks 
            collisionCheck: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    Brick currentBrick = map.map[i][j];
                    
                    if (currentBrick != null) { 
                        if (ball.getRect().intersects(currentBrick.rect)) {
                            
                            // **SUPER-BALL LOGIC**
                            if (ball.isFiredUp()) {
                                // Super-Ball destroys any brick instantly and passes through (no bounce)
                                
                                // Calculate score for the type of brick being instantly destroyed
                                if (currentBrick instanceof PowerUpBrick) score += 50;
                                else if (currentBrick instanceof MultiHitBrick) score += 10;
                                else if (currentBrick instanceof SimpleBrick) score += 5;
                                
                                // Remove the brick reference
                                map.map[i][j] = null;
                                // The ball continues its trajectory due to lack of a reverseDir call
                                break collisionCheck; 
                            }
                            
                            // **NORMAL BALL LOGIC**
                            score += currentBrick.onHit(); 
                            
                            // Power-Up Activation Check
                            if (currentBrick instanceof PowerUpBrick && currentBrick.isBroken()) {
                                ball.setFiredUp(true); // ACTIVATE SUPER-STATE
                            }
                            
                            // Remove logic
                            if (currentBrick.isBroken()) {
                                map.map[i][j] = null; 
                            }

                            // Normal Bounce logic
                            if (ball.getX() + 19 <= currentBrick.rect.x || ball.getX() + 1 >= currentBrick.rect.x + currentBrick.rect.width) {
                                ball.reverseDirX();
                            } else {
                                ball.reverseDirY();
                            }
                            
                            break collisionCheck; 
                        }
                    }
                }
            }

            // 3. Ball Movement and Wall Collision 
            ball.move();
            
            if (ball.getX() < 0 || ball.getX() > 660) { 
                ball.reverseDirX();
            }
            // Check top wall bounce only if not Fired Up (Fired up ball could pass through the top if we wanted, but let's keep it safe)
            if (ball.getY() < 0) { 
                ball.reverseDirY();
            }
        }
        repaint();
    }

    // --- Restart Game (Final Logic) ---
    private void restartGame() {
        play = true;
        score = 0;
        paddle = new Paddle(310);
        
        ball.reset(120, 350, -1, -2); 
        paddleHitCount = 0;
        
        this.startTime = System.currentTimeMillis();
        elapsedTime = 0;
        
        map = new MapGenerator(3, 7); 
        repaint();
    }
    
    // Unused KeyListener methods
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            play = true;
            paddle.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            play = true;
            paddle.moveLeft();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                restartGame();
            }
        }
    }
}