package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
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
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo personalizado
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Fondo con degradado verde
                GradientPaint gradient = new GradientPaint(0, 0, new Color(23, 107, 16), 
                getWidth(), getHeight(), new Color(11, 54, 7));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Patrón de serpiente sutil en el fondo
                g2d.setColor(new Color(34, 139, 27, 50));
                for (int i = 0; i < getWidth(); i += 40) {
                    for (int j = 0; j < getHeight(); j += 40) {
                        g2d.fillOval(i, j, 8, 8);
                    }
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Panel de contenido con transparencia
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(0, 30));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Título con estilo de juego
        JLabel titleLabel = new JLabel("SNAKE GAME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 56));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Efecto de sombra para el título
        titleLabel.setForeground(new Color(240, 240, 240));
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(46, 184, 36), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel central para el botón
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        // Botón Play rediseñado
        playButton = new CircleButton("▶");
        playButton.setBackground(new Color(46, 184, 36));
        playButton.setForeground(Color.WHITE);
        playButton.setPreferredSize(new Dimension(195, 195));
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(new Color(35, 120, 23), 3));
        
        // Efecto de hover para el botón
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButton.setBackground(new Color(57, 219, 43));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButton.setBackground(new Color(46, 184, 36));
            }
        });
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlayButton();
            }
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        centerPanel.add(playButton, gbc);
        
        contentPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel para el campo de nombre
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        
        JLabel nameLabel = new JLabel("INGRESA TU NOMBRE:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.GRAY);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setMaximumSize(new Dimension(300, 40));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(46, 184, 36), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        namePanel.add(nameLabel);
        namePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        namePanel.add(nameField);
        
        contentPanel.add(namePanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void handlePlayButton() {
        String playerName = nameField.getText().trim();
        
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingresa tu nombre para empezar a jugar",
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