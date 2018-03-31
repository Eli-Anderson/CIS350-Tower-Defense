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
     * Movement speed
     **/
    private int moveSpeed;

    /**
     * Reward for killing the monster
     **/
    private int reward;

    private TowerType type;

    private boolean deleteOnNextFrame;

    private int framesSinceLastTravel = 0;

    /*****************************************************************
     * Creates the monster
     * @param attack - attack value of monster
     * @param health - health of monster
     * @param col - x position of monster
     * @param row - y position of monster
     *****************************************************************/
    public Monster(int attack, int health, int col, int row, TowerType type) {
        this.attack = attack;
        this.health = health;
        this.col = col;
        this.row = row;
        moveSpeed = 30;
        reward = 1;
        this.type = type;
        deleteOnNextFrame = false;
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

    public int getPathIndex() {
        return pathIndex;
    }

    public TowerType getType() {
        return type;
    }

    /**********************************************
     * gets the monster's attack
     * @return attack - monster's attack strength
     ***********************************************/
    public int getAttack() {
        return attack;
    }


    /***********************************************
     * gets the reward
     * @return reward - reward for killing monster
     ***********************************************/
    public int getReward() {
        return reward;
    }

    /**************************************
     * gets the monster's health
     * @return health - monster's health
     **************************************/
    public int getHealth() {
        return health;
    }


    /*****************************************
     * Checks if monster is alive
     * @return true if monster alive
     *****************************************/
    public boolean isDead() {
        return health <= 0 || deleteOnNextFrame;
    }

    public boolean getDeleteOnNextFrame() {
        return deleteOnNextFrame;
    }


    /************************************
     * when the monster is attacked
     * @param damage - health points lost
     *************************************/
    public void hurt(int damage) {
        if (deleteOnNextFrame) return; // monster is already dead
        health -= damage;
        if (health <= 0) {
            //flag for deletion
            deleteOnNextFrame = true;
            Game.getInstance().claimBounty(reward);
        }
    }

    /***********************************************
     * Moves the monster one Tile further along the path
     ***********************************************/
    private void travel() {
        ArrayList<Tile> path = Game.getInstance().getMap().getPath();
        // travels the actual path starting at the tile at the first position

        pathIndex ++;
        if (pathIndex >= path.size()) {
            // hurt base
            Game.getInstance().getMap().getBase().removeHealth(attack);
            // flag for deletion
            deleteOnNextFrame = true;
            return;
        }
        col = path.get(pathIndex).col;
        row = path.get(pathIndex).row;
    }

    public void attemptTravel() {
        framesSinceLastTravel ++;
        if (framesSinceLastTravel >= moveSpeed) {
            travel();
            framesSinceLastTravel = 0;
        }
    }


}


