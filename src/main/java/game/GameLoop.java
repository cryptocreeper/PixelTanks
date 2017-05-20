package game;

class GameLoop implements Runnable {

    private Game game;
    private boolean isRunning;

    GameLoop(Game game) {
        this.game = game;
    }

    void start() {
        isRunning = true;
        startGameLoop();
    }

    void stop() {
        isRunning = false;
    }

    private void startGameLoop() {
        // Start game loop in new thread
        Thread gameLoopThread = new Thread(this, "gameLoop");
        gameLoopThread.start();
    }

    // Game loop thread
    public void run() {
        final int FPS = 30;
        final int TIME_FOR_ONE_FRAME_IN_MILLIS = 1000 / FPS;
        long nextGameLoopTime = System.currentTimeMillis();

        while (isRunning) {
            game.update();
            game.draw();

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
}
