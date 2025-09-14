package modules;

import java.awt.Point;
import java.util.List;
import java.util.Random;

public class Food {
    private Point position;
    private Random random;
    private int gridWidth;
    private int gridHeight;
    
    public Food(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.random = new Random();
        this.position = new Point(0, 0);
    }
    
    public void generateFood(List<Point> snakeBody) {
        Point newPosition;
        boolean validPosition;
        
        do {
            validPosition = true;
            newPosition = new Point(random.nextInt(gridWidth), random.nextInt(gridHeight));
            
            for (Point segment : snakeBody) {
                if (segment.equals(newPosition)) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
        
        position = newPosition;
    }
    
    public Point getPosition() { return new Point(position); }
}