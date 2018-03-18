/*****************************************************************
 * Rock Tower class and its methods
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class RockTower extends Tower {

	/** Tower's attack range */
	private int attackRange = 3;
	
	/** Tower's attack value */
	private int attackValue = 125;
	
	/** Tower's building cost */
	private int buildingFee = 100;
	
	/** Tower's attack Speed */
	private double attackSpeed = 1;

	/** Tower's type (Rock, Paper, Scissor) different type will affect the damage to the monsters*/
	private Type towerType = Type.Rock;
	
	/** To check should the tower attack */
	public boolean attack = false;
	
	
	/*****************************************************************
	 * Constructor
	 * @param towerX the X coordinate of the tower.
	 * @param towerY the Y coordinate of the tower.
	 *****************************************************************/
	public RockTower(int towerX, int towerY){
		this.towerX = towerX;
		this.towerY = towerY;
	}
	
	
	
	/*****************************************************************
	 * to sense the monster under the fire attack range
	 * @param targetX, the X coordinate of the monster.
	 * @param targetY, the Y coordinate of the monster.
	 *****************************************************************/
	public void radar(int targetX, int targetY){
		// get monster's location
		//calculate the distance between the tower and monster
		distance = Math.sqrt(Math.pow(towerX - targetX,2) + Math.pow((towerY - targetY),2));
		Math.abs(distance);

		if(distance < getAttackRange()){
			attack = true;
		}
	}
	
	
	
	/*************************************************************************************
	 * attack the monsters
	 * @param monsterType, define what type the monster is.
	 ************************************************************************************/
	public void fire(Type monsterType){
		attackEffect(monsterType);
		if(attack == true){
			monsterHealth -= attackValue * attackSpeed;
		}
	}
	
	
	
	/*************************************************************************************
	 * different types of tower faces to different types of monster has different effects.
	 * @param monsterType, define what type the monster is.
	 ************************************************************************************/
	public void attackEffect(Type monsterType){
		// Paper beats Rock, Scissor defense Paper
			switch(monsterType){
			case Rock:
				attackValue += 15;
				break;

			case Paper:
				break;

			case Scissor:
				attackValue -= 15;
				break;
			}
		
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
	 * @return integer buildingFee
	 ************************************************************************************/
	public int getBuildingfee() {
		return buildingFee;
	}

	

	/*************************************************************************************
	 * get tower's category
	 * @return Type towerType
	 ************************************************************************************/
	public Type getTowerType() {
		return towerType;
	}
}
