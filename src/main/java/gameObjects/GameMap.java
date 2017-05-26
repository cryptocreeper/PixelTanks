package gameObjects;

import gameObjects.tileSet.TileMatrix;
import javafx.scene.canvas.GraphicsContext;

public class GameMap extends TileMatrix {

    public GameMap(String tileSetImagePath, String tileMapPath) {
        super(tileSetImagePath, tileMapPath);
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }

}
