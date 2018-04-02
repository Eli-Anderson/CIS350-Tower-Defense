import java.util.ArrayList;

/*****************************************************************
 * CIS 350 Group Project Tower Defense Tower class and its methods
 * @author Runquan Ye
 * @version T01
 * date: winter/2018
 *****************************************************************/

public abstract class Tower {
	/** X is for Tower's position */
	public int col;

	/** Y is for Tower's position */
	public int row;

	/** Tower's attack range */
	private int attackRange = 1;

	/** Tower's attack value */
	private int attackValue = 1;

	/** Tower's building cost */
	protected int cost = 1;

	/** Tower's attack Speed */
	private int attackSpeed = 15; // 1 = every frame, 30 = every 30 frames ... 30 frames is 1 second

	/** Tower's type (Rock, Paper, Scissor) different type will affect the damage to the monsters*/
	protected TowerType towerType;

	private int framesSinceLastAttack = 999;

	private double rotation = 0.0; // rotation in Radians


	/*************************************************************************************
	 * get tower's X coordinate
	 * @return towerX the tower's X coordinate
	 ************************************************************************************/
	public int getCol() {
		return col;
	}


	/*************************************************************************************
	 * get tower's Y coordinate
	 * @return towerY the Y coordinate
	 ************************************************************************************/
	public int getRow() {
		return row;
	}




	/*************************************************************************************
	 * get tower's building cost
	 * @return integer cost
	 ************************************************************************************/
	public int getCost() {
		return cost;
	}


	public int getFramesSinceLastAttack() {
		return framesSinceLastAttack;
	}

	/*************************************************************************************
	 * get tower's category
	 * @return Type, towerType
	 ************************************************************************************/
	public TowerType getType() {
		return towerType;
	}

	public double getRotation() {
		return rotation;
	}

	private Monster getTarget(ArrayList<Monster> targets) {
		targets.sort((o1, o2) -> o2.getPathIndex() - o1.getPathIndex()); // sort by who is furthest along the path
		for (Monster m : targets) {
			if (	!m.getDeleteOnNextFrame() && // make sure it is not already dead
					Math.abs(col - m.getCol()) + Math.abs(row - m.getRow()) <= attackRange) {
				return m;
			}
		}
		return null;
	}

	/*****************************************************************
	 * Attempts to attack the
	 * @param targets the ArrayList of Monsters on the map
	 *****************************************************************/
	public void attemptAttack(ArrayList<Monster> targets){
		framesSinceLastAttack ++;
		Monster target = getTarget(targets);
		if (framesSinceLastAttack >= attackSpeed) {
			if (target == null) return;
			int dCol = target.getCol() - col;
			int dRow = target.getRow() - row;
			rotation = (-1 * Math.atan2(dCol, dRow)) + (Math.PI / 2);

			target.hurt((int)(attackValue * getAttackMultiplier(target.getType())));

			framesSinceLastAttack = 0;
		}


	}

	/*************************************************************************************
	 * different types of tower faces to different types of monster has different effects.
	 * @param monsterType define what type the monster is.
	 ************************************************************************************/
	private double getAttackMultiplier(TowerType monsterType){
		return 1.0;
	}
}
