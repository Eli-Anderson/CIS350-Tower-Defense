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
        if (this.health <= 0) {
            Game.getInstance().gameOver();
        }
    }

    public void setHealth(int h) {
        this.health = h;
    }
}
