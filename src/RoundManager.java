public class RoundManager {
    private static int round = 0;
    private static int monstersSent = 0;
    private static boolean sendingMonsters = false;
    private static int upgradeRate = 5; // upgrade Monsters every 5 rounds
    private static int upgradeBonus = 5;

    public static int getRound() {
        return round;
    }

    private static void sendNextMonster() {
        int i = (int)(Math.random() * 3);
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

    public static void reset() {
        round = 0;
        sendingMonsters = false;
        monstersSent = 0;
    }

    public static void update() {
        if (Game.getInstance().getMap().getMonsters().size() == 0 && !sendingMonsters) {
            sendingMonsters = true;
            monstersSent = 0;
            round++;
        }
        if (sendingMonsters) {
            Tile pathStart = Game.getInstance().getMap().getPath().get(0);
            for (Monster m : Game.getInstance().getMap().getMonsters()) {
                if (m.getCol() == pathStart.col && m.getRow() == pathStart.row) return;
            }
            // first Tile in the path is free, so we can send a Monster
            sendNextMonster();
        }
    }
}