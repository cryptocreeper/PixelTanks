public class Game {

    private boolean gameIsRunning = true;

    public void start() {
        startGameLoop();
    }

    private void startGameLoop() {
        final int FPS = 5;
        final int TIME_FOR_ONE_FRAME_IN_MILLIS = 1000 / FPS;
        long nextGameLoopTime = System.currentTimeMillis();

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

    private void update() {
        System.out.println("do update");
    }

    private void display() {
        System.out.println("do display");
    }

}
