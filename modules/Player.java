package modules;

public class Player {
    private String username;
    private int highScore;
    
    public Player(String username) {
        this.username = username;
        this.highScore = 0;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getHighScore() {
        return highScore;
    }
    
    public void setHighScore(int score) {
        if (score > highScore) {
            highScore = score;
        }
    }
    
    @Override
    public String toString() {
        return username + " - High Score: " + highScore;
    }
}