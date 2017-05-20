package stateManagement.states;

import javafx.scene.canvas.GraphicsContext;
import map.Map;
import stateManagement.*;

public class LevelState extends GameState {

    private Map map;

    public LevelState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        init();
    }

    public void init() {
        map = new Map("/tileSets/tileSet.png", "/maps/level.map", 0, 0);
    }

    public void update() {

    }

    public void draw(GraphicsContext graphicsContext) {
        map.draw(graphicsContext);
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
