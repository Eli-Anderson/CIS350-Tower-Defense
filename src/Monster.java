/********************************
 * CIS 359 Monster Class
 * Creates monsters to attack in
 * tower defense game
 * @author Kaylin Zaroukian
 * @version 1
 ********************************/

import java.awt.*;

public class Monster{
    
    /** Monster's attack **/
    private int attack;

    /** Angle **/
    private void angle;
    
    /** Monster's health **/
    private int health;

    /** Path for monster to take **/
    public int path;
    
    /** Hold Position X **/
    private double posX;
    
    /** Holds position Y **/
    private double posY;
    
    /** Attack Range **/
    private int range;
    
    /** Attack speed bigger = slower**/
    private int attackSpeed;
    
    /** Movement speed  bigger = slower**/
    private int moveSpeed;

    /** Reward for killing the monster **/
    private int reward;
    
    public Monster(int attack, int health, double posX, double posY){
        this.attack = attack;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        
    }
    
    public double getPosX() {
        return posX;
    }
    
    public void setPosX(double posX) {
        this.posX = posX;
    }
    
    public double getPosY() {
        return posY;
    }
    
    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    public int getAttack() {
        return attack;
    }
    
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void getReward(int reward) {
        return reward;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public boolean alive(){
        if(health > 0){
            return true;
        }
        else{
            reward = 20;
            return false;
        }
    }

    public int yourReward(){
        if(alive()){
            reward = 0;
        }
        else{
            reward = 20;
        }
    }

    public void hurt(int damage){
        if(health > 0){
            health -= damage;
        }
        else{
            health = 0;
        }
    }


    public void move(double time){

        // transform.position ?????
        Tile position = new Tile(posX, posY);
        int targX = posX * 128 + 64;
        int targY = posY * 128 + 64;

        distance = Math.abs(targY - posY) + Math.abs(targX - posX);
        Tile targetPos = new Tile(targX, targY);

        angle = Math.atan2(targY, targX);
        if(angle < Math.PI){
            angle += Math.PI * 2;
        }

        if(distance < time * moveSpeed){
            path += 1;
        }

        // add some way to rotate?

        else{
            // get tile position
        }



    }

    public void draw(){
        // lets us draw the image
    }
    

    
    // TODO: add function to locate monster's exact position as well as tower's exact position
    
}
