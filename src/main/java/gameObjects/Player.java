package gameObjects;

import gameObjects.tileSet.MovableTileMatrix;
import javafx.scene.canvas.GraphicsContext;

public class Player extends MovableTileMatrix {

    public Player(String tileSetImagePath, String tileMapPath, double speed) {
        super(tileSetImagePath, tileMapPath, speed);
    }

    /*public boolean checkCollision(TileMatrix gameObjectMatrix) {
        // 1. Пересекаются ли прямоугольные области объектов
        if (!(this.xPosition + (width - 1) >= gameObjectMatrix.xPosition &&
                this.xPosition <= gameObjectMatrix.xPosition + (gameObjectMatrix.width - 1) &&
                this.yPosition + (height - 1) >= gameObjectMatrix.yPosition &&
                this.yPosition <= gameObjectMatrix.yPosition + (gameObjectMatrix.height - 1)))
            return false;

        // 2. Находим пересечение областей
        int xStart = xPosition > gameObjectMatrix.xPosition ? xPosition : gameObjectMatrix.xPosition;
        int xEnd = xPosition + width - 1 < gameObjectMatrix.xPosition + gameObjectMatrix.width - 1 ?
                xPosition + width - 1 : gameObjectMatrix.xPosition + gameObjectMatrix.width - 1;
        int yStart = yPosition > gameObjectMatrix.yPosition ? yPosition : gameObjectMatrix.yPosition;
        int yEnd = yPosition + height - 1 < gameObjectMatrix.yPosition + gameObjectMatrix.height - 1 ?
                yPosition + height - 1 : gameObjectMatrix.yPosition + gameObjectMatrix.height - 1;

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
        int partX1 = globalX1 - tileMatrix.xPosition;
        int partX2 = globalX2 - tileMatrix.xPosition;
        int partY1 = globalY1 - tileMatrix.yPosition;
        int partY2 = globalY2 - tileMatrix.yPosition;

        int width = partX2 - partX1 + 1;
        int height = partY2 - partY1 + 1;

        int[][] partOfMatrix = new int[height][width];
        for (int i = partY1, iPart = 0; i <= partY2; i++, iPart++)
            for (int j = partX1, jPart = 0; j <= partX2; j++, jPart++)
                partOfMatrix[iPart][jPart] = tileMatrix.matrix[i][j];

        return partOfMatrix;
    }
    */

    public void update() {
        super.update();
    }

    public void draw(GraphicsContext graphicsContext) {
        super.draw(graphicsContext);
    }
}
