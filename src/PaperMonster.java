public class PaperMonster extends Monster {
    public PaperMonster() {
        super(1, 4, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.PAPER);
    }
}
