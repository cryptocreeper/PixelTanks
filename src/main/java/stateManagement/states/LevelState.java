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
        map = new GameMap("/tileSets/tileSet.png", "/matrices/level.map");
        player = new Player("/tileSets/tileSet.png", "/matrices/player.map", 0.2);
        player.setPosition(1, 1);
    }

    public void update() {
        player.update();
    }

    public void draw(GraphicsContext graphicsContext) {
        map.draw(graphicsContext);
        player.draw(graphicsContext);
    }

    public void keyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) player.setMoveUp(true);
        if (keyCode == KeyCode.DOWN) player.setMoveDown(true);
        if (keyCode == KeyCode.LEFT) player.setMoveLeft(true);
        if (keyCode == KeyCode.RIGHT) player.setMoveRight(true);
    }

    public void keyReleased(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) player.setMoveUp(false);
        if (keyCode == KeyCode.DOWN) player.setMoveDown(false);
        if (keyCode == KeyCode.LEFT) player.setMoveLeft(false);
        if (keyCode == KeyCode.RIGHT) player.setMoveRight(false);
    }
}
