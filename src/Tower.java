import java.util.ArrayList;

/*****************************************************************
 * CIS 350 Group Project Tower Defense Tower class and its methods
 * @author Runquan Ye
 * @version T01
 * date: winter/2018
 *****************************************************************/

public abstract class Tower {
	/** X is for Tower's position */
	public int towerX;

	/** Y is for Tower's position */
	public int towerY;

	/** Tower's attack range */
	private int attackRange = 1;

	/** Tower's attack value */
	private int attackValue = 1;

	/** Tower's building cost */
	protected int cost = 1;

	/** Tower's attack Speed */
	private double attackSpeed = 1;

	/** Tower's type (Rock, Paper, Scissor) different type will affect the damage to the monsters*/
	private TowerType towerType = TowerType.PAPER;


	/*************************************************************************************
	 * get tower's X coordinate
	 * @return towerX the tower's X coordinate
	 ************************************************************************************/
	public int getTowerX() {
		return towerX;
	}


	/*************************************************************************************
	 * get tower's Y coordinate
	 * @return towerY the Y coordinate
	 ************************************************************************************/
	public int getTowerY() {
		return towerY;
	}

	/*************************************************************************************
	 * get tower's attack value
	 * @return integer attackValue
	 ************************************************************************************/
	public int getAttackValue() {
		return attackValue;
	}



	/*************************************************************************************
	 * get tower's attack range
	 * @return integer attackRange
	 ************************************************************************************/
	public int getAttackRange() {
		return attackRange;
	}



	/*************************************************************************************
	 * get tower's attack speed
	 * @return integer attackSpeed
	 ************************************************************************************/
	public double getAttackSpeed() {
		return attackSpeed;
	}



	/*************************************************************************************
	 * get tower's building cost
	 * @return integer cost
	 ************************************************************************************/
	public int getCost() {
		return cost;
	}



	/*************************************************************************************
	 * get tower's category
	 * @return Type, towerType
	 ************************************************************************************/
	public TowerType getTowerType() {
		return towerType;
	}

	private Monster getTarget(ArrayList<Monster> targets) {
		targets.sort((o1, o2) -> o2.getPathIndex() - o1.getPathIndex()); // sort by who is furthest along the path
		for (Monster m : targets) {
			if (Math.abs(towerX - m.getCol()) + Math.abs(towerY - m.getRow()) <= attackRange) {
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
		Monster target = getTarget(targets);

		if (target == null) return;
		System.out.println("target attacked");
		target.hurt((int)(attackValue * getAttackMultiplier(target.type)));
		if (target.isDead()) {
			Game.getInstance().claimBounty(target.getReward());
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
