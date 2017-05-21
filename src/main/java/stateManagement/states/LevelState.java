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
        player.setPosition(3, 17);
    }

    public void update() {

    }

    public void draw(GraphicsContext graphicsContext) {
        map.draw(graphicsContext);
        player.draw(graphicsContext);
    }

    public void keyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) System.out.println("pressed UP");
        if (keyCode == KeyCode.DOWN) System.out.println("pressed DOWN");
    }

    public void keyReleased(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) System.out.println("released UP");
        if (keyCode == KeyCode.DOWN) System.out.println("released DOWN");
    }
}
