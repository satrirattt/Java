public class Game {
    private static int highScore;

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int score) {
        highScore = score;
    }

    private static GameState gameState = GameState.RUNNING;

    public static GameState getState() {
        return gameState;
    }

    public static void setState(GameState state) {
        gameState = state;
    }
}

enum GameState {
    RUNNING, OVER, CONTINUE
}
