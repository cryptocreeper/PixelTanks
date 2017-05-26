package gameObjects;

import gameObjects.tileSet.MovableTileMatrix;
import javafx.scene.canvas.GraphicsContext;

public class Player extends MovableTileMatrix {

    // movement
    private int speed;

    public Player(String tileSetImagePath, String tileMapPath) {
        super(tileSetImagePath, tileMapPath);
        initialize();
    }

    private void initialize() {
        speed = 5;
    }

    private void getNextPosition() {
//        if (left) x -= speed;
//        else if (right) x += speed;
//        else if (up) y -= speed;
//        else if (down) y += speed;
    }

    public void update() {
        // update position
        getNextPosition();
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}
