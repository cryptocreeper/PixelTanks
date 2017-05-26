package gameObjects.tileSet;

public abstract class MovableTileMatrix extends TileMatrix {

    // work only for square matrix
    protected int[][] facingUpMatrix;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public MovableTileMatrix(String tileSetImagePath, String tileMapPath) {
        super(tileSetImagePath, tileMapPath);
        initFacing();
    }

    private void initFacing() {
        facingUpMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                facingUpMatrix[i][j] = matrix[i][j];

        setUp(true);
    }

    public void setLeft(boolean left) {
        this.left = left;
        if (left) turnMatrixLeft();
    }

    public void setRight(boolean right) {
        this.right = right;
        if (right) turnMatrixRight();
    }

    public void setUp(boolean up) {
        this.up = up;
        if (up) turnMatrixUp();
    }

    public void setDown(boolean down) {
        this.down = down;
        if (down) turnMatrixDown();
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
}
