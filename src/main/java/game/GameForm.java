package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameForm {

    private Game game;
    private Stage primaryStage;
    private Canvas canvas;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 400;

    GameForm(Game game, Stage primaryStage) {
        this.game = game;
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        canvas = new Canvas(WIDTH, HEIGHT);
        BorderPane gameForm = new BorderPane(canvas);
        Scene scene = new Scene(gameForm, 400, 600, Color.LIGHTBLUE);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                game.close();
            }
        });
        primaryStage.setTitle("Pixel Tanks");
        primaryStage.setScene(scene);
    }

    GraphicsContext getGraphicContext() {
        return canvas.getGraphicsContext2D();
    }

    void show() {
        primaryStage.show();
    }

}
