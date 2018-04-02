import java.util.ArrayList;

/*****************************************************************
 * Paper Tower class and its methods
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class PaperTower extends Tower {
	/*****************************************************************
	 * Constructor
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public PaperTower(int col, int row){
		cost = 10;
		this.col = col;
		this.row = row;
		towerType = TowerType.PAPER;
	}
	@Override
	protected double getAttackMultiplier(TowerType monsterType){
		// Paper beats Rock, Scissor defense Paper
		switch(monsterType){
			case ROCK:
				return 3/2; // 1.5
			case PAPER:
				return 1;
			case SCISSORS:
				return 2/3; // 0.66 (1.5x decrease)
		}
		return 1;
	}
}

