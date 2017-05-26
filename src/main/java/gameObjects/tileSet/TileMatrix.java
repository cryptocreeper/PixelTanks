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

    private Tile[][] tileSet;

    // position in pixel
    protected int x;
    protected int y;

    // tile set image
    private Image tileSetImage;
    private int columnsInTileSetImage;
    private int rowsInTileSetImage;

    // numeric matrix
    protected int[][] matrix;
    protected int matrixWidth;
    protected int matrixHeight;

    public TileMatrix(String tileSetImagePath, String tileMapPath) {
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
            matrixWidth = Integer.parseInt(bufferedReader.readLine());
            matrixHeight = Integer.parseInt(bufferedReader.readLine());
            matrix = new int[matrixHeight][matrixWidth];
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
            for (int row = 0; row < matrixHeight; row++) {
                String line = bufferedReader.readLine();
                String[] tokens = line.split(delimiter);

                for (int column = 0; column < matrixWidth; column++) {
                    matrix[row][column] = Integer.parseInt(tokens[column]);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Tile getTile(int tileSetRow, int tileSetColumn) {
        int tileCode = matrix[tileSetRow][tileSetColumn];
        int tilesRow = tileCode / columnsInTileSetImage;
        int tilesColumn = tileCode % columnsInTileSetImage;
        return tileSet[tilesRow][tilesColumn];
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext graphicsContext) {
        for(int matrixRow = 0; matrixRow < matrixHeight; matrixRow++) {
            for(int matrixColumn = 0; matrixColumn < matrixWidth; matrixColumn++) {
                Tile tile = getTile(matrixRow, matrixColumn);
                graphicsContext.drawImage(tile.getImage(),
                        (x / Tile.SIZE + matrixColumn) * Tile.SIZE, (y / Tile.SIZE + matrixRow) * Tile.SIZE);
            }
        }
    }

}
