/********************************
 * CIS 359 Monster Class
 * Creates monsters to attack in
 * tower defense game
 * @author Kaylin Zaroukian
 * @version 1
 ********************************/


public class Monster {
    
    /** Monster's attack **/
    private int attack;
    
    /** Monster's health **/
    private int health;
    
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
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public int alive(){
        if(health > 0){
            return 1;
        }
        else{
            return -1;
        }
    }
    
    public int reward(){
        int reward = 0;
        // add something to increment reward?
        return reward;
    }
    

    
    // TODO: add function to locate monster's exact position as well as tower's exact position
    
}
