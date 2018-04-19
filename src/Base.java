/********************************
 * CIS 359 Base Class.
 * Creates base
 * tower defense game
 * @author Rose Ault
 * @version 1
 ********************************/

public class Base {

    /**
     * Base health.
     * */
    private int health;
    /** Base position x. **/
    private int col;
    /** Base position y. **/
    private int row;

    /*************************************
     * creates base at row and column.
     * @param health - base health
     * @param row - x coord
     * @param col - y coord
     *************************************/
    public Base(int health, int col, int row) {
        this.health = health;
        this.col = col;
        this.row = row;
    }

    /**********************************
     * Gets column location of base.
     *
     * @return col - y coord
     **********************************/
    public int getCol() {
        return col;
    }

    /******************************
     * Gets row location of base.
     *
     * @return row - x coord
     ******************************/
    public int getRow() {
        return row;
    }

    /**********************************
     * Gets health of base.
     *
     * @return health - base health
     *********************************/
    public int getHealth() {
        return health;
    }

    /******************************************************************
     * Removes health from base, if health is less then 0, game over.
     *
     * @param dmg - damage to base
     ******************************************************************/
    public void removeHealth(int dmg) {
        this.health -=  dmg;
        if (this.health <= 0) {
            Game.getInstance().gameOver();
        }
    }

    /******************************
     * Sets the health.
     *
     * @param h - base health
     ******************************/
    public void setHealth(int h) {
        this.health = h;
    }
}
