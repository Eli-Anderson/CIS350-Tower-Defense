import java.util.Observable;

public class Game extends Observable {
    private int goldCount, currentRound;
    private Map map;
    private double targetFrameRatePerSec;
    private int currentFrame;
    private boolean isRunning;
    private static Game instance;
    public Monster m;

    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    private Game() {
        isRunning = false;
        map = new Map(16, 8);
        targetFrameRatePerSec = 0.75;
        currentRound = 1;
        goldCount = 0;
        currentFrame = 0;
        m = new Monster(1, 1, map.getPath().get(0).col, map.getPath().get(0).row);
    }

    public Map getMap() {
        return map;
    }

    int getCurrentRound() {
        return currentRound;
    }

    int getGoldCount() {
        return goldCount;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            loop();
        }
    }

    private void loop() {
        while (isRunning) {
            currentFrame++;
            //System.out.println(currentFrame);
            GUI.getInstance().clearMonsterImages();

            m.travel();
            setChanged();
            notifyObservers(m);
            try {
                Thread.sleep(Math.round(1000 / targetFrameRatePerSec));
            } catch (InterruptedException e) {
                System.out.println("An error occurred in Thread.sleep");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Game.getInstance().start();
    }
}