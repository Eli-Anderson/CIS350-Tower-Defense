/********************************
 * CIS 359 Monster Class
 * Creates monsters to attack in
 * tower defense game
 * @author Kaylin Zaroukian
 * @version 1
 ********************************/

/* figure out how to do this
 import Tower;
 import Map; */

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
    
    // idk if this would in theory work, would have to have a different multiplier
    public int battle(Type tower, Type monster){
        if(monster == Type.Rock){
            if(tower == Rock){
                // no effect?
                health += 1;
            }
            if(tower == Paper){
                attack += 15;
            }
            
            if(tower == Scissor){
                attack -= 15;
            }
        }
        if(monster == Type.Paper){
            if(tower == Rock){
                attack += 10;
            }
            if(tower == Paper){
                // no effect?
                health += 1;
            }
            
            if(tower == Scissor){
                attack -= 25;
            }
        }
        if(monster == Type.Scissor){
            if(tower == Rock){
                attack -= 20;
            }
            if(tower == Paper){
                attack += 25;
            }
            
            if(tower == Scissor){
                // no effect?
                health += 1;
            }
        }
        return health;
    }
    
    // TODO: Make SubMonster classes?
    
    public void monsterType(Type monster){
        if(monster == Type.Rock){
            attack = 100;
            health = 1000;
            moveSpeed = 5;
            attackSpeed = 4;
            //range = 10;
        }
        if(monster == Type.Paper){
            attack = 50;
            health = 750;
            moveSpeed = 3;
            attackSpeed = 2;
            //range = 4;
        }
        if(monster == Type.Scissor){
            attack = 200;
            health = 1500;
            moveSpeed = 12;
            attackSpeed = 10;
            //range = 6;
            
        }
    }
    
    // TODO: add function to locate monster's exact position as well as tower's exact position
    
}
