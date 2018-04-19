/*****************************************************************
 * Scissor Tower class and its methods.
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class ScissorTower extends Tower {

    /** attack range. **/
	private static int attackRange = 5;

	/*****************************************************************
	 * Constructor.
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public ScissorTower(int col, int row) {

		this.col = col;
		this.row = row;
		attackValue = 1;
		attackSpeed = 15; // twice a second
		towerType = TowerType.SCISSORS;
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
			case PAPER:
				return 2.0;
			case SCISSORS:
				return 1.0;
			case ROCK:
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
	    return 10;
	}

    /******************************
     * Gets tower's attack range.
     * @return attackRange
     ******************************/
	@Override
	public int getAttackRange() {
	    return attackRange;
	}

    /**************************************
     * Gets tower's static attack range.
     * @return attackRange
     **************************************/
	public static int getStaticAttackRange() {
	    return attackRange;
	}
}
