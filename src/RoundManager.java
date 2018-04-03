public class RoundManager {
    private int round = 0;
    private int setIndex = 0;
    private int monstersSent = 0;
    private boolean sendingMonsters = false;
    private Round[] roundTable;
    private static RoundManager instance;

    private RoundManager() {
        roundTable = new Round[] {
            new Round(new MonsterSet[]{ new MonsterSet(TowerType.PAPER, 2)}),
            new Round(new MonsterSet[]{ new MonsterSet(TowerType.ROCK, 2)}),
            new Round(new MonsterSet[]{ new MonsterSet(TowerType.SCISSORS, 2)}),
            new Round(new MonsterSet[]{ new MonsterSet(TowerType.PAPER, 2),
                                        new MonsterSet(TowerType.SCISSORS, 2)}),
            new Round(new MonsterSet[]{ new MonsterSet(TowerType.ROCK, 2),
                                        new MonsterSet(TowerType.PAPER, 2)}),
            new Round(new MonsterSet[]{ new MonsterSet(TowerType.SCISSORS, 2),
                                        new MonsterSet(TowerType.ROCK, 2)}),
        };
        //@TODO: Create more rounds
    }
    public static RoundManager getInstance() {
        if (RoundManager.instance == null) {
            RoundManager.instance = new RoundManager();
        }
        return RoundManager.instance;
    }

    public int getRound() {
        return round;
    }
    private void sendNextMonster() {
        switch(roundTable[round].sets[setIndex].getMonsterType()) {
            case ROCK:
                Game.getInstance().getMap().addMonster(new RockMonster());
                break;
            case PAPER:
                Game.getInstance().getMap().addMonster(new PaperMonster());
                break;
            case SCISSORS:
                Game.getInstance().getMap().addMonster(new ScissorMonster());
                break;
        }
        monstersSent++;
        int totalMonsters = 0;
        if (round < roundTable.length) { // do not allow Player to surpass the amount of created rounds
            for (MonsterSet set : roundTable[round].sets) {
                totalMonsters += set.getNumOfMonsters();
                if (monstersSent == totalMonsters) {
                    setIndex++;
                }
            }
        }

        if (monstersSent >= totalMonsters) {
            sendingMonsters = false;
        }
    }

    public void update() {
        if (Game.getInstance().getMap().getMonsters().size() == 0) {
            sendingMonsters = true;
            monstersSent = 0;
            setIndex = 0;
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
