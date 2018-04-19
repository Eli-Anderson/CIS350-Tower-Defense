/*************************************
 * Creates rock monster type.
 *************************************/
public class RockMonster extends Monster {
    /******************************************
     * Constructor for RockMonster.
     * @param attackBoost - specific for rock
     * @param healthBoost - specific for rock
     ******************************************/
    public RockMonster(int attackBoost, int healthBoost) {
        super(5 + attackBoost, 15 + healthBoost, Game.getInstance().getMap().getPath().get(0).col,
                Game.getInstance().getMap().getPath().get(0).row, TowerType.ROCK);
        setMoveSpeed(45);
    }
}
