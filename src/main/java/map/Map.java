package map;

import game.GameForm;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.io.*;

public class Map {

    // position
    private double x;
    private double y;

    // map
    private int[][] map;
    private int numberOfRows;
    private int numberOfColumns;

    // tile set image
    private Image tileSetImage;
    private Tile[][] tiles;
    private int columnsInTileSetImage;
    private int rowsInTileSetImage;

    // drawing
    private int rowOffset;
    private int columnOffset;
    private int numberRowsToDraw;
    private int numberColsToDraw;

    public Map(String tileSetImagePath, String tileMapPath, int xPosition, int yPosition) {
        initialize(tileSetImagePath, tileMapPath);
        setPosition(xPosition, yPosition);
    }

    private void initialize(String tileSetImagePath, String tileMapPath) {
        numberRowsToDraw = GameForm.HEIGHT / Tile.SIZE;
        numberColsToDraw = GameForm.WIDTH / Tile.SIZE;

        tileSetImage = new Image(getClass().getResourceAsStream(tileSetImagePath));
        columnsInTileSetImage = (int)(tileSetImage.getWidth() / Tile.SIZE);
        rowsInTileSetImage = (int)(tileSetImage.getHeight() / Tile.SIZE);
        tiles = new Tile[rowsInTileSetImage][columnsInTileSetImage];

        InputStream inputStream = getClass().getResourceAsStream(tileMapPath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            numberOfColumns = Integer.parseInt(bufferedReader.readLine());
            numberOfRows = Integer.parseInt(bufferedReader.readLine());
            map = new int[numberOfRows][numberOfColumns];
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
                    tiles[row][column] = new Tile(tileImage, row);
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
            for (int row = 0; row < numberOfRows; row++) {
                String line = bufferedReader.readLine();
                String[] tokens = line.split(delimiter);

                for (int column = 0; column < numberOfColumns; column++) {
                    map[row][column] = Integer.parseInt(tokens[column]);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public int getTileType(int mapRow, int mapColumn) {
        return getTile(mapRow, mapColumn).getType();
    }

    private Tile getTile(int mapRow, int mapColumn) {
        int tileCode = map[mapRow][mapColumn];
        int tilesRow = tileCode / columnsInTileSetImage;
        int tilesColumn = tileCode % columnsInTileSetImage;
        return tiles[tilesRow][tilesColumn];
    }

    private void setPosition(double x, double y) {
        this.x = x;
        this.y = y;

        columnOffset = (int)-this.x / Tile.SIZE;
        rowOffset = (int)-this.y / Tile.SIZE;
    }

    public void draw(GraphicsContext graphicsContext) {
        for(int mapRow = rowOffset; mapRow < rowOffset + numberRowsToDraw; mapRow++) {
            if(mapRow >= numberOfRows) break;

            for(int mapColumn = columnOffset; mapColumn < columnOffset + numberColsToDraw; mapColumn++) {
                if(mapColumn >= numberOfColumns) break;

                Tile tile = getTile(mapRow, mapColumn);
                graphicsContext.drawImage(tile.getImage(),
                        (int)x + mapColumn * Tile.SIZE, (int)y + mapRow * Tile.SIZE);
            }
        }
    }

}