public class PaperMonster extends Monster {
    public PaperMonster(int attackBoost, int healthBoost) {
        super(1 + attackBoost, 4 + healthBoost, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.PAPER);
        setMoveSpeed(15);
    }
}
