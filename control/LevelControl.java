package control;

import modules.Level;
import java.awt.Color;

public class LevelControl {
    
    public Level getLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                return new Level("Facíl", 150, 1, Color.GREEN, "F");
            case 2:
                return new Level("Medio facíl", 120, 2, Color.ORANGE, "M-F");
            case 3:
                return new Level("Intermedio", 90, 3, Color.GRAY, "I");
            case 4:
                return new Level("Poco difícil", 70, 4, Color.BLUE, "P-D");
            case 5:
                return new Level("Difícil", 50, 5, Color.RED, "D");
            default:
                return new Level("Muy difícil", 150, 1, Color.GREEN, "M-D");
        }
    }
    
    public int getTotalLevels() {
        return 5;
    }
}