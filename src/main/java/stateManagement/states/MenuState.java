package stateManagement.states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import stateManagement.GameState;
import stateManagement.GameStateManager;

import java.util.Random;

public class MenuState extends GameState {

    public MenuState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public void init() {

    }

    public void update() {

    }

    public void draw(GraphicsContext graphicsContext) {
        Color color = new Color(Math.random(), Math.random(), Math.random(), 1);
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, 200, 200);
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
