public class Base{

    // Base health
    private int health;

    //Base position x
    private int posX;

    //Base position y
    private int posY;

    public Base(int health, int PosX, int PosY){
        this.health = health;
        this.posX = PosX;
        this.posY = PosY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int y){
        this.posY = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public boolean alive(){
        if (health > 0) {
            return true;
        } else {
            return false;
        }
    }
}
