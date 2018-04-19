/********************************
 * CIS 359 Monster Class
 * Creates monsters to attack in
 * tower defense game
 * @author Kaylin Zaroukian
 * @version 1
 ********************************/

import java.awt.*;
import java.util.*;

/****************************************
 * Class to create monsters for game.
 ****************************************/
public class Monster {

    /**
     * Monster's attack.
     **/
    private int attack;
    /**
     * Angle.
     **/
    private double rotation;
    /**
     * Monster's health.
     **/
    private double health;
    /**
     * Position in the path that the monster is.
     **/
    private int pathIndex;
    /**
     * Hold Position Y.
     **/
    private int col;
    /**
     * Holds position X.
     **/
    private int row;
    /**
     * Movement speed.
     **/
    private int moveSpeed;
    /**
     * Reward for killing the monster.
     **/
    private int reward;
    /** Tower type. **/
    private TowerType type;
    /** decides when to delete monster. **/
    private boolean deleteOnNextFrame;
    /** frames since monster last travelled. **/
    private int framesSinceLastTravel = 0;

    /*****************************************************************
     * Creates the monster.
     * @param attack - attack value of monster
     * @param health - health of monster
     * @param col - y position of monster
     * @param row - x position of monster
     * @param type - monster type
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
     * gets Y position of monster on map.
     * @return col - y position of monster on map
     **************************************************/
    public int getCol() {
        return col;
    }

    /**************************************************
     * Gets X position of monster on map.
     * @return row - X position of monster on map
     **************************************************/
    public int getRow() {
        return row;
    }

    /********************************************
     * Gets path index of monster.
     * @return pathIndex - index of path
     ********************************************/
    public int getPathIndex() {
        return pathIndex;
    }

    /***************************************
     * Gets Tower Type.
     * @return type - tower type
     ***************************************/
    public TowerType getType() {
        return type;
    }

    /**********************************************
     * Gets the monster's attack.
     * @return attack - monster's attack strength
     ***********************************************/
    public int getAttack() {
        return attack;
    }


    /***********************************************
     * Gets the reward.
     * @return reward - reward for killing monster
     ***********************************************/
    public int getReward() {
        return reward;
    }

    /**************************************
     * Gets the monster's health.
     * @return health - monster's health
     **************************************/
    public double getHealth() {
        return health;
    }

    /****************************************
     * Gets monster's rotation.
     * @return rotation
     ****************************************/
    public double getRotation() {
        return rotation;
    }

    /*****************************************
     * Checks if monster is alive.
     * @return true if monster dead
     *****************************************/
    public boolean isDead() {
        return health <= 0 || deleteOnNextFrame;
    }

    /****************************************
     * Decides if monster should be deleted.
     * @return deleteOnNextFrame
     ****************************************/
    public boolean getDeleteOnNextFrame() {
        return deleteOnNextFrame;
    }

    /*************************************
     * Sets monster's speed.
     * @param speed - movement speed
     *************************************/
    protected void setMoveSpeed(int speed) {
        moveSpeed = speed;
    }

    /************************************
     * When the monster is attacked.
     * @param damage - health points lost
     *************************************/
    public void hurt(double damage) {
        if (deleteOnNextFrame) {
            return; // monster is already dead
        }
        health -= damage;
        if (Double.compare(health, 0.0) <= 0) {
            //flag for deletion
            deleteOnNextFrame = true;
            Game.getInstance().claimBounty(reward);
        }
    }

    /*****************************************************
     * Moves the monster one Tile further along the path.
     *****************************************************/
    private void travel() {
        ArrayList<Tile> path = Game.getInstance().getMap().getPath();
        // travels the actual path starting at the tile at the first position

        pathIndex++;
        if (pathIndex >= path.size()) {
            // hurt base
            Game.getInstance().getMap().getBase().removeHealth(attack);
            // flag for deletion
            deleteOnNextFrame = true;
            return;
        }
        col = path.get(pathIndex).col;
        row = path.get(pathIndex).row;
        switch (Game.getInstance().getMap().getTile(col, row).type) {
            case Map.D:
            case Map.DR:
                rotation = Math.PI / 2;
                break;
            case Map.U:
            case Map.UR:
                rotation = 3 * Math.PI / 2;
                break;
            case Map.R:
            case Map.RU:
            case Map.RD:
                rotation = 0.0;
                break;
            default:
                // do nothing
                break;
        }
    }

    /*********************************
     * Monster attempts to travel.
     *********************************/
    public void attemptTravel() {
        framesSinceLastTravel++;
        if (framesSinceLastTravel >= moveSpeed) {
            travel();
            framesSinceLastTravel = 0;
        }
    }


}


