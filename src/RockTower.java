/*****************************************************************
 * Rock Tower class and its methods
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class RockTower extends Tower {
	private static int attackRange = 2;
	/*****************************************************************
	 * Constructor
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public RockTower(int col, int row){
		this.col = col;
		this.row = row;
		towerType = TowerType.ROCK;
		attackValue = 8;
		attackSpeed = 45; // once every 1.5 seconds
	}

	@Override
	protected double getAttackMultiplier(TowerType monsterType){
		// Paper beats Rock, Scissor defense Paper
		switch(monsterType){
			case SCISSORS:
				return 2.0;
			case ROCK:
				return 1.0;
			case PAPER:
				return 0.5;
		}
		return 1.0;
	}

	public static int getCost() {
		return 20;
	}

	@Override
	public int getAttackRange() {return attackRange;}

	public static int getStaticAttackRange() {return attackRange;}
}
