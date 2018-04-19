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


}
