package stateManagement.states;

import gameObjects.tileSet.TileMatrix;
import gameObjects.GameMap;
import gameObjects.Player;
import stateManagement.*;
import javafx.scene.input.KeyCode;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class LevelState extends GameState {

    private GameMap map;
    private Player player;
    private List<TileMatrix> levelObjects;

    public LevelState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        init();
    }

    public void init() {
        map = new GameMap("/tileSets/tileSet.png", "/matrices/level.map");
        player = new Player("/tileSets/tileSet.png", "/matrices/player.map", 0.2);
        levelObjects = new ArrayList<TileMatrix>();
        levelObjects.add(map);
        levelObjects.add(player);

        player.setGameObjects(levelObjects);
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
