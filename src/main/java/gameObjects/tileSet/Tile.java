package gameObjects.tileSet;

import javafx.scene.image.Image;

public class Tile {

    public static final int SIZE = 20;

    public static final int EMPTY = 0;
    public static final int BLOCKED = 1;

    private Image image;
    private int type;

    public Tile(Image image, int type) {
        this.image = image;
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public int getType() {
        return type;
    }
}
