package gameObjects.tileSet;

public abstract class MovableTileMatrix extends TileMatrix {

    // work only for square matrix
    protected int[][] facingUpMatrix;

    protected boolean moveLeft;
    protected boolean moveRight;
    protected boolean moveUp;
    protected boolean moveDown;

    public MovableTileMatrix(String tileSetImagePath, String tileMapPath) {
        super(tileSetImagePath, tileMapPath);
        initFacing();
    }

    private void initFacing() {
        facingUpMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                facingUpMatrix[i][j] = matrix[i][j];
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
        if (!moveLeft) centerXInRow();
        setFacing();
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
        if (!moveRight) centerXInRow();
        setFacing();
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
        if (!moveUp) centerYInRow();
        setFacing();
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
        if (!moveDown) centerYInRow();
        setFacing();
    }

    private void turnMatrixLeft() {
        for (int i = 0; i < matrixHeight; i++)
            for (int j = 0; j < matrixWidth; j++)
                matrix[matrix.length - 1 - j][i] = facingUpMatrix[i][j];
    }

    private void turnMatrixRight() {
        for (int i = 0; i < matrixHeight; i++)
            for (int j = 0; j < matrixWidth; j++)
                matrix[j][matrix.length - 1 - i] = facingUpMatrix[i][j];
    }

    private void turnMatrixUp() {
        for (int i = 0; i < matrixHeight; i++)
            for (int j = 0; j < matrixWidth; j++)
                matrix[i][j] = facingUpMatrix[i][j];
    }

    private void turnMatrixDown() {
        for (int i = 0; i < matrixHeight; i++)
            for (int j = 0; j < matrixWidth; j++)
                matrix[matrix.length - 1 - i][matrix.length - 1 - j] = facingUpMatrix[i][j];
    }

    private void setFacing() {
        if (moveLeft) turnMatrixLeft();
        else if (moveRight) turnMatrixRight();
        else if (moveUp) turnMatrixUp();
        else if (moveDown) turnMatrixDown();
    }

    private void centerYInRow() {
        y = y - (y % Tile.SIZE) + (Tile.SIZE / 2);
    }

    private void centerXInRow() {
        x = x - (x % Tile.SIZE) + (Tile.SIZE / 2);
    }
}
