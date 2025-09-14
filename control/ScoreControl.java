package control;

import modules.Score;
import modules.Player;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ScoreControl {
    private List<Score> scores;
    
    public ScoreControl() {
        scores = new ArrayList<>();
        // Aquí luego cargaríamos scores de archivo o base de datos
    }
    
    // Actualizar score existente o crear uno nuevo
    public void updateScore(Player player, int newScore, int nivel, Duration tiempoJugado) {
        // Buscar si ya existe un score para este jugador y nivel
        Score scoreExistente = null;
        for (Score score : scores) {
            if (score.getJugador().equals(player.getUsername()) && score.getNivel() == nivel) {
                scoreExistente = score;
                break;
            }
        }
        
        if (scoreExistente != null) {
            // Actualizar solo si el nuevo score es mayor
            if (newScore > scoreExistente.getPuntos()) {
                scoreExistente = new Score(player.getUsername(), newScore, nivel, tiempoJugado);
                // Reemplazar en la lista
                scores.remove(scoreExistente);
                scores.add(scoreExistente);
            }
        } else {
            // Crear nuevo score
            scores.add(new Score(player.getUsername(), newScore, nivel, tiempoJugado));
        }
        
        // Actualizar high score del jugador
        if (newScore > player.getHighScore()) {
            player.setHighScore(newScore);
        }
        
        // Ordenar scores por puntuación
        scores.sort((s1, s2) -> Integer.compare(s2.getPuntos(), s1.getPuntos()));
    }
    
    // Obtener top scores para un nivel específico
    public List<Score> getTopScores(int nivel, int cantidad) {
        List<Score> topScores = new ArrayList<>();
        for (Score score : scores) {
            if (score.getNivel() == nivel) {
                topScores.add(score);
                if (topScores.size() >= cantidad) {
                    break;
                }
            }
        }
        return topScores;
    }
    
    // Obtener todos los scores de un jugador
    public List<Score> getPlayerScores(String username) {
        List<Score> playerScores = new ArrayList<>();
        for (Score score : scores) {
            if (score.getJugador().equals(username)) {
                playerScores.add(score);
            }
        }
        return playerScores;
    }
    
    // Guardar scores (para implementar luego con archivos)
    public void saveScores() {
        // TODO: Implementar guardado en archivo
    }
    
    // Cargar scores (para implementar luego)
    public void loadScores() {
        // TODO: Implementar carga desde archivo
    }
}