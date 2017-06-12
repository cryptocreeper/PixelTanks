package gameObjects.tileSet;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class TileMatrix {

    private TileTypes tileTypes;

    protected Tile[][] matrix;
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
            matrix = new Tile[height][width];
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
                    matrix[row][column] = tileTypes.get(Integer.parseInt(tokens[column]));
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
                graphicsContext.drawImage(matrix[y][x].getImage(),
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

    public boolean checkCollision(TileMatrix anotherMatrix) {
        // 1. Пересекаются ли прямоугольные области объектов
        if (!(xPosition + (width - 1) >= anotherMatrix.xPosition &&
                xPosition <= anotherMatrix.xPosition + (anotherMatrix.width - 1) &&
                yPosition + (height - 1) >= anotherMatrix.yPosition &&
                yPosition <= anotherMatrix.yPosition + (anotherMatrix.height - 1)))
            return false;

        // 2. Находим пересечение областей
        int xStart = xPosition > anotherMatrix.xPosition ? xPosition : anotherMatrix.xPosition;
        int xEnd = xPosition + width - 1 < anotherMatrix.xPosition + anotherMatrix.width - 1 ?
                xPosition + width - 1 : anotherMatrix.xPosition + anotherMatrix.width - 1;
        int yStart = yPosition > anotherMatrix.yPosition ? yPosition : anotherMatrix.yPosition;
        int yEnd = yPosition + height - 1 < anotherMatrix.yPosition + anotherMatrix.height - 1 ?
                yPosition + height - 1 : anotherMatrix.yPosition + anotherMatrix.height - 1;

        // 3. Получаем две матрицы для сравнения
        Tile[][] partOfPlayerMatrix = getPartOfMatrixByCoordinates(this, xStart, xEnd, yStart, yEnd);
        Tile[][] partOfGameObjectMatrix = getPartOfMatrixByCoordinates(anotherMatrix, xStart, xEnd, yStart, yEnd);

        // 4. Сравнеивам матрицы на предмет коллизий
        for (int i = 0; i < partOfPlayerMatrix.length; i++)
            for (int j = 0; j < partOfPlayerMatrix[0].length; j++)
                if (partOfPlayerMatrix[i][j].getType() == Tile.BLOCKED && partOfGameObjectMatrix[i][j].getType() == Tile.BLOCKED)
                    return true;

        return false;
    }

    private Tile[][] getPartOfMatrixByCoordinates(TileMatrix tileMatrix, int x1, int x2, int y1, int y2) {
        int partX1 = x1 - tileMatrix.xPosition;
        int partX2 = x2 - tileMatrix.xPosition;
        int partY1 = y1 - tileMatrix.yPosition;
        int partY2 = y2 - tileMatrix.yPosition;

        int width = partX2 - partX1 + 1;
        int height = partY2 - partY1 + 1;

        Tile[][] partOfMatrix = new Tile[height][width];
        for (int i = partY1, iPart = 0; i <= partY2; i++, iPart++)
            for (int j = partX1, jPart = 0; j <= partX2; j++, jPart++)
                partOfMatrix[iPart][jPart] = tileMatrix.matrix[i][j];

        return partOfMatrix;
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
                SnapshotParameters snapshotParameters = new SnapshotParameters();
                snapshotParameters.setFill(Color.TRANSPARENT);
                WritableImage tileImage;

                for (int column = 0; column < columnsInTileSetImage; column++) {
                    for (int row = 0; row < rowsInTileSetImage; row++) {
                        graphicsContext.drawImage(tileSetImage,
                                column * Tile.SIZE, row * Tile.SIZE, Tile.SIZE, Tile.SIZE,
                                0, 0, Tile.SIZE, Tile.SIZE);
                        tileImage = tileCanvas.snapshot(snapshotParameters, new WritableImage(Tile.SIZE, Tile.SIZE));
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
