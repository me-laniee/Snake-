package modules;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    public enum Direction { UP, DOWN, LEFT, RIGHT }
    
    private List<Point> body;
    private Direction direction;
    private Direction nextDirection;
    
    public Snake() {
        initSnake();
    }
    
    private void initSnake() {
        body = new ArrayList<>();
        body.add(new Point(5, 5));
        body.add(new Point(4, 5));
        body.add(new Point(3, 5));
        direction = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
    }
    
    public void move() {
        direction = nextDirection;
        Point head = new Point(body.get(0));
        
        switch (direction) {
            case UP: head.y--; break;
            case DOWN: head.y++; break;
            case LEFT: head.x--; break;
            case RIGHT: head.x++; break;
        }
        
        body.add(0, head);
        body.remove(body.size() - 1);
    }
    
    public void setDirection(Direction newDirection) {
        if ((direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
            (direction == Direction.RIGHT && newDirection != Direction.LEFT) ||
            (direction == Direction.UP && newDirection != Direction.DOWN) ||
            (direction == Direction.DOWN && newDirection != Direction.UP)) {
            nextDirection = newDirection;
        }
    }
    
    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail));
    }
    
    public boolean checkSelfCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkWallCollision(int gridWidth, int gridHeight) {
        Point head = body.get(0);
        return head.x < 0 || head.x >= gridWidth || head.y < 0 || head.y >= gridHeight;
    }
    
    // Getters
    public List<Point> getBody() { return new ArrayList<>(body); }
    public Point getHead() { return new Point(body.get(0)); }
    public Direction getDirection() { return direction; }
}