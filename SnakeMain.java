import javax.swing.SwingUtilities;
import vistas.*;

public class SnakeMain {
    public static void main(String[] args) {
        // Ejecutar en el hilo de la interfaz
        SwingUtilities.invokeLater(() -> {
            LoginView login = new LoginView();
            login.setVisible(true);
        });
    }
}