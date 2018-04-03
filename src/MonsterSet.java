public class MonsterSet {
    private TowerType monsterType;
    private int numOfMonsters;
    MonsterSet(TowerType type, int num) {
        monsterType = type;
        numOfMonsters = num;
    }

    public TowerType getMonsterType() {
        return monsterType;
    }

    public int getNumOfMonsters() {
        return numOfMonsters;
    }
}