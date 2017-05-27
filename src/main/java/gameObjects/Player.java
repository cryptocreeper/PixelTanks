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
        speed = 3;
    }

    private void getNextPosition() {
        if (moveLeft) x -= speed;
        else if (moveRight) x += speed;
        else if (moveUp) y -= speed;
        else if (moveDown) y += speed;
    }

    public void update() {
        // update position
        getNextPosition();
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}
