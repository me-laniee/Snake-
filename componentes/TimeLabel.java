package componentes;

import javax.swing.JLabel;
import java.awt.Font;

public class TimeLabel extends JLabel {
    
    public TimeLabel() {
        super("00:00");
        setFont(new Font("Arial", Font.BOLD, 16));
    }
    
    public void updateTime(long segundosTotales) {
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        setText(String.format("%02d:%02d", minutos, segundos));
    }
    
    public void updateTime(int minutos, int segundos) {
        setText(String.format("%02d:%02d", minutos, segundos));
    }
    
    // Método simple para usar desde LogicGame
    public void setTiempo(String tiempoFormateado) {
        setText(tiempoFormateado);
    }
}