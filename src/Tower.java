/*****************************************************************
 * CIS 350 Group Project Tower Defense Tower class and its methods
 * @author: Runquan Ye 
 * @version: T01
 * @date: winter/2018 
 *****************************************************************/

public class Tower {
	/**get connect to the monster class*/
	private Monster target;
	/** X is for Tower's position */
	private int towerX;
	
	/** Y is for Tower's position */
	private int towerY;
	
	/** The distence between tower and monster */
	private double distance;
	
	/** Tower's attack value */
	private int attackValue;
	
	/** Tower's attack Speed */
	private double attackSpeed;
	
	/** Tower's attack range */
	private int attackRange;
	
	/** Tower's building cost */
	private int buildingFee;
	
	/** get the monster health from monster class */
	private int monsterHealth;	
	
	/** Tower's type (Rock, Paper, Scissor) different type will affect the damage to the monsters*/
	private Type towerType;
	
	/** To check should the tower attack */
	private boolean attack = false;
	
	
	/*****************************************************************
	 * Constructor
	 * @param: towerX, the X coordinate of the tower.
	 * @param: towerY, the Y coordinate of the tower.
	 * @param: towerType, define what type the tower is.
	 *****************************************************************/
	public Tower(int towerX, int towerY, Type towerType){
		this.towerX = towerX;
		this.towerY = towerY;
		this.towerType = towerType;
	}

	
	/*****************************************************************
	 * different type of tower has different features
	 * @param: towerType, define what type the tower is.
	 *****************************************************************/
	public void typeofTower(Type touwerTyper){
		//Rock type tower: expensive, low attack speed, small attack range, but high attack value.
		switch(towerType){
			case Rock:
				attackRange = 3;
				attackValue = 125;
				buildingFee = 100;
				attackSpeed = 1;
				break;
			
		
		//Paper type tower: all the feature are neutral.
			case Paper:
				attackRange = 4;
				attackValue = 40;
				buildingFee = 75;
				attackSpeed = 1.5;
				break;
		
		//Scissor type tower: cheap, fast attack speed, large attack range, but very low attack value.
			case Scissor:
				attackRange = 5;
				attackValue = 30;
				buildingFee = 50;
				attackSpeed = 2.5;
				break;
		
		}		
	}
	
	
	/*****************************************************************
	 * to sense the monster under the fire attack range
	 * @param: targetX, the X coordinate of the monster.
	 * @param: targetY, the Y coordinate of the monster.
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
	 * different types of tower faces to different types of monster has different effects.
	 * @param: towerType, define what type the tower is.
	 * @param: monsterType, define what type the monster is.
	 ************************************************************************************/
	public void attackEffect(Type touwerTyper, Type MonsterType){
		// Rock beats Scissor, Paper defense Rock
		if(towerType == Type.Rock){
			switch(MonsterType){
			case Rock:
				break;
			
			case Paper:
				attackValue -= 15;
				break;
			
			case Scissor:
				attackValue += 15;
				break;
			}
		}
		
		// Paper beats Rock, Scissor defense Paper
		if(towerType == Type.Paper){
			switch(MonsterType){
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
		
		// Scissor beats Paper, Rock defense Scissor
		if(towerType == Type.Scissor){
			switch(MonsterType){
			case Rock:
				attackValue -= 15;
				break;
			
			case Paper:
				attackValue += 15;
				break;
			
			case Scissor:
				break;
			}
		}
		
	}
	
	
	/*************************************************************************************
	 * attack the monsters
	 * @param: towerType, define what type the tower is.
	 * @param: monsterType, define what type the monster is.
	 ************************************************************************************/
	public void fire(Type touwerTyper, Type monsterType){
		attackEffect(touwerTyper, monsterType);
		if(attack == true){
			monsterHealth -= attackValue * attackSpeed;
		}
		
	}
	
	
	/*************************************************************************************
	 * get tower's X coordinate
	 * @return: integer towerX 
	 ************************************************************************************/
	public int geTowerX() {
		return towerX;
	}

	
	/*************************************************************************************
	 * set tower's X coordinate
	 * @param: integer towerX
	 ************************************************************************************/
	public void setTowerX(int towerX) {
		this.towerX = towerX;
	}

	
	/*************************************************************************************
	 * get tower's Y coordinate
	 * @return: integer towerY 
	 ************************************************************************************/
	public int getTowerY() {
		return towerY;
	}

	
	/*************************************************************************************
	 * set tower's Y coordinate
	 * @param: integer towerY
	 ************************************************************************************/
	public void setTowerY(int towerY) {
		this.towerY = towerY;
	}

	
	/*************************************************************************************
	 * get tower's attack value
	 * @return: integer attackValue 
	 ************************************************************************************/
	public int getAttackValue() {
		return attackValue;
	}


	/*************************************************************************************
	 * get tower's attack range
	 * @return: integer attackRange 
	 ************************************************************************************/
	public int getAttackRange() {
		return attackRange;
	}

	/*************************************************************************************
	 * get tower's attack speed
	 * @return: integer attackSpeed 
	 ************************************************************************************/
	public double getAttackSpeed() {
		return attackSpeed;
	}

	
	/*************************************************************************************
	 * get tower's building cost
	 * @return: integer buildingFee 
	 ************************************************************************************/
	public int getBuildingfee() {
		return buildingFee;
	}

	
	/*************************************************************************************
	 * get tower's category
	 * @return: Type, towerType 
	 ************************************************************************************/
	public Type getTowerType() {
		return towerType;
	}

}
