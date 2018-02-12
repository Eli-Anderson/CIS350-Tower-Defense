/*****************************************************************
 * CIS 250 Group Project Tower Defense Tower class and its methods
 * @author: Runquan Ye 
 * @version: T01
 * @date: winter/2018 
 *****************************************************************/
public class tower {
	/** X is for Tower's position */
	private int towerX;
	
	/** Y is for Tower's position */
	private int towerY;
	
	/** Tower's attack value */
	private int attackValue;
	
	/** Tower's attack range */
	private int attackRange;
	
	/** Tower's building cost */
	private int buildingFee;
	
	/** Tower's type (Rock, Paper, Scissor) different type will affect the damage to the monsters*/
	private Type towerType;
	
	//constructor
	public tower(int towerX, int towerY, Type towerType, int buildingFee){
		this.towerX = towerX;
		this.towerY = towerY;
		this.towerType = towerType;
		this.buildingFee = buildingFee;
		
	}

	public void typeofTower(Type touwerTyper){
		if(towerType == Type.Rock){
			attackRange = 3;
			attackValue = 50;
		}
		if(towerType == Type.Paper){
			attackRange = 4;
			attackValue = 40;
		}
		if(towerType == Type.Scissor){
			attackRange = 5;
			attackValue = 30;
		}
			
	}
	
	public void radar(){
		
		
	}
	
	public void attack(Type touwerTyper, Type MonsterType){
		
		if(towerType == Type.Rock){
			switch(MonsterType){
			case Rock:
				attackValue = 50;
			
			case Paper:
				attackValue = 35;
			
			case Scissor:
				attackValue = 65;
			}
		}
		if(towerType == Type.Paper){
			switch(MonsterType){
			case Rock:
				attackValue = 55;
			
			case Paper:
				attackValue = 40;
			
			case Scissor:
				attackValue = 25;
			}
		}
		if(towerType == Type.Scissor){
			switch(MonsterType){
			case Rock:
				attackValue = 15;
			
			case Paper:
				attackValue = 45;
			
			case Scissor:
				attackValue = 30;
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

	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public int getBuildingfee() {
		return buildingfee;
	}

	public void setBuildingfee(int buildingfee) {
		this.buildingfee = buildingfee;
	}

	public Type getTowerType() {
		return towerType;
	}

	public void setTowerType(Type towerType) {
		this.towerType = towerType;
	}
	
	
	
}
