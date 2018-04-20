import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*****************************
 * Test for tower class.
 *****************************/
public class TowerTest {
    /** Creates a map object. **/
    static Map map;

    /******************************
     * Sets up a map instance.
     ******************************/
    @BeforeClass
    public static void setUp() {
        map = Game.getInstance().getMap();
    }

    /************************************
     * Adds towers to the map.
     ************************************/
    @Before
    public void before() {
        map.addTower(new RockTower(0, 0), 0, 0);
        map.addTower(new ScissorTower(2,2), 2, 2);
        map.addTower(new PaperTower(3, 3), 3, 3);
    }

    /************************************
     * Removes towers from the map.
     ************************************/
    @After
    public void after() {
        map.destroyTower(0,0);
        map.destroyTower(2,2);
        map.destroyTower(3,3);
    }

    /**************************************
     * Tests the get target method
     **************************************/
    @Test
    public void testTarget(){
        ScissorMonster s = new ScissorMonster(0,0);
        map.addMonster(s);
        ArrayList<Monster> monsters = map.getMonsters();
        Tower t = map.getTower(0,0);
        map.removeMonster(s);
        t.getTarget(monsters);
        assertEquals("Target should be null",t.getTarget(monsters), null);

    }

    /********************************
     * Tests attempt attack method.
     ********************************/
    @Test
    public void attemptAttack(){
        ScissorMonster s = new ScissorMonster(0, 0);
        map.addMonster(s);
        ArrayList<Monster> monsters = map.getMonsters();
        Tower t = map.getTower(0,0);
        t.attemptAttack(monsters);
        assertEquals("Monster health should be 7.0", s.getHealth(), 7.0, 0);
    }

    /****************************
     * Tests rotation.
     ****************************/
    @Test
    public void testRotation() {
        Tower t = map.getTower(0, 0);
        assertEquals("Rotation should be 0", t.getRotation(), 0, 0);
    }

    /****************************
     * Tests get type method.
     ****************************/
    @Test
    public void testType() {
        Tower t = map.getTower(0, 0);
        Tower p = map.getTower(3,3);
        Tower s = map.getTower(2,2);
        assertEquals("Should be a ROCK tower", t.getType(), TowerType.ROCK);
        assertEquals("Should be a scissor tower", s.getType(), TowerType.SCISSORS);
        assertEquals("Should be a paper tower", p.getType(), TowerType.PAPER);
    }

    /**************************
     * Tests tower position.
     **************************/
    @Test
    public void checkPos() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 0", t.getCol(), 0);
        assertEquals("Should be 0", t.getRow(), 0);
    }

    /********************************
     * Tests attack modifier.
     ********************************/
    @Test
    public void attackModifier() {
        Tower t = map.getTower(0, 0);
        Tower s = map.getTower(2,2);
        Tower p = map.getTower(3,3);
        System.out.println(s.getAttackMultiplier(s.getType()));
        System.out.println(p.getAttackMultiplier(p.getType()));
        assertEquals("Should be 1.0", t.getAttackMultiplier(t.getType()), 1.0, 0);
        assertEquals("Should be 1.0", s.getAttackMultiplier(s.getType()), 1.0, 0);
        assertEquals("Should be 1.0", p.getAttackMultiplier(p.getType()), 1.0, 0);

    }

    /***************************
     * Tests attack range.
     ***************************/
    @Test
    public void testAttackRange() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 2", t.getAttackRange(), 2);
    }

    /*************************
     * Tests tower cost.
     *************************/
    @Test
    public void towerCost() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 0", t.getCost(), 0);
    }


}
