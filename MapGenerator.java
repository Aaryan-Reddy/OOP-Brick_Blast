import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    public Brick map[][]; 
    public final int brickWidth;
    public final int brickHeight;
    private final Random random = new Random(); 
    
    private final int ROWS = 4;
    private final int COLS = 8;

    public MapGenerator(int row, int col) {
        brickWidth = 560 / COLS;
        brickHeight = 160 / ROWS;
        map = new Brick[ROWS][COLS];
        
        List<int[]> allCoords = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                allCoords.add(new int[]{i, j});
            }
        }
        Collections.shuffle(allCoords);
        
        // Guaranteed Placement of special bricks
        placeSpecialBricks(allCoords);

        // Fill remaining spots
        fillRemainingBricks();
    }
    
    private void placeSpecialBricks(List<int[]> shuffledCoords) {
        int index = 0;
        
        // Place 2 Unbreakable Bricks
        for (int k = 0; k < 2; k++) {
            int[] coord = shuffledCoords.get(index++);
            int i = coord[0];
            int j = coord[1];
            int brickX = j * brickWidth + 80;
            int brickY = i * brickHeight + 50;
            
            map[i][j] = new UnbreakableBrick(brickX, brickY, brickWidth, brickHeight);
        }

        // Place 2 PowerUp Bricks
        for (int k = 0; k < 2; k++) {
            int[] coord = shuffledCoords.get(index++);
            int i = coord[0];
            int j = coord[1];
            int brickX = j * brickWidth + 80;
            int brickY = i * brickHeight + 50;
            
            map[i][j] = new PowerUpBrick(brickX, brickY, brickWidth, brickHeight);
        }
    }

    private void fillRemainingBricks() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (map[i][j] == null) { 
                    int brickX = j * brickWidth + 80;
                    int brickY = i * brickHeight + 50;

                    // 40/60 chance for standard bricks
                    if (random.nextDouble() < 0.3) { 
                        map[i][j] = new MultiHitBrick(brickX, brickY, brickWidth, brickHeight); 
                    } else {
                        map[i][j] = new SimpleBrick(brickX, brickY, brickWidth, brickHeight); 
                    }
                }
            }
        }
    }
    
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null) {
                    map[i][j].draw(g); 
                }
            }
        }
    }

    public boolean allBricksBroken() {
        // Win condition: ignores Unbreakable bricks
        for (Brick[] row : map) {
            for (Brick brick : row) {
                if (brick != null && !(brick instanceof UnbreakableBrick) && !brick.isBroken()) {
                    return false;
                }
            }
        }
        return true;
    }
}