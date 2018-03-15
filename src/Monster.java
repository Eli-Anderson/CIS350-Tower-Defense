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
     * Path for monster to take
     **/
    public Tile[][] path;

    /**
     * remaing path for monster
     **/
    public Tile[][] remaining;


    /**
     * Hold Position X
     **/
    private double posX;

    /**
     * Holds position Y
     **/
    private double posY;

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
     * @param posX - x position of monster
     * @param posY - y position of monster
     *****************************************************************/
    public Monster(int attack, int health, double posX, double posY) {
        this.attack = attack;
        this.health = health;
        this.posX = posX;
        this.posY = posY;

    }

    /**************************************************
     * gets X position of monster on map
     * @return posX - x position of monster on map
     **************************************************/
    public double getPosX() {
        return posX;
    }

    /**************************************************
     * gets X position of monster on map
     * @return posX - x position of monster on map
     **************************************************/
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**************************************************
     * gets Y position of monster on map
     * @return posY - y position of monster on map
     **************************************************/
    public double getPosY() {
        return posY;
    }

    /***************************************
     * sets Y Position of monster on map
     * @param posY - y position of monster
     **************************************/
    public void setPosY(double posY) {
        this.posY = posY;
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
     * Allows the monsters to travel across the map
     ***********************************************/
    public void travel() {
        Map m = new Map(8, 8);
        ArrayList<Tile> visited = new ArrayList<Tile>();
        // gives us the path we can eventually take
        for (int y = 0; y < m.getHeight(); y++) {
            for (int x = 0; x < m.getWidth(); x++) {
                // path we need to take
                path[y][x] = m.getTile(x, y);
                // what we have not visited yet
                //remaining[x][y] = m.getTile(x,y);
            }
        }

        Tile here = path[0][0];

        // travels the actual path starting at the tile at the first position
        // will need to update to account for movement speed
        while (visited.size() < path.length) {
            for (Tile[] position : path) {
                for (Tile tile : position) {
                    // visits the tile and adds it to list of visited tiles
                    here = tile;

                    // adds tile to previous tile we have visited
                    visited.add(here);
                }
            }
        }


    }
}


