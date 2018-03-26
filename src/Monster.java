/********************************
 * CIS 359 Monster Class
 * Creates monsters to attack in
 * tower defense game
 * @author Kaylin Zaroukian
 * @version 1
 ********************************/

import java.awt.*;
import java.util.*;

public class Monster {

    /**
     * Monster's attack
     **/
    private int attack;

    /**
     * Angle
     **/
    private double angle;

    /**
     * Monster's health
     **/
    private int health;

    /**
     * Position in the path that the monster is
     **/
    private int pathIndex;

    /**
     * Hold Position X
     **/
    private int col;

    /**
     * Holds position Y
     **/
    private int row;

    /**
     * Attack Range
     **/
    private int range;

    /**
     * Attack speed bigger = slower
     **/
    private int attackSpeed;

    /**
     * Movement speed  bigger = slower
     **/
    private int moveSpeed;

    /**
     * Reward for killing the monster
     **/
    private int reward;

    /*****************************************************************
     * Creates the monster
     * @param attack - attack value of monster
     * @param health - health of monster
     * @param col - x position of monster
     * @param row - y position of monster
     *****************************************************************/
    public Monster(int attack, int health, int col, int row) {
        this.attack = attack;
        this.health = health;
        this.col = col;
        this.row = row;

    }

    /**************************************************
     * gets X position of monster on map
     * @return posX - x position of monster on map
     **************************************************/
    public int getCol() {
        return col;
    }

    /**************************************************
     * gets Y position of monster on map
     * @return posY - y position of monster on map
     **************************************************/
    public int getRow() {
        return row;
    }

    /**********************************************
     * gets the monster's attack
     * @return attack - monster's attack strength
     ***********************************************/
    public int getAttack() {
        return attack;
    }

    /**********************************************
     * sets the monster's attack
     * @param attack - monster's attack strength
     ***********************************************/
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /***********************************************
     * gets the reward
     * @return reward - reward for killing monster
     ***********************************************/
    public int getReward() {
        return reward;
    }

    /***********************************************
     * sets the reward
     * @param reward - reward for killing monster
     ***********************************************/
    public void setReward(int reward) {
        this.reward = reward;
    }

    /**************************************
     * gets the monster's health
     * @return health - monster's health
     **************************************/
    public int getHealth() {
        return health;
    }

    /***************************************
     * sets the monster's health
     * @param health - monster's health
     ***************************************/
    public void setHealth(int health) {
        this.health = health;
    }

    /*****************************************
     * Checks if monster is alive
     * If monster is dead rewards the player
     * @return true if monster alive
     *****************************************/
    public boolean alive() {
        if (health > 0) {
            return true;
        } else {
            reward = 20;
            return false;
        }
    }


    /************************************
     * when the monster is attacked
     * @param damage - health points lost
     *************************************/
    public void hurt(int damage) {
        if (health > 0) {
            health -= damage;
        } else {
            health = 0;
        }
    }

    /***********************************************
     * Moves the monster one Tile further along the path
     ***********************************************/
    public void travel() {
        ArrayList<Tile> path = Game.getInstance().getMap().getPath();
        // travels the actual path starting at the tile at the first position
        // will need to update to account for movement speed

        pathIndex ++;
        if (pathIndex >= path.size()) {
            // hurt base
            Game.getInstance().getMap().getBase().removeHealth(attack);
            // delete me
            return;
        }
        col = path.get(pathIndex).col;
        row = path.get(pathIndex).row;
    }
}


