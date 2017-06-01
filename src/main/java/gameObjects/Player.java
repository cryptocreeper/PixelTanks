package gameObjects;

import gameObjects.tileSet.MovableTileMatrix;
import gameObjects.tileSet.Tile;
import gameObjects.tileSet.TileMatrix;
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

    public boolean checkCollision(TileMatrix gameObjectMatrix) {
        // 1. Пересекаются ли прямоугольные области объектов
        if (!(this.matrixX + (width - 1) >= gameObjectMatrix.matrixX &&
                this.matrixX <= gameObjectMatrix.matrixX + (gameObjectMatrix.width - 1) &&
                this.matrixY + (height - 1) >= gameObjectMatrix.matrixY &&
                this.matrixY <= gameObjectMatrix.matrixY + (gameObjectMatrix.height - 1)))
            return false;

        // 2. Находим пересечение областей
        int xStart = matrixX > gameObjectMatrix.matrixX ? matrixX : gameObjectMatrix.matrixX;
        int xEnd = matrixX + width - 1 < gameObjectMatrix.matrixX + gameObjectMatrix.width - 1 ?
                matrixX + width - 1 : gameObjectMatrix.matrixX + gameObjectMatrix.width - 1;
        int yStart = matrixY > gameObjectMatrix.matrixY ? matrixY : gameObjectMatrix.matrixY;
        int yEnd = matrixY + height - 1 < gameObjectMatrix.matrixY + gameObjectMatrix.height - 1 ?
                matrixY + height - 1 : gameObjectMatrix.matrixY + gameObjectMatrix.height - 1;

        // 3. Получаем две матрицы для сравнения
        int[][] partOfPlayerMatrix = getPartOfMatrixByGlobalCoords(this, xStart, xEnd, yStart, yEnd);
        int[][] partOfGameObjectMatrix = getPartOfMatrixByGlobalCoords(gameObjectMatrix, xStart, xEnd, yStart, yEnd);

        // 4. Сравнеивам матрицы на предмет коллизий
        for (int i = 0; i < partOfPlayerMatrix.length; i++)
            for (int j = 0; j < partOfPlayerMatrix[0].length; j++)
                if (partOfPlayerMatrix[i][j] == Tile.BLOCKED && partOfGameObjectMatrix[i][j] == Tile.BLOCKED)
                    return true;

        return false;
    }

    private int[][] getPartOfMatrixByGlobalCoords(TileMatrix tileMatrix, int globalX1, int globalX2, int globalY1, int globalY2) {
        int partX1 = globalX1 - tileMatrix.matrixX;
        int partX2 = globalX2 - tileMatrix.matrixX;
        int partY1 = globalY1 - tileMatrix.matrixY;
        int partY2 = globalY2 - tileMatrix.matrixY;

        int width = partX2 - partX1 + 1;
        int height = partY2 - partY1 + 1;

        int[][] partOfMatrix = new int[height][width];
        for (int i = partY1, iPart = 0; i <= partY2; i++, iPart++)
            for (int j = partX1, jPart = 0; j <= partX2; j++, jPart++)
                partOfMatrix[iPart][jPart] = tileMatrix.matrix[i][j];

        return partOfMatrix;
    }

    public void calculateNewPosition() {
        if (moveLeft) xNew = x - speed;
        else if (moveRight) xNew = x + speed;
        else if (moveUp) yNew = y - speed;
        else if (moveDown) yNew = y + speed;
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}
