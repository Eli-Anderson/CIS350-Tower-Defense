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
        targetFrameRatePerSec = 30;
        currentRound = 1;
        goldCount = 40;
        currentFrame = 0;
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
            for (Monster m : map.getMonsters()) { // update monster positions
                m.attemptTravel();
            }

            for (Tower t : map.getTowers()) { // have each tower try to attack
                t.attemptAttack(map.getMonsters());
            }

            for (Monster m : map.getMonsters()) { // check if the monster is dead
                if (m.getDeleteOnNextFrame())
                    monstersToDelete.add(m);      // add it to a different array so we can delete it
            }

            setChanged();
            notifyObservers();

            for (Monster m : monstersToDelete) { // remove the dead monsters
                map.removeMonster(m);
            }

            monstersToDelete.clear();

            RoundManager.getInstance().update();

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
        map = new Map(16, 8);
        isRunning = true;
        //TODO: Reset game completely
    }

    public static void main(String[] args) {
        Game.getInstance().start();
    }
}