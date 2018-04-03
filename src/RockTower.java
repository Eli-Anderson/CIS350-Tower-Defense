/*****************************************************************
 * Rock Tower class and its methods
 * @author Runquan Ye
 * @version PT01
 * date: winter/2018
 *****************************************************************/

public class RockTower extends Tower {
	/*****************************************************************
	 * Constructor
	 * @param col the X coordinate of the tower.
	 * @param row the Y coordinate of the tower.
	 *****************************************************************/
	public RockTower(int col, int row){
		cost = 10;
		this.col = col;
		this.row = row;
		towerType = TowerType.ROCK;
	}
	@Override
	protected double getAttackMultiplier(TowerType monsterType){
		// Paper beats Rock, Scissor defense Paper
		switch(monsterType){
			case SCISSORS:
				return 3.0/2.0; // 1.5 (1.5x increase)
			case ROCK:
				return 1.0;
			case PAPER:
				return 2.0/3.0; // 0.66 (1.5x decrease)
		}
		return 1.0;
	}
}
