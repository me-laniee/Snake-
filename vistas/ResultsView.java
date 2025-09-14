package vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import control.ScoreControl;
import modules.Score;

public class ResultsView extends JFrame {
    private ScoreControl scoreControl;
    
    public ResultsView() {
        this.scoreControl = new ScoreControl();
        initialize();
    }
    
    private void initialize() {
        setTitle("Tabla de Puntuaciones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Tabla de scores
        String[] columnNames = {"Jugador", "Puntos", "Tiempo", "Nivel", "Fecha"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Llenar tabla con scores
        for (Score score : scoreControl.getTopScores(1, 10)) { // Top 10 nivel 1
            model.addRow(new Object[]{
                score.getJugador(),
                score.getPuntos(),
                score.getTiempoFormateado(),
                score.getNivel(),
                score.getFecha().toString()
            });
        }
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Botón para ver por niveles
        JButton btnNivel1 = new JButton("Nivel 1");
        JButton btnNivel2 = new JButton("Nivel 2");
        // ... más botones ...
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNivel1);
        buttonPanel.add(btnNivel2);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
}