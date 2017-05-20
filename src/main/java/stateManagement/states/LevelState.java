package stateManagement.states;

import javafx.scene.canvas.GraphicsContext;
import stateManagement.GameState;
import stateManagement.GameStateManager;

public class LevelState extends GameState {

    public LevelState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public void init() {

    }

    public void update() {
        System.out.println("Update LevelState");
    }

    public void draw(GraphicsContext graphicsContext) {
        System.out.println("Display LevelState");
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
