package control;

import modules.*;
import componentes.SnakePanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;

public class LogicGame implements ActionListener, KeyListener {
    private Snake snake;
    private Food food;
    private Timer gameTimer;
    private Timer timerTiempo;
    private int score;
    private boolean gameRunning;
    private boolean paused;
    
    private SnakePanel snakePanel;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel levelLabel;
    
    private int gridWidth = 20;
    private int gridHeight = 15;
    private int currentLevel;
    private Player currentPlayer;
    private ScoreControl scoreControl;
    
    private long tiempoInicio;
    private Duration tiempoTranscurrido;
    
    public LogicGame(LevelControl levelControl, ScoreControl scoreControl, 
                    UserControl userControl, Player player, int level) {
        this.currentPlayer = player;
        this.currentLevel = level;
        this.scoreControl = scoreControl;
        initGame();
    }
    
    private void initGame() {
        snake = new Snake();
        food = new Food(gridWidth, gridHeight);
        food.generateFood(snake.getBody());
        
        score = 0;
        gameRunning = true;
        paused = false;
        
        // Configurar timer según el nivel
        int speed = getSpeedForLevel(currentLevel);
        gameTimer = new Timer(speed, this);
        
        // Inicializar tiempo
        tiempoInicio = System.currentTimeMillis();
        tiempoTranscurrido = Duration.ZERO;
        
        // Timer para actualizar el tiempo cada segundo
        timerTiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();
            }
        });
        
        gameTimer.start();
        timerTiempo.start();
    }
    
    private int getSpeedForLevel(int level) {
        switch (level) {
            case 1: return 150;
            case 2: return 120;
            case 3: return 90;
            case 4: return 70;
            case 5: return 50;
            default: return 150;
        }
    }
    
    private void actualizarTiempo() {
        if (!paused && gameRunning) {
            long tiempoActual = System.currentTimeMillis();
            long segundosJugados = (tiempoActual - tiempoInicio) / 1000;
            tiempoTranscurrido = Duration.ofSeconds(segundosJugados);
            
            // Actualizar label de tiempo
            if (timeLabel != null) {
                long segundos = tiempoTranscurrido.getSeconds();
                long minutos = segundos / 60;
                long segundosRestantes = segundos % 60;
                timeLabel.setText(String.format("Tiempo: %02d:%02d", minutos, segundosRestantes));
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!paused && gameRunning) {
            updateGame();
        }
    }
    
    private void updateGame() {
        // Mover la serpiente
        snake.move();
        
        // Verificar colisiones con bordes
        if (snake.checkWallCollision(gridWidth, gridHeight)) {
            gameOver();
            return;
        }
        
        // Verificar colisión consigo misma
        if (snake.checkSelfCollision()) {
            gameOver();
            return;
        }
        
        // Verificar si comió comida
        if (snake.getHead().equals(food.getPosition())) {
            snake.grow();
            score += 10 * currentLevel;
            food.generateFood(snake.getBody());
            
            // Actualizar puntuación
            if (scoreLabel != null) {
                scoreLabel.setText("Puntuación: " + score);
            }
        }
        
        // Actualizar el panel
        if (snakePanel != null) {
            snakePanel.updatePanel();
        }
    }
    
    private void gameOver() {
        gameRunning = false;
        gameTimer.stop();
        timerTiempo.stop();
        
        // Guardar score con el tiempo
        scoreControl.updateScore(currentPlayer, score, currentLevel, tiempoTranscurrido);
        
        JOptionPane.showMessageDialog(null, 
            "Game Over!\nPuntuación: " + score + 
            "\nTiempo: " + formatTiempo(tiempoTranscurrido));
    }
    
    private String formatTiempo(Duration duration) {
        long segundos = duration.getSeconds();
        long minutos = segundos / 60;
        long segundosRestantes = segundos % 60;
        return String.format("%02d:%02d", minutos, segundosRestantes);
    }
    
    // Control de teclado
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameRunning) return;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.setDirection(Snake.Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection(Snake.Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection(Snake.Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection(Snake.Direction.RIGHT);
                break;
            case KeyEvent.VK_P:
                togglePause();
                break;
            case KeyEvent.VK_R:
                restartGame();
                break;
        }
    }
    
    public void togglePause() {
        paused = !paused;
        if (paused) {
            gameTimer.stop();
            timerTiempo.stop();
        } else {
            gameTimer.start();
            timerTiempo.start();
            // Reajustar tiempo de inicio
            tiempoInicio = System.currentTimeMillis() - tiempoTranscurrido.toMillis();
        }
    }
    
    public void restartGame() {
        gameTimer.stop();
        timerTiempo.stop();
        initGame();
    }
    
    public void stopGame() {
        if (gameTimer != null) gameTimer.stop();
        if (timerTiempo != null) timerTiempo.stop();
        gameRunning = false;
    }
    
    public void startGame() {
        if (gameTimer != null) gameTimer.start();
        if (timerTiempo != null) timerTiempo.start();
    }
    
    // Getters
    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public int getGridWidth() { return gridWidth; }
    public int getGridHeight() { return gridHeight; }
    public boolean isGameRunning() { return gameRunning; }
    
    // Setters
    public void setSnakePanel(SnakePanel snakePanel) { 
        this.snakePanel = snakePanel; 
        snakePanel.addKeyListener(this);
        snakePanel.setFocusable(true);
        snakePanel.requestFocusInWindow();
    }
    
    public void setScoreLabel(JLabel scoreLabel) { 
        this.scoreLabel = scoreLabel; 
        if (scoreLabel != null) {
            scoreLabel.setText("Puntuación: " + score);
        }
    }
    
    public void setTimeLabel(JLabel timeLabel) { 
        this.timeLabel = timeLabel; 
        if (timeLabel != null) {
            timeLabel.setText("Tiempo: 00:00");
        }
    }
    
    public void setLevelLabel(JLabel levelLabel) { 
        this.levelLabel = levelLabel; 
        if (levelLabel != null) {
            levelLabel.setText("Nivel: " + currentLevel);
        }
    }
    
    // Métodos no usados de KeyListener
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}