package vistas;

import javax.swing.*;
import java.awt.*;
import componentes.SnakePanel;
import componentes.TimeLabel; // ← Cambiado de TimerLabel a TimeLabel
import componentes.CustomButton;
import control.LogicGame;
import control.LevelControl;
import control.ScoreControl;
import control.UserControl;
import modules.Player;

public class GameView extends JFrame {
    private SnakePanel snakePanel;
    private TimeLabel timeLabel; // ← Cambiado de TimerLabel a TimeLabel
    private JLabel scoreLabel;
    private JLabel levelLabel;
    private LogicGame logicGame;
    private Player currentPlayer;
    private int currentLevel;
    
    public GameView(Player player, int level) {
        this.currentPlayer = player;
        this.currentLevel = level;
        initialize();
    }
    
    private void initialize() {
        setTitle("Snake Game - Nivel " + currentLevel + " - Jugador: " + currentPlayer.getUsername());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // controladores
        LevelControl levelControl = new LevelControl();
        ScoreControl scoreControl = new ScoreControl();
        UserControl userControl = new UserControl();
        
        // Inicializar juego
        logicGame = new LogicGame(levelControl, scoreControl, userControl, currentPlayer, currentLevel);
        
        setLayout(new BorderLayout());
        
        // Panel superior con información
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setPreferredSize(new Dimension(800, 40));
        
        scoreLabel = new JLabel("Puntuación: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        timeLabel = new TimeLabel(); // ← Cambiado de TimerLabel() a TimeLabel()
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        levelLabel = new JLabel("Nivel: " + currentLevel);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.add(scoreLabel);
        infoPanel.add(timeLabel);
        infoPanel.add(levelLabel);
        
        topPanel.add(infoPanel, BorderLayout.CENTER);
        
        // Panel de juego
        snakePanel = new SnakePanel(logicGame);
        logicGame.setSnakePanel(snakePanel);
        logicGame.setScoreLabel(scoreLabel);
        logicGame.setTimeLabel(timeLabel);
        logicGame.setLevelLabel(levelLabel);
        
        // Panel con botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setPreferredSize(new Dimension(800, 50));
        
        CustomButton pauseButton = new CustomButton("Pausa");
        pauseButton.addActionListener(e -> logicGame.togglePause());
        
        CustomButton restartButton = new CustomButton("Reiniciar");
        restartButton.addActionListener(e -> logicGame.restartGame());
        
        CustomButton exitButton = new CustomButton("Salir");
        exitButton.addActionListener(e -> {
            logicGame.stopGame();
            new LevelsView(currentPlayer).setVisible(true);
            dispose();
        });
        
        bottomPanel.add(pauseButton);
        bottomPanel.add(restartButton);
        bottomPanel.add(exitButton);
        
        // Añadir componentes al frame
        add(topPanel, BorderLayout.NORTH);
        add(snakePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null); // Centrar ventana
        
        // Iniciar el juego
        logicGame.startGame();
    }
}