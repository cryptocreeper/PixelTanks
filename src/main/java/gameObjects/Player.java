package gameObjects;

import gameObjects.tileSet.TileSet;
import javafx.scene.canvas.GraphicsContext;

public class Player extends TileSet {

    public Player(String tileSetImagePath, String tileMapPath) {
        super(tileSetImagePath, tileMapPath);
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}
