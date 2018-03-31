public class RockMonster extends Monster {
    public RockMonster() {
        super(1, 1, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.ROCK);
    }
}