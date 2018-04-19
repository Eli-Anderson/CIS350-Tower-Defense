
/*****************************************************************
 * Paper Tower class and its methods.
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class PaperTower extends Tower {

    /** attack range. **/
	private static int attackRange = 3;

	/*****************************************************************
	 * Constructor.
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public PaperTower(int col, int row) {
		this.col = col;
		this.row = row;
		towerType = TowerType.PAPER;
		attackValue = 2;
		attackSpeed = 30; // once a second
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
			case ROCK:
				return 2.0;
			case PAPER:
				return 1.0;
			case SCISSORS:
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
		return 15;
	}

    /******************************
     * Gets tower's attack range.
     * @return attackRange
     ******************************/
	@Override
	public int getAttackRange() {
	    return attackRange;
	}

    /********************************
     * Gets static attack range.
     * @return attackRange
     ********************************/
	public static int getStaticAttackRange() {
	    return attackRange;
	}
}

