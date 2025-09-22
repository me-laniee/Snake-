package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import componentes.CustomButton;
import modules.Player;
import modules.Level;
import control.LevelControl;

public class LevelsView extends JFrame {
    private Player currentPlayer;
    private LevelControl levelControl;
    
    public LevelsView(Player player) {
        this.currentPlayer = player;
        this.levelControl = new LevelControl();
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Snake Game - Seleccionar Nivel");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Título
        JLabel titleLabel = new JLabel("Selecciona un Nivel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(new Color(0, 86, 63)); // Verde Esmeralda Oscuro
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel de niveles
        JPanel levelsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        levelsPanel.setBackground(new Color(192, 223, 192)); // Verde Menta Suave
        levelsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botones de niveles
        for (int i = 1; i <= levelControl.getTotalLevels(); i++) {
            Level level = levelControl.getLevel(i);
            CustomButton levelButton = new CustomButton("Nivel " + i + " - " + level.getName());
            levelButton.setFont(new Font("Arial", Font.BOLD, 18));
            
            if (i == 1) {
                levelButton.setBackground(new Color(76, 175, 80)); // Verde
            } else if (i == 2) {
                levelButton.setBackground(new Color(255, 152, 0)); // Naranja
            } else {
                levelButton.setBackground(new Color(244, 67, 54)); // Rojo
            }
            
            final int levelNumber = i;
            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startGame(levelNumber);
                }
            });
            
            levelsPanel.add(levelButton);
        }
        
        mainPanel.add(levelsPanel, BorderLayout.CENTER);
        
        // Botón de volver
        CustomButton backButton = new CustomButton("Volver al inicio");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToLogin();
            }
        });
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void startGame(int levelNumber) {
        this.dispose();
        GameView gameView = new GameView(currentPlayer, levelNumber);
        gameView.setVisible(true);
    }
    
    private void returnToLogin() {
        this.dispose();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}