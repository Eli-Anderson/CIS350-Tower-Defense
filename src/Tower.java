/*****************************************************************
 * CIS 350 Group Project Tower Defense Tower class and its methods
 * @author Runquan Ye
 * @version T01
 * date: winter/2018
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
	 * @return towerX the tower's X coordinate
	 ************************************************************************************/
	public int getTowerX() {
		return towerX;
	}


	/*************************************************************************************
	 * set tower's X coordinate
	 * @param towerX the X coordinate
	 ************************************************************************************/
	public void setTowerX(int towerX) {
		this.towerX = towerX;
	}


	/*************************************************************************************
	 * get tower's Y coordinate
	 * @return towerY the Y coordinate
	 ************************************************************************************/
	public int getTowerY() {
		return towerY;
	}


	/*************************************************************************************
	 * set tower's Y coordinate
	 * @param towerY the Y coordinate
	 ************************************************************************************/
	public void setTowerY(int towerY) {
		this.towerY = towerY;
	}
}
