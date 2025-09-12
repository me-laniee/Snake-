package vistas;

import javax.swing.*;
import java.awt.*;
import componentes.CircleButton;

public class LoginView extends JFrame {
    
    private JTextField nameField;
    private JButton playButton;

    public LoginView() {
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

        // --------- Título ---------
        JLabel titleLabel = new JLabel("SNAKE GAME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // --------- Botón Play (círculo con triángulo) ---------
       playButton = new CircleButton("▶"); // 👈 Aquí usas tu clase personalizada
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.setForeground(new Color(0, 102, 204)); // Triángulo azul
        
        JPanel playPanel = new JPanel();
        playPanel.setBackground(Color.WHITE);
        playPanel.add(playButton);
        mainPanel.add(playPanel, BorderLayout.CENTER);

        // --------- Campo nombre ---------
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel nameLabel = new JLabel("nombre:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        mainPanel.add(namePanel, BorderLayout.SOUTH);

        // Añadir al frame
        add(mainPanel);
    }

    // Getter para el nombre ingresado
    public String getPlayerName() {
        return nameField.getText();
    }

    // Getter para el botón
    public JButton getPlayButton() {
        return playButton;
    }
}
