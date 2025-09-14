package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import componentes.CircleButton;
import modules.Player;
import control.UserControl;

public class LoginView extends JFrame {
    
    private JTextField nameField;
    private CircleButton playButton;
    private UserControl userControl;

    public LoginView() {
        this.userControl = new UserControl();
        
        // Configuración de la ventana
        setTitle("Snake Game - Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel("SNAKE GAME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Botón Play
        playButton = new CircleButton("▶");
        playButton.setBackground(new Color(0, 102, 204));
        playButton.setForeground(Color.BLUE);
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlayButton();
            }
        });
        
        JPanel playPanel = new JPanel();
        playPanel.setBackground(Color.WHITE);
        playPanel.add(playButton);
        mainPanel.add(playPanel, BorderLayout.CENTER);

        // Campo nombre
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel nameLabel = new JLabel("Ingresa tu nombre:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        mainPanel.add(namePanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void handlePlayButton() {
        String playerName = nameField.getText().trim();
        
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingresa tu nombre",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Player player = userControl.getOrCreatePlayer(playerName);
        openLevelsView(player);
    }

    private void openLevelsView(Player player) {
        this.dispose();
        LevelsView levelsView = new LevelsView(player);
        levelsView.setVisible(true);
    }

    public String getPlayerName() {
        return nameField.getText();
    }

    public JButton getPlayButton() {
        return playButton;
    }
}