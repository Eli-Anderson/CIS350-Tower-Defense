import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*********************************
 * Tests Monster class.
 *********************************/
public class MonsterTest {
    static Map map;

    /*******************************
     *  Sets up a map.
     *******************************/
    @BeforeClass
    public static void setUp() {
        map = Game.getInstance().getMap();
    }

    /***********************************
     * Creates a paper monster and
     * adds it to the map.
     ***********************************/
    @Before
    public void before() {
        map.addMonster(new PaperMonster(0,0));
    }

    /****************************************
     * Removes the monster from the map.
     ****************************************/
    @After
    public void after() {
        map.removeMonster(map.getMonsters().get(0));
    }

    /*****************************************************
     * Tests monster's path index.
     *****************************************************/
    @Test
    public void getPathIndex() {
        assertEquals(0, map.getMonsters().get(0).getPathIndex());
        map.getMonsters().get(0).setMoveSpeed(10);
        for (int i=0; i < 10; i++) {
            assertEquals(0, map.getMonsters().get(0).getPathIndex());
            map.getMonsters().get(0).attemptTravel();
        }
        assertEquals(1, map.getMonsters().get(0).getPathIndex());
    }

    /*******************************
     * Tests monster's rotation.
     *******************************/
    @Test
    public void getRotation() {
        Monster m = map.getMonsters().get(0);
        assertEquals(0, Double.compare(m.getRotation(), 0.0));

        m.setMoveSpeed(1);
        while (m.getPathIndex() < map.getPath().size() - 1) {
            m.attemptTravel();

            if (map.getPath().get(m.getPathIndex()).row < map.getPath().get(m.getPathIndex()-1).row) {
                // path is going upwards
                assertTrue(Math.abs(m.getRotation() - (3 * Math.PI / 2)) < 0.1);
            } else if (map.getPath().get(m.getPathIndex()).row > map.getPath().get(m.getPathIndex()-1).row) {
                // path is going downwards
                assertTrue(Math.abs(m.getRotation() - (Math.PI / 2)) < 0.1);
            } else {
                // path is horizontal
                assertTrue(Math.abs(m.getRotation() - (0.0)) < 0.1);
            }
        }
    }

    /**********************************
     * Tests if monster is dead.
     **********************************/
    @Test
    public void isDead() {
        Monster m = map.getMonsters().get(0);
        assertFalse(m.isDead());
        while (m.getHealth() > 0) {
            assertFalse(m.isDead());
            m.hurt(1.0);
        }
        assertTrue(m.isDead());
    }

    /*******************************
     * Tests the hurt function
     *******************************/
    @Test
    public void hurt() {
        Monster m = map.getMonsters().get(0);
        double lastHealth = m.getHealth();
        while (m.getHealth() > 0) {
            m.hurt(1.0);
            assertTrue(Math.abs((lastHealth - 1.0) - m.getHealth()) < 0.001);
            lastHealth = m.getHealth();
        }
    }

    /*******************************
     * Tests attempt travel method.
     *******************************/
    @Test
    public void attemptTravel() {
        Monster m = map.getMonsters().get(0);
        m.setMoveSpeed(1); // so it moves every time we call attemptTravel()
        ArrayList<Tile> path = map.getPath();
        // make sure it starts on the first tile of the path
        assertEquals(path.get(0).col, m.getCol());
        assertEquals(path.get(0).row, m.getRow());
        int baseHealth = map.getBase().getHealth();
        while (m.getPathIndex() < path.size() - 1) {
            m.attemptTravel();
            assertEquals(path.get(m.getPathIndex()).col, m.getCol());
            assertEquals(path.get(m.getPathIndex()).row, m.getRow());
        }
        m.attemptTravel();
        if (m.getPathIndex() == path.size()) {
            // we are on the last tile
            assertTrue(m.getDeleteOnNextFrame()); // is it going to be deleted?
            assertTrue(map.getBase().getHealth() < baseHealth); // hurt the base
        }
    }
}