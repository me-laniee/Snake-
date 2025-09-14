package componentes;

import control.LogicGame;
import modules.Snake;
import modules.Food;
import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {
    private LogicGame logicGame;
    private final int CELL_SIZE = 40; // Tamaño de cada celda
    
    public SnakePanel(LogicGame logicGame) {
        this.logicGame = logicGame;
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (logicGame != null) {
            drawGrid(g2);
            drawSnake(g2);
            drawFood(g2);
        }
    }
    
    private void drawGrid(Graphics2D g2) {
        g2.setColor(new Color(30, 30, 30));
        for (int x = 0; x <= logicGame.getGridWidth(); x++) {
            g2.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, logicGame.getGridHeight() * CELL_SIZE);
        }
        for (int y = 0; y <= logicGame.getGridHeight(); y++) {
            g2.drawLine(0, y * CELL_SIZE, logicGame.getGridWidth() * CELL_SIZE, y * CELL_SIZE);
        }
    }
    
    private void drawSnake(Graphics2D g2) {
        Snake snake = logicGame.getSnake();
        
        // Dibujar cuerpo
        g2.setColor(Color.GREEN);
        for (int i = 1; i < snake.getBody().size(); i++) {
            Point segment = snake.getBody().get(i);
            g2.fillRect(segment.x * CELL_SIZE, segment.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        
        // Dibujar cabeza
        Point head = snake.getHead();
        g2.setColor(new Color(0, 200, 0)); // Verde más oscuro para la cabeza
        g2.fillRect(head.x * CELL_SIZE, head.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        
    }
    
    private void drawFood(Graphics2D g2) {
        Food food = logicGame.getFood();
        Point foodPos = food.getPosition();
        
        g2.setColor(Color.RED);
        g2.fillOval(foodPos.x * CELL_SIZE, foodPos.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
    
    public void updatePanel() {
        repaint();
    }
}