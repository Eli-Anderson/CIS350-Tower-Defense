public class RoundManager {
    private static int round = 0;
    private static int monstersSent = 0;
    private static boolean sendingMonsters = false;

    public static int getRound() {
        return round;
    }
    private static void sendNextMonster() {
        Game.getInstance().getMap().addMonster(new PaperMonster());
        monstersSent++;
        if (monstersSent >= round) {
            sendingMonsters = false;
        }
    }

    public static void update() {
        if (Game.getInstance().getMap().getMonsters().size() == 0) {
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
