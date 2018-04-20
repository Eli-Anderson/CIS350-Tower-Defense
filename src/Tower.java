import java.util.ArrayList;

/*****************************************************************
 * CIS 350 Group Project Tower Defense Tower class and its methods.
 * @author Runquan Ye
 * @version T01
 * date: winter/2018
 *****************************************************************/

public abstract class Tower {

	/** X is for Tower's position. */
	protected int col;

	/** Y is for Tower's position. */
	protected int row;

	/** Tower's attack value. */
	protected int attackValue = 1;

	/** Tower's attack Speed. */
	protected int attackSpeed = 15; // 1 = every frame, 30 = every 30 frames ... 30 frames is 1 second

	/** Tower's type (Rock, Paper, Scissor) different type will affect the damage to the monsters.*/
	protected TowerType towerType;

	/** Gets frames since last attack. **/
	private int framesSinceLastAttack = 999;

	/** Rotation of tower. **/
	private double rotation = 0.0; // rotation in Radians


	/*************************************************************************************
	 * Get tower's Y coordinate.
	 * @return col the tower's Y coordinate
	 ************************************************************************************/
	public int getCol() {
		return col;
	}


	/*************************************************************************************
	 * Get tower's X coordinate.
	 * @return row the x coordinate
	 ************************************************************************************/
	public int getRow() {
		return row;
	}

	/*************************************************************************************
	 * Get tower's building cost.
	 * This is a static method, because the cost of each Tower type will be consistent
	 * between all Towers of that type, and this way we can access it straight from the
	 * class instead of needing to create a Tower object solely to check its cost.
	 * @return integer cost
	 ************************************************************************************/
	public static int getCost() {
		return 0;
	}

    /*******************************
     * Get tower's attack range.
     * @return 0
     *******************************/
	public int getAttackRange() {
	    return 0;
	}

    /***************************************
     * Gets # of frames since last attack.
     * @return framesSinceLastAttack
     ***************************************/
	public int getFramesSinceLastAttack() {
		return framesSinceLastAttack;
	}

	/*************************************************************************************
	 * Get tower's category.
	 * @return Type, towerType
	 ************************************************************************************/
	public TowerType getType() {
	    return towerType;
	}

    /********************************
     * Gets tower's rotation.
     * @return rotation
     ********************************/
	public double getRotation() {
		return rotation;
	}


    /***************************************
     * Tower's current target.
     * @param targets - targeted monsters
     * @return monster targeted
     ****************************************/
	public Monster getTarget(ArrayList<Monster> targets) {
		targets.sort((o1, o2) -> o2.getPathIndex() - o1.getPathIndex()); // sort by who is furthest along the path
		for (Monster m : targets) {
			if (!m.getDeleteOnNextFrame() && // make sure it is not already dead
					Math.abs(col - m.getCol()) + Math.abs(row - m.getRow()) <= this.getAttackRange()) {
				return m;
			}
		}
		return null;
	}

	/*****************************************************************
	 * Attempts to attack the monsters on map.
	 * @param targets the ArrayList of Monsters on the map
	 *****************************************************************/
	public void attemptAttack(ArrayList<Monster> targets) {
		framesSinceLastAttack++;
		Monster target = getTarget(targets);
		if (framesSinceLastAttack >= attackSpeed) {
			if (target == null) {
			    return;
            }
			int dCol = target.getCol() - col;
			int dRow = target.getRow() - row;
			rotation = (-1 * Math.atan2(dCol, dRow)) + (Math.PI / 2);

			target.hurt(((double)attackValue) * getAttackMultiplier(target.getType()));

			framesSinceLastAttack = 0;
		}


	}

	/*************************************************************************************
	 * Different types of tower faces to different types of monster has different effects.
	 * @param monsterType define what type the monster is.
	 ************************************************************************************/
	protected abstract double getAttackMultiplier(TowerType monsterType);


}
