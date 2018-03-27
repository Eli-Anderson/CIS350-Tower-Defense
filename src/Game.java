import java.util.ArrayList;
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
        targetFrameRatePerSec = 3;
        currentRound = 1;
        goldCount = 40;
        currentFrame = 0;
        map.addMonster(new Monster(10, 3, map.getPath().get(0).col, map.getPath().get(0).row, TowerType.PAPER));
    }

    public Map getMap() {
        return map;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getGold() {
        return goldCount;
    }

    public void removeGold(int cost) {
        goldCount -= cost;
        if (goldCount < 0) goldCount = 0;
    }

    public void claimBounty(int goldAmount) {
        goldCount += goldAmount;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            loop();
        }
    }

    private void loop() {
        ArrayList<Monster> monstersToDelete;
        while (isRunning) {
            currentFrame++;

            GUI.getInstance().clearMonsterImages();

            monstersToDelete = new ArrayList<>();
            for (Monster m : map.getMonsters()) {
                m.travel();
                if (m.getDeleteOnNextFrame())
                    monstersToDelete.add(m);
                setChanged();
                notifyObservers(m);
                //@TODO Check if it is possible to notify the GUI after all monsters are updated, instead of each iteration
            }
            for (Monster m : monstersToDelete) {
                map.removeMonster(m);
            }

            for (Tower t : map.getTowers()) {
                t.attemptAttack(map.getMonsters());
            }

            monstersToDelete.clear();

            try {
                Thread.sleep(Math.round(1000 / targetFrameRatePerSec));
            } catch (InterruptedException e) {
                System.out.println("An error occurred in Thread.sleep");
                break;
            }
        }
    }

    public void gameOver () {
        isRunning = false;
        new GUI.GameOverDialog();
    }

    public void reset () {
        System.out.println("Reset");
        map = new Map(16, 8);
        isRunning = true;
    }

    public static void main(String[] args) {
        Game.getInstance().start();
    }
}