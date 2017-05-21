package gameObjects;

import gameObjects.tileSet.TileSet;
import javafx.scene.canvas.GraphicsContext;

public class GameMap extends TileSet {

    public GameMap(String tileSetImagePath, String tileMapPath) {
        super(tileSetImagePath, tileMapPath);
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }

}
