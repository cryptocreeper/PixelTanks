package gameObjects.tileSet;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class TileSet {

    private Tile[][] tileSet;

    // position
    private int x;
    private int y;

    // tile set image
    private Image tileSetImage;
    private int columnsInTileSetImage;
    private int rowsInTileSetImage;

    // numericMatrix
    private int[][] numericMatrix;
    private int width;
    private int height;

    public TileSet(String tileSetImagePath, String tileMapPath) {
        initialize(tileSetImagePath, tileMapPath);
    }

    private void initialize(String tileSetImagePath, String tileMapPath) {
        tileSetImage = new Image(getClass().getResourceAsStream(tileSetImagePath));
        columnsInTileSetImage = (int)(tileSetImage.getWidth() / Tile.SIZE);
        rowsInTileSetImage = (int)(tileSetImage.getHeight() / Tile.SIZE);
        tileSet = new Tile[rowsInTileSetImage][columnsInTileSetImage];

        InputStream inputStream = getClass().getResourceAsStream(tileMapPath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            width = Integer.parseInt(bufferedReader.readLine());
            height = Integer.parseInt(bufferedReader.readLine());
            numericMatrix = new int[height][width];
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadTiles();
        loadMap(bufferedReader);
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
                    tileSet[row][column] = new Tile(tileImage, row);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMap(BufferedReader bufferedReader) {
        try {
            String delimiter = "\\s+";
            for (int row = 0; row < height; row++) {
                String line = bufferedReader.readLine();
                String[] tokens = line.split(delimiter);

                for (int column = 0; column < width; column++) {
                    numericMatrix[row][column] = Integer.parseInt(tokens[column]);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Tile getTile(int tileSetRow, int tileSetColumn) {
        int tileCode = numericMatrix[tileSetRow][tileSetColumn];
        int tilesRow = tileCode / columnsInTileSetImage;
        int tilesColumn = tileCode % columnsInTileSetImage;
        return tileSet[tilesRow][tilesColumn];
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext graphicsContext) {
        for(int row = 0; row < height; row++) {
            for(int column = 0; column < width; column++) {
                Tile tile = getTile(row, column);
                graphicsContext.drawImage(tile.getImage(),
                        (x + column) * Tile.SIZE, (y + row) * Tile.SIZE);
            }
        }
    }

}
