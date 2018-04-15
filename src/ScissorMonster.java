public class ScissorMonster extends Monster {
    public ScissorMonster(int attackBoost, int healthBoost) {
        super(3 + attackBoost, 7 + healthBoost, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.SCISSORS);
        setMoveSpeed(30);
    }
}
