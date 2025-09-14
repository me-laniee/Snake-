package control;

import modules.Player;
import java.util.HashMap;
import java.util.Map;

public class UserControl {
    private Map<String, Player> players;
    
    public UserControl() {
        players = new HashMap<>();
    }
    
    public Player getOrCreatePlayer(String username) {
        if (players.containsKey(username)) {
            return players.get(username);
        } else {
            Player newPlayer = new Player(username);
            players.put(username, newPlayer);
            return newPlayer;
        }
    }
    
    public void savePlayer(Player player) {
        players.put(player.getUsername(), player);
    }
}