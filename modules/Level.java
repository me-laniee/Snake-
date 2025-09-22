package modules;

import java.awt.Color;

public class Level {
    private String name;
    private int speed;
    private int difficulty;
    private Color color;
    
    /**
     * Constructor completo para crear un nivel
     * @param name
     * @param speed
     * @param difficulty
     * @param color

     */
    public Level(String name, int speed, int difficulty, Color color, String emoji) {
        this.name = name;
        this.speed = speed;
        this.difficulty = difficulty;
        this.color = color;
    }
    
    // GETTERS - Métodos para acceder a las propiedades del nivel
    
    /** @return Nombre del nivel*/
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