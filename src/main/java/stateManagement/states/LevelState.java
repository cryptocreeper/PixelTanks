package stateManagement.states;

import javafx.scene.canvas.GraphicsContext;
import gameObjects.GameMap;
import gameObjects.Player;
import javafx.scene.input.KeyCode;
import stateManagement.*;

public class LevelState extends GameState {

    private GameMap map;
    private Player player;

    public LevelState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        init();
    }

    public void init() {
        map = new GameMap("/tileSets/tileSet.png", "/maps/level.map");
        player = new Player("/tileSets/tileSet.png", "/maps/player.map");
        player.setPosition(0, 0);
    }

    public void update() {

    }

    public void draw(GraphicsContext graphicsContext) {
        map.draw(graphicsContext);
        player.draw(graphicsContext);
    }

    public void keyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) player.setUp(true);
        if (keyCode == KeyCode.DOWN) player.setDown(true);
        if (keyCode == KeyCode.LEFT) player.setLeft(true);
        if (keyCode == KeyCode.RIGHT) player.setRight(true);
    }

    public void keyReleased(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) player.setUp(false);
        if (keyCode == KeyCode.DOWN) player.setDown(false);
        if (keyCode == KeyCode.LEFT) player.setLeft(false);
        if (keyCode == KeyCode.RIGHT) player.setRight(false);
    }
}
