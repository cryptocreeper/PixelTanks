package gameObjects;

import gameObjects.tileSet.MovableTileMatrix;
import gameObjects.tileSet.TileMatrix;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Player extends MovableTileMatrix {

    public Player(String tileSetImagePath, String tileMapPath, double speed, TileMatrix map,
                  List<TileMatrix> gameObjects) {
        super(tileSetImagePath, tileMapPath, speed, map, gameObjects);
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}