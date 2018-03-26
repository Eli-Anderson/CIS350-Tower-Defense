public class Base{

    // Base health
    private int health;

    //Base position x
    private int col;

    //Base position y
    private int row;

    public Base(int health, int col, int row){
        this.health = health;
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getHealth() {
        return health;
    }

    public void removeHealth(int dmg) {
        this.health -=  dmg;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public boolean isAlive(){
        return health > 0;
    }
}
