import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
    private int goldCount;
    private Map map;
    private double targetFrameRatePerSec;
    private int currentFrame;
    private boolean isRunning;
    private static Game instance;
    public Monster m;

    /**
     * Create a Game object if one is not created yet, then return it.
     * If one is created already, just return it.
     * @return {Game} The Game instance
     */
    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    /**
     * The Game constructor. Should only be called from the getInstance() method.
     */
    private Game() {
        isRunning = false;
        map = new Map(16, 8);
        targetFrameRatePerSec = 30;
        goldCount = 20;
        currentFrame = 0;
    }

    /**
     * Get the current map
     * @return {Map} The current Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Get the current gold count
     * @return {int} The amount of gold the Player has
     */
    public int getGold() {
        return goldCount;
    }

    /**
     * Remove some gold from the Player's gold count
     * @param cost {int} The amount of gold to remove
     */
    public void removeGold(int cost) {
        goldCount -= cost;
        if (goldCount < 0) goldCount = 0;
    }

    /**
     * Add some gold to the Player's gold count
     * @param goldAmount {int} The amount of gold to add
     */
    public void claimBounty(int goldAmount) {
        goldCount += goldAmount;
    }

    /**
     * Starts the game loop if it is not already running
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            loop();
        }
    }

    /**
     * The main logic of the game. This contains a while loop with a Thread.sleep() call,
     * which allows us to handle the logic on a frame-by-frame basis. We can customize
     * the rate at which the loop runs, thus altering the frame rate of the game. The
     * loop updates the Monsters and Towers, then updates the GUI through the notifyObservers()
     * call.
     */
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
                // we delete at the end of the frame to avoid deleting
                // a Monster mid-way through a for loop, causing bugs
            }

            setChanged();
            notifyObservers(); // notify our observers (GUI)

            for (Monster m : monstersToDelete) {
                map.removeMonster(m); // remove the dead monsters
            }

            monstersToDelete.clear();

            RoundManager.update(); // RoundManager needs to update every frame, for sending Monsters properly

            try {
                Thread.sleep(Math.round(1000 / targetFrameRatePerSec));
            } catch (InterruptedException e) {
                System.out.println("An error occurred in Thread.sleep");
                break;
            }
        }
    }

    /**
     * End the game, and notify the Player
     */
    public void gameOver () {
        isRunning = false;
        new GUI.GameOverDialog();
    }

    /**
     * Reset the game
     */
    public void reset () {
        map = new Map(16, 8);
        isRunning = true;
        //@TODO: Reset game completely
    }
}