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
	 * @param towerX the X coordinate of the tower.
	 * @param towerY the Y coordinate of the tower.
	 *****************************************************************/
	public PaperTower(int towerX, int towerY){
		cost = 10;
		this.col = towerX;
		this.row = towerY;
		towerType = TowerType.PAPER;
	}

	private double getAttackMultiplier(TowerType monsterType){
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

