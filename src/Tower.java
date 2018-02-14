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
	private int distance;
	
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
	
	//constructor
	public Tower(int towerX, int towerY, Type towerType){
		this.towerX = towerX;
		this.towerY = towerY;
		this.towerType = towerType;
	}

	public void typeofTower(Type touwerTyper){
		//Rock type tower: expensive, low attack speed, small attack range, but high attack value.
		if(towerType == Type.Rock){
			attackRange = 3;
			attackValue = 125;
			buildingFee = 100;
			attackSpeed = 1;
		}
		
		//Paper type tower: all the feature are neutral.
		if(towerType == Type.Paper){
			attackRange = 4;
			attackValue = 40;
			buildingFee = 75;
			attackSpeed = 1.5;
		}
		
		//Scissor type tower: cheap, fast attack speed, large attack range, but very low attack value.
		if(towerType == Type.Scissor){
			attackRange = 5;
			attackValue = 30;
			buildingFee = 50;
			attackSpeed = 2.5;
		}
			
	}
	
	public void radar(int targetX, int targetY){
		// get monster's location
		//calculate the distance between the tower and monster
		distance = 0;
		
		if(distance < getAttackRange()){
			attack = true;
		}
	}
	
	
	
	public void fire(Type touwerTyper, Type MonsterType){
		attack(touwerTyper, MonsterType);
		if(attack == true){
			monsterHealth -= attackValue * attackSpeed;
		}
		
	}
	
	public void attack(Type touwerTyper, Type MonsterType){
		
		if(towerType == Type.Rock){
			switch(MonsterType){
			case Rock:
				break;
			
			case Paper:
				attackValue -= 15;
			
			case Scissor:
				attackValue += 15;
			}
		}
		if(towerType == Type.Paper){
			switch(MonsterType){
			case Rock:
				attackValue += 15;
			
			case Paper:
				break;
			
			case Scissor:
				attackValue -= 15;
			}
		}
		if(towerType == Type.Scissor){
			switch(MonsterType){
			case Rock:
				attackValue -= 15;
			
			case Paper:
				attackValue += 15;
			
			case Scissor:
				break;
			}
		}
		
	}
	
	
	public int geTowerX() {
		return towerX;
	}

	public void setTowerX(int towerX) {
		this.towerX = towerX;
	}

	public int getTowerY() {
		return towerY;
	}

	public void setTowerY(int towerY) {
		this.towerY = towerY;
	}

	public int getAttackValue() {
		return attackValue;
	}


	public int getAttackRange() {
		return attackRange;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public int getBuildingfee() {
		return buildingFee;
	}

	public Type getTowerType() {
		return towerType;
	}

	
	
}
