/*****************************************************************
 * Scissor Tower class and its methods
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class ScissorTower extends Tower {
	/*****************************************************************
	 * Constructor
	 * @param towerX the X coordinate of the tower.
	 * @param towerY the Y coordinate of the tower.
	 *****************************************************************/
	public ScissorTower(int towerX, int towerY){
		cost = 10;
		this.towerX = towerX;
		this.towerY = towerY;
	}

	private double getAttackMultiplier(TowerType monsterType){
		// Paper beats Rock, Scissor defense Paper
		switch(monsterType){
			case PAPER:
				return 3/2; // 1.5
			case SCISSORS:
				return 1;
			case ROCK:
				return 2/3; // 0.66 (1.5x decrease)
		}
		return 1;
	}
}
