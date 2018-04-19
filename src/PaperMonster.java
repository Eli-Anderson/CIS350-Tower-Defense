/*************************************
 * Creates paper monster type.
 *************************************/
public class PaperMonster extends Monster {
    /******************************************
     * Constructor for PaperMonster.
     * @param attackBoost - specific for paper
     * @param healthBoost - specific for paper
     ******************************************/
    public PaperMonster(int attackBoost, int healthBoost) {
        super(1 + attackBoost, 4 + healthBoost, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.PAPER);
        setMoveSpeed(15);
    }
}
