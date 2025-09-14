package control;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private String nombre;
    private int ancho;
    private int alto;
    private Color colorFondo;
    private Color colorSerpiente;
    private Color colorComida;
    private List<Point> obstaculos;
    private List<Point> paredes;
    private boolean bordesMortales;
    private int velocidad;
    private String tema;
    
    public Mapa(String nombre, int ancho, int alto, Color colorFondo, 
        Color colorSerpiente, Color colorComida, String tema) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.alto = alto;
        this.colorFondo = colorFondo;
        this.colorSerpiente = colorSerpiente;
        this.colorComida = colorComida;
        this.tema = tema;
        this.obstaculos = new ArrayList<>();
        this.paredes = new ArrayList<>();
        this.bordesMortales = true;
        this.velocidad = 150;
    }
    
    // Diferentes tipos de mapas predefinidos
    public static Mapa crearMapaJardin() {
        Mapa mapa = new Mapa("Jardín", 20, 15, 
            new Color(144, 238, 144),    // Verde claro
            new Color(34, 139, 34),      // Verde foresta
            new Color(255, 99, 71),      // Rojo tomate
            "Naturaleza"
        );
        mapa.setBordesMortales(false);
        mapa.setVelocidad(150);
        return mapa;
    }
    
    public static Mapa crearMapaDesierto() {
        Mapa mapa = new Mapa("Desierto", 18, 12,
            new Color(245, 222, 179),    // Beige arena
            new Color(139, 69, 19),      // Marrón
            new Color(255, 140, 0),      // Naranja oscuro
            "Desierto"
        );
        // Agregar cactus (obstáculos)
        mapa.agregarObstaculo(5, 5);
        mapa.agregarObstaculo(12, 3);
        mapa.agregarObstaculo(8, 8);
        mapa.setVelocidad(120);
        return mapa;
    }
    
    public static Mapa crearMapaLaberinto() {
        Mapa mapa = new Mapa("Laberinto", 25, 18,
            new Color(70, 70, 70),       // Gris oscuro
            new Color(0, 255, 255),      // Cian
            new Color(255, 215, 0),      // Oro
            "Laberinto"
        );
        // Crear paredes del laberinto
        mapa.agregarPared(0, 0, 25, 1);   // Pared superior
        mapa.agregarPared(0, 17, 25, 1);  // Pared inferior
        mapa.agregarPared(0, 0, 1, 18);   // Pared izquierda
        mapa.agregarPared(24, 0, 1, 18);  // Pared derecha
        
        // Paredes internas
        mapa.agregarPared(5, 5, 15, 1);
        mapa.agregarPared(10, 10, 1, 8);
        mapa.setVelocidad(100);
        return mapa;
    }
    
    public static Mapa crearMapaNoche() {
        Mapa mapa = new Mapa("Noche", 22, 16,
            new Color(25, 25, 112),      // Azul noche
            new Color(50, 205, 50),      // Verde lima
            new Color(255, 255, 0),      // Amarillo (luna)
            "Nocturno"
        );
        // Agregar estrellas (puntos especiales)
        for (int i = 0; i < 10; i++) {
            mapa.agregarObstaculo((int)(Math.random() * 22), (int)(Math.random() * 16));
        }
        mapa.setBordesMortales(true);
        mapa.setVelocidad(80);
        return mapa;
    }
    
    public static Mapa crearMapaInfierno() {
        Mapa mapa = new Mapa("Infierno", 16, 12,
            new Color(139, 0, 0),        // Rojo oscuro
            new Color(255, 165, 0),      // Naranja
            new Color(255, 255, 0),      // Amarillo (fuego)
            "Infierno"
        );
        // Agregar lava (obstáculos)
        for (int i = 2; i < 14; i += 2) {
            mapa.agregarObstaculo(i, 6);
        }
        mapa.setVelocidad(60);
        return mapa;
    }
    
    // Métodos para agregar elementos al mapa
    public void agregarObstaculo(int x, int y) {
        obstaculos.add(new Point(x, y));
    }
    
    public void agregarPared(int x, int y, int ancho, int alto) {
        for (int i = x; i < x + ancho; i++) {
            for (int j = y; j < y + alto; j++) {
                paredes.add(new Point(i, j));
            }
        }
    }
    
    // Verificar colisiones con obstáculos
    public boolean hayObstaculo(int x, int y) {
        return obstaculos.contains(new Point(x, y)) || paredes.contains(new Point(x, y));
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public Color getColorFondo() { return colorFondo; }
    public Color getColorSerpiente() { return colorSerpiente; }
    public Color getColorComida() { return colorComida; }
    public List<Point> getObstaculos() { return obstaculos; }
    public List<Point> getParedes() { return paredes; }
    public boolean isBordesMortales() { return bordesMortales; }
    public int getVelocidad() { return velocidad; }
    public String getTema() { return tema; }
    
    public void setBordesMortales(boolean bordesMortales) { this.bordesMortales = bordesMortales; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
}