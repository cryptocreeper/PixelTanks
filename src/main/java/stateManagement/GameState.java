package stateManagement;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public abstract class GameState {

    protected GameStateManager gameStateManager;

    public abstract void init();
    public abstract void update();
    public abstract void draw(GraphicsContext graphicsContext);
    public abstract void keyPressed(KeyCode keyCode);
    public abstract void keyReleased(KeyCode keyCode);

}
