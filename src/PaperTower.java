import java.util.ArrayList;

/*****************************************************************
 * Paper Tower class and its methods
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class PaperTower extends Tower {
	private static int attackRange = 3;
	/*****************************************************************
	 * Constructor
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public PaperTower(int col, int row){
		this.col = col;
		this.row = row;
		towerType = TowerType.PAPER;
		attackValue = 2;
		attackSpeed = 30; // once a second
	}
	@Override
	protected double getAttackMultiplier(TowerType monsterType){
		// Paper beats Rock, Scissor defense Paper
		switch(monsterType){
			case ROCK:
				return 2.0;
			case PAPER:
				return 1.0;
			case SCISSORS:
				return 0.5;
		}
		return 1.0;
	}

	public static int getCost() {
		return 15;
	}

	@Override
	public int getAttackRange() {return attackRange;}

	public static int getStaticAttackRange() {return attackRange;}
}

