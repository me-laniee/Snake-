package componentes;

import javax.swing.*;
import java.awt.*;

public class CircleButton extends JButton {

    public CircleButton(String text) {
        super(text);
        setContentAreaFilled(false); // No pintar fondo rectangular
        setFocusPainted(false);      // Quita el rectángulo de foco
        setBorderPainted(false);     // Quita borde rectangular
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Dibujar un círculo de fondo
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.WHITE); // Fondo blanco
        g2.fillOval(0, 0, getWidth(), getHeight());

        // Dibujar borde azul
        g2.setColor(new Color(0, 102, 204));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(0, 0, getWidth()-1, getHeight()-1);

        // Dibujar el texto (▶)
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60); // Tamaño del círculo
    }

    @Override
    public boolean contains(int x, int y) {
        // Detectar clic solo dentro del círculo
        int radius = getWidth() / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }
}
