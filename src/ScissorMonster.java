/*************************************
 * Creates scissor monster type.
 *************************************/
public class ScissorMonster extends Monster {
    /*******************************************
     * Constructor for ScissorMonster.
     * @param attackBoost - specific for scissor
     * @param healthBoost - specific for scissor
     ********************************************/
    public ScissorMonster(int attackBoost, int healthBoost) {
        super(3 + attackBoost, 7 + healthBoost, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.SCISSORS);
        setMoveSpeed(30);
    }
}
