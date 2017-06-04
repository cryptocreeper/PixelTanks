package gameObjects.tileSet;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class TileMatrix {

    private TileTypes tileTypes;

    protected Tile[][] tileMatrix;
    protected int xPosition;
    protected int yPosition;
    protected int width;
    protected int height;

    public TileMatrix(String tileSetImagePath, String tileMapPath) {
        initialize(tileSetImagePath, tileMapPath);
    }

    private void initialize(String tileSetImagePath, String tileMapPath) {
        tileTypes = new TileTypes(tileSetImagePath);

        InputStream inputStream = getClass().getResourceAsStream(tileMapPath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            width = Integer.parseInt(bufferedReader.readLine());
            height = Integer.parseInt(bufferedReader.readLine());
            tileMatrix = new Tile[height][width];
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadMap(bufferedReader);
    }

    // TODO: Выделить map в отедльный класс. Избавиться от задания размеров в начале файлов .map
    private void loadMap(BufferedReader bufferedReader) {
        try {
            String delimiter = "\\s+";
            for (int row = 0; row < height; row++) {
                String line = bufferedReader.readLine();
                String[] tokens = line.split(delimiter);

                for (int column = 0; column < width; column++) {
                    tileMatrix[row][column] = tileTypes.get(Integer.parseInt(tokens[column]));
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void draw(GraphicsContext graphicsContext) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                graphicsContext.drawImage(tileMatrix[y][x].getImage(),
                        (xPosition + x) * Tile.SIZE, (yPosition + y) * Tile.SIZE);
            }
        }
    }

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private class TileTypes {

        private Image tileSetImage;
        private int columnsInTileSetImage;
        private int rowsInTileSetImage;

        private Tile[][] tileTypes;

        TileTypes(String tileSetImagePath) {
            initialize(tileSetImagePath);
            loadTiles();
        }

        private void initialize(String tileSetImagePath) {
            tileSetImage = new Image(getClass().getResourceAsStream(tileSetImagePath));
            columnsInTileSetImage = (int)(tileSetImage.getWidth() / Tile.SIZE);
            rowsInTileSetImage = (int)(tileSetImage.getHeight() / Tile.SIZE);
            tileTypes = new Tile[rowsInTileSetImage][columnsInTileSetImage];
        }

        private void loadTiles() {
            try {
                Canvas tileCanvas = new Canvas(Tile.SIZE, Tile.SIZE);
                GraphicsContext graphicsContext = tileCanvas.getGraphicsContext2D();
                WritableImage tileImage;

                for (int column = 0; column < columnsInTileSetImage; column++) {
                    for (int row = 0; row < rowsInTileSetImage; row++) {
                        graphicsContext.drawImage(tileSetImage,
                                column * Tile.SIZE, row * Tile.SIZE, Tile.SIZE, Tile.SIZE,
                                0, 0, Tile.SIZE, Tile.SIZE);
                        tileImage = tileCanvas.snapshot(null, new WritableImage(Tile.SIZE, Tile.SIZE));
                        tileTypes[row][column] = new Tile(tileImage, row);
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        Tile get(int cellCode) {
            int tilesRow = cellCode / columnsInTileSetImage;
            int tilesColumn = cellCode % columnsInTileSetImage;
            return tileTypes[tilesRow][tilesColumn];
        }

    }

}
