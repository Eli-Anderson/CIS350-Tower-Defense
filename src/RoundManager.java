import java.util.Random;

/*******************************
 * Class to manage game rounds.
 *******************************/
public class RoundManager {

    /** current round #. **/
    private static int round = 0;

    /** number of monsters sent that round. **/
    private static int monstersSent = 0;

    /** whether or not monsters are sent. **/
    private static boolean sendingMonsters = false;

    /** upgrade rate. **/
    private static int upgradeRate = 5; // upgrade Monsters every 5 rounds

    /** upgrade bonus. **/
    private static int upgradeBonus = 5;

    /*******************************
     * Gets current round.
     * @return round
     *******************************/
    public static int getRound() {
        return round;
    }

    /****************************************
     * Decides when to send next monster.
     ****************************************/
    private static void sendNextMonster() {
        Random r = new Random();
        int i = r.nextInt(3);
        Monster m;
        int bonus = upgradeBonus * (round / upgradeRate);
        switch (i) {
            case 0:
                m = new PaperMonster(bonus, bonus);
                break;
            case 1:
                m = new RockMonster(bonus, bonus);
                break;
            case 2:
                m = new ScissorMonster(bonus, bonus);
                break;
            default:
                m = new PaperMonster(bonus, bonus);
                break;
        }
        Game.getInstance().getMap().addMonster(m);
        monstersSent++;
        if (monstersSent >= round) {
            sendingMonsters = false;
        }
    }

    /*****************************
     * Resets the rounds.
     *****************************/
    public static void reset() {
        round = 0;
        sendingMonsters = false;
        monstersSent = 0;
    }

    /*********************************
     * Updates for each round.
     *********************************/
    public static void update() {
        if (Game.getInstance().getMap().getMonsters().size() == 0 && !sendingMonsters) {
            sendingMonsters = true;
            monstersSent = 0;
            round++;
        }
        if (sendingMonsters) {
            Tile pathStart = Game.getInstance().getMap().getPath().get(0);
            for (Monster m : Game.getInstance().getMap().getMonsters()) {
                if (m.getCol() == pathStart.col && m.getRow() == pathStart.row) {
                    return;
                }
            }
            // first Tile in the path is free, so we can send a Monster
            sendNextMonster();
        }
    }
}
