package modules;

import java.awt.Color;

/**
 * Representa un nivel del juego con todas sus características
 * Cada nivel tiene diferente dificultad, velocidad y tema visual
 */
public class Level {
    private String name;        // Nombre descriptivo del nivel
    private int speed;          // Velocidad en milisegundos (menos = más rápido)
    private int difficulty;     // Nivel de dificultad (1-5 estrellas)
    private Color color;        // Color principal del tema
    
    /**
     * Constructor completo para crear un nivel
     * @param name Nombre del nivel
     * @param speed Velocidad en milisegundos
     * @param difficulty Nivel de dificultad (1-5)
     * @param color Color temático

     */
    public Level(String name, int speed, int difficulty, Color color, String emoji) {
        this.name = name;
        this.speed = speed;
        this.difficulty = difficulty;
        this.color = color;
    }
    
    // GETTERS - Métodos para acceder a las propiedades del nivel
    
    /** @return Nombre del nivel (ej: "Jardín", "Desierto") */
    public String getName() { return name; }
    
    /** @return Velocidad en ms (intervalo entre movimientos) */
    public int getSpeed() { return speed; }
    
    /** @return Nivel de dificultad (1-5) */
    public int getDifficulty() { return difficulty; }
    
    /** @return Color temático del nivel */
    public Color getColor() { return color; }
    
    /**
     * Representación textual del nivel para debugging
     */
    @Override
    public String toString() {
        return name + " (" + speed + "ms)";
    }
}