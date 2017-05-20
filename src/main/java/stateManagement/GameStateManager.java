package stateManagement;

import javafx.scene.canvas.GraphicsContext;
import stateManagement.states.LevelState;
import stateManagement.states.MenuState;

public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;

    public static final int NUMBER_OF_GAME_STATES = 2;
    public static final int MENU_STATE = 0;
    public static final int LEVEL_STATE = 1;

    public GameStateManager() {
        gameStates = new GameState[NUMBER_OF_GAME_STATES];
        currentState = LEVEL_STATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        switch (state) {
            case MENU_STATE:
                gameStates[state] = new MenuState(this);
                break;
            case LEVEL_STATE:
                gameStates[state] = new LevelState(this);
                break;
        }
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void update() {
        try {
            gameStates[currentState].update();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext graphicsContext) {
        try {
            gameStates[currentState].draw(graphicsContext);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
