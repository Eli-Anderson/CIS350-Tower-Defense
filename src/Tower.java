/*****************************************************************
 * CIS 350 Group Project Tower Defense Tower class and its methods
 * @author: Runquan Ye
 * @version: T01
 * @date: winter/2018
 *****************************************************************/

public class Tower {
	/**get connect to the monster class*/
	public Monster target;
	/** X is for Tower's position */
	public int towerX;

	/** Y is for Tower's position */
	public int towerY;

	/** The distence between tower and monster */
	public double distance;

	/** get the monster health from monster class */
	public int monsterHealth;


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
}
