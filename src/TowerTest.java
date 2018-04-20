import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TowerTest {
    static Map map;

    @BeforeClass
    public static void setUp() {
        map = Game.getInstance().getMap();
    }

    @Before
    public void before() {
        map.addTower(new RockTower(0, 0), 0, 0);
    }

    @After
    public void after() {
        map.destroyTower(0,0);
    }

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

    @Test
    public void attemptAttack(){
        ScissorMonster s = new ScissorMonster(0, 0);
        map.addMonster(s);
        ArrayList<Monster> monsters = map.getMonsters();
        Tower t = map.getTower(0,0);
        t.attemptAttack(monsters);
        assertEquals("Monster health should be 7.0", s.getHealth(), 7.0, 0);
    }

    @Test
    public void testRotation() {
        Tower t = map.getTower(0, 0);
        assertEquals("Rotation should be 0", t.getRotation(), 0, 0);
    }

    @Test
    public void testType() {
        Tower t = map.getTower(0, 0);
        System.out.println(t.getType());
        assertEquals("Should be a ROCK tower", t.getType(), TowerType.ROCK);
    }

    @Test
    public void checkPos() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 0", t.getCol(), 0);
        assertEquals("Should be 0", t.getRow(), 0);
    }

    @Test
    public void attackModifier() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 1.0", t.getAttackMultiplier(t.getType()), 1.0, 0);

    }

    @Test
    public void testAttackRange() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 2", t.getAttackRange(), 2);
    }

    @Test
    public void towerCost() {
        Tower t = map.getTower(0, 0);
        assertEquals("Should be 20", t.getCost(), 0);
    }


}
