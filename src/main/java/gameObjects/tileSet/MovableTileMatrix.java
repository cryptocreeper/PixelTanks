package gameObjects.tileSet;

import java.util.List;

public abstract class MovableTileMatrix extends TileMatrix {

    private int xPositionOld;
    private int yPositionOld;

    private double xShift;
    private double yShift;
    private double speed;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveUpdated;

    // work only for square matrix
    private Tile[][] facingUpMatrix;
    private boolean facingLeft;
    private boolean facingRight;
    private boolean facingUp;
    private boolean facingDown;

    private TileMatrix map;
    private List<TileMatrix> gameObjects;

    public MovableTileMatrix(String tileSetImagePath, String tileMapPath, double speed, TileMatrix map,
                             List<TileMatrix> gameObjects) {
        super(tileSetImagePath, tileMapPath);
        this.speed = speed;
        this.map = map;
        this.gameObjects = gameObjects;
        centerXShift();
        centerYShift();
        initFacing();
    }

    private void initFacing() {
        facingUpMatrix = new Tile[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                facingUpMatrix[i][j] = matrix[i][j];
        facingUp = true;
    }

    private void resetFacing() {
        facingLeft = facingRight = facingUp = facingDown = false;
    }

    private void setFacingLeft() {
        resetFacing();
        facingLeft = true;
        turnMatrixLeft();
    }

    private void setFacingRight() {
        resetFacing();
        facingRight = true;
        turnMatrixRight();
    }

    private void setFacingUp() {
        resetFacing();
        facingUp = true;
        turnMatrixUp();
    }

    private void setFacingDown() {
        resetFacing();
        facingDown = true;
        turnMatrixDown();
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
        if (!moveLeft) centerXShift();
    }

    public void setMoveRight(boolean newMoveRight) {
        this.moveRight = newMoveRight;
        if (!newMoveRight) centerXShift();
    }

    public void setMoveUp(boolean newMoveUp) {
        this.moveUp = newMoveUp;
        if (!newMoveUp) centerYShift();
    }

    public void setMoveDown(boolean newMoveDown) {
        this.moveDown = newMoveDown;
        if (!newMoveDown) centerYShift();
    }

    private void turnMatrixLeft() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[matrix.length - 1 - j][i] = facingUpMatrix[i][j];
    }

    private void turnMatrixRight() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[j][matrix.length - 1 - i] = facingUpMatrix[i][j];
    }

    private void turnMatrixUp() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[i][j] = facingUpMatrix[i][j];
    }

    private void turnMatrixDown() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[matrix.length - 1 - i][matrix.length - 1 - j] = facingUpMatrix[i][j];
    }

    private void centerYShift() {
        yShift = 0.5;
    }

    private void centerXShift() {
        xShift = 0.5;
    }

    private void handleMoveLeft() {
        if (!moveUpdated && moveLeft) {
            if (!facingLeft) setFacingLeft();
            moveLeft();
        }
    }

    private void handleMoveRight() {
        if (!moveUpdated && moveRight) {
            if (!facingRight) setFacingRight();
            moveRight();
        }
    }

    private void handleMoveUp() {
        if (!moveUpdated && moveUp) {
            if (!facingUp) setFacingUp();
            moveUp();
        }
    }

    private void handleMoveDown() {
        if (!moveUpdated && moveDown) {
            if (!facingDown) setFacingDown();
            moveDown();
        }
    }

    private void moveLeft() {
        xShift -= speed;
        if (xShift < 0) {
            xPosition -= (int)-xShift + 1;
            xShift = 1 + (xShift % 1);
            if (isCollide() || isOutsideTheMap()) {
                xShift = 0;
                xPosition = xPositionOld;
            }
        }
        moveUpdated = true;
    }

    private void moveRight() {
        xShift += speed;
        if (xShift > 1) {
            xPosition += (int)xShift;
            xShift = xShift % 1;
            if (isCollide() || isOutsideTheMap()) {
                xShift = 1;
                xPosition = xPositionOld;
            }
        }
        moveUpdated = true;
    }

    private void moveUp() {
        yShift -= speed;
        if (yShift < 0) {
            yPosition -= (int)-yShift + 1;
            yShift = 1 + (yShift % 1);
            if (isCollide() || isOutsideTheMap()) {
                yShift = 0;
                yPosition = yPositionOld;
            }
        }
        moveUpdated = true;
    }

    private void moveDown() {
        yShift += speed;
        if (yShift > 1) {
            yPosition += (int)yShift;
            yShift = yShift % 1;
            if (isCollide() || isOutsideTheMap()) {
                yShift = 1;
                yPosition = yPositionOld;
            }
        }
        moveUpdated = true;
    }

    private boolean isCollide() {
        if (gameObjects == null) return false;

        for (TileMatrix gameObject : gameObjects) {
            if (gameObject == this) continue;
            if (checkCollision(gameObject)) return true;
        }

        return false;
    }

    private boolean isOutsideTheMap() {
        if (map == null) return false;

        return xPosition < 0 || yPosition < 0
                || xPosition + width > map.getWidth()
                || yPosition + height > map.getHeight();
    }

    private void saveOldPosition() {
        xPositionOld = xPosition;
        yPositionOld = yPosition;
    }

    public void update() {
        moveUpdated = false;
        saveOldPosition();
        handleMoveLeft();
        handleMoveRight();
        handleMoveUp();
        handleMoveDown();
    }
}