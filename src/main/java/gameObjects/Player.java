package gameObjects;

import gameObjects.tileSet.MovableTileMatrix;
import javafx.scene.canvas.GraphicsContext;

public class Player extends MovableTileMatrix {

    public Player(String tileSetImagePath, String tileMapPath, double speed) {
        super(tileSetImagePath, tileMapPath, speed);
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}