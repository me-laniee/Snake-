package database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Historial {

    private static final String FILE_NAME = "historial.txt";

    // Guardar un nuevo puntaje
    public static void guardarPuntaje(String jugador, int nivel, int puntaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(jugador + " | Nivel: " + nivel + " | Puntaje: " + puntaje);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    // Leer todos los puntajes
    public static List<String> leerHistorial() {
        List<String> historial = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                historial.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No hay historial aún o hubo un error: " + e.getMessage());
        }
        return historial;
    }

    // Mostrar historial en consola
    public static void mostrarHistorial() {
        List<String> historial = leerHistorial();
        if (historial.isEmpty()) {
            System.out.println("No hay registros de puntajes todavía.");
        } else {
            System.out.println("===== HISTORIAL DE PUNTAJES =====");
            for (String linea : historial) {
                System.out.println(linea);
            }
        }
    }
}
