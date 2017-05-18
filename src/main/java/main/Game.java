package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application implements Runnable {

    private boolean gameIsRunning;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        startGameForm(primaryStage);
        startGameLoop();
    }

    private void startGameLoop() {
        // Start game loop in new thread
        Thread gameLoopThread = new Thread(this, "gameLoop");
        gameLoopThread.start();
    }

    public void run() {
        final int FPS = 5;
        final int TIME_FOR_ONE_FRAME_IN_MILLIS = 1000 / FPS;
        long nextGameLoopTime = System.currentTimeMillis();
        gameIsRunning = true;

        while (gameIsRunning) {
            update();
            display();

            nextGameLoopTime += TIME_FOR_ONE_FRAME_IN_MILLIS;
            waitNextLoop(nextGameLoopTime);
        }
    }

    private void waitNextLoop(long nextGameLoopTime) {
        long currentTime = System.currentTimeMillis();
        long sleepTime = nextGameLoopTime - currentTime;

        if (sleepTime >= 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startGameForm(Stage primaryStage) {
        primaryStage.setTitle("Pixel Tanks");
        GameForm gameForm = new GameForm();
        Scene scene = new Scene(gameForm, 800, 400, Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update() {
        System.out.println("do update");
    }

    private void display() {
        System.out.println("do display");
    }
}