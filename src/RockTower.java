/*****************************************************************
 * Rock Tower class and its methods.
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class RockTower extends Tower {

    /** attack range. **/
	private static int attackRange = 2;

	/*****************************************************************
	 * Constructor.
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public RockTower(int col, int row) {
		this.col = col;
		this.row = row;
		towerType = TowerType.ROCK;
		attackValue = 8;
		attackSpeed = 45; // once every 1.5 seconds
	}

	/********************************************************
	 * Sets tower's specific attack depending on monster.
	 * @param monsterType define what type the monster is.
	 * @return attack val
	 ********************************************************/
	@Override
	protected double getAttackMultiplier(TowerType monsterType) {
		// Paper beats Rock, Scissor defense Paper
		switch (monsterType) {
			case SCISSORS:
				return 2.0;
			case ROCK:
				return 1.0;
			case PAPER:
				return 0.5;
			default:
			    // do nothing
                break;
		}
		return 1.0;
	}

    /*****************************************
     * Tower cost.
     * @return cost
     *****************************************/
	public static int getCost() {
	    return 20;
	}

    /******************************
     * Gets tower's attack range.
     * @return attackRange
     ******************************/
	@Override
	public int getAttackRange() {
	    return attackRange;
	}

    /*************************************
     * Gets tower's static attack range.
     * @return attackRange
     *************************************/
	public static int getStaticAttackRange() {
	    return attackRange;
	}
}
