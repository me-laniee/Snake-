package modules;

import java.time.Duration;
import java.time.LocalDateTime;

public class Score {
    private int puntos;
    private LocalDateTime fecha;
    private Duration tiempoJugado;
    private String jugador;
    private int nivel;
    
    public Score(String jugador, int puntos, int nivel) {
        this.jugador = jugador;
        this.puntos = puntos;
        this.nivel = nivel;
        this.fecha = LocalDateTime.now();
        this.tiempoJugado = Duration.ZERO;
    }
    
    public Score(String jugador, int puntos, int nivel, Duration tiempoJugado) {
        this.jugador = jugador;
        this.puntos = puntos;
        this.nivel = nivel;
        this.fecha = LocalDateTime.now();
        this.tiempoJugado = tiempoJugado;
    }
    
    // Método para formatear el tiempo en minutos:segundos
    public String getTiempoFormateado() {
        long segundos = tiempoJugado.getSeconds();
        long minutos = segundos / 60;
        long segundosRestantes = segundos % 60;
        return String.format("%02d:%02d", minutos, segundosRestantes);
    }
    
    // Método para actualizar el tiempo durante el juego
    public void actualizarTiempo(Duration nuevoTiempo) {
        this.tiempoJugado = nuevoTiempo;
    }
    
    // Getters
    public int getPuntos() { return puntos; }
    public LocalDateTime getFecha() { return fecha; }
    public Duration getTiempoJugado() { return tiempoJugado; }
    public String getJugador() { return jugador; }
    public int getNivel() { return nivel; }
    
    // Para ordenar por puntuación (mayor primero)
    public int compareTo(Score otro) {
        return Integer.compare(otro.puntos, this.puntos);
    }
    
    @Override
    public String toString() {
        return jugador + " - " + puntos + " pts - " + getTiempoFormateado() + " - Nvl " + nivel;
    }
}