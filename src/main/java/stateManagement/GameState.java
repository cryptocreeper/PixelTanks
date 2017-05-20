package stateManagement;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameState {

    protected GameStateManager gameStateManager;

    public abstract void init();
    public abstract void update();
    public abstract void draw(GraphicsContext graphicsContext);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);

}
