public class Game {
    private int goldCount, currentRound;
    private Map currentMap;
    private int targetFrameRatePerSec, currentFrame;
    public boolean isRunning;
    public Game() {
        isRunning = false;
        currentMap = new Map(8,8);
        targetFrameRatePerSec = 1;
        currentRound = 1;
        goldCount = 0;
        currentFrame = 0;
    }

    Map getCurrentMap() {
        return currentMap;
    }
    int getCurrentRound() {
        return currentRound;
    }
    int getGoldCount() {
        return goldCount;
    }
    public static void main(String[] args) {
        new Game();
    }
    public void start() {
        if (!isRunning) {
            loop();
            isRunning = true;
        }
    }
    private void loop() {
        while (isRunning) {
            currentFrame ++;
            System.out.println(currentFrame);
            try {
                Thread.sleep(1000 / targetFrameRatePerSec);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
