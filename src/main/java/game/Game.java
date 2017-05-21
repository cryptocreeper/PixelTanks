package game;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import stateManagement.GameStateManager;

public class Game extends Application {

    private GameStateManager gameStateManager;
    private GameLoop gameLoop;
    private GameForm gameForm;
    private GraphicsContext graphicsContext;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initialize(primaryStage);
        gameLoop.start();
        gameForm.show();
    }

    private void initialize(Stage primaryStage) {
        gameStateManager = new GameStateManager();
        gameLoop = new GameLoop(this);
        gameForm = new GameForm(this, primaryStage);
        graphicsContext = gameForm.getGraphicContext();
    }

    void keyPressed(KeyCode keyCode) {
        gameStateManager.keyPressed(keyCode);
    }

    void keyReleased(KeyCode keyCode) {
        gameStateManager.keyReleased(keyCode);
    }

    void update() {
        gameStateManager.update();
    }

    void draw() {
        gameStateManager.draw(graphicsContext);
    }

    void close() {
        gameLoop.stop();
    }
}