import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**********************************
 * Tests RoundManager class.
 **********************************/
public class RoundManagerTest {

    /*******************************
     * Sets up tests.
     *******************************/
    @Before
    public void setUp() {
    }

    /********************************
     * Tests reset method.
     ********************************/
    @Test
    public void reset() {
        RoundManager.update();
        assertTrue(Game.getInstance().getMap().getMonsters().size() != 0);
        RoundManager.reset();
        Monster s = new RockMonster(0, 0);
        for(Monster m : Game.getInstance().getMap().getMonsters()){
             s = m;
        }
        Game.getInstance().getMap().removeMonster(s);

        assertTrue(Game.getInstance().getMap().getMonsters().size() == 0);
    }

    /**********************************
     * Tests update method.
     **********************************/
    @Test
    public void update() {
        RoundManager.update();
        assertEquals("Round should be 1", RoundManager.getRound(), 1);

    }
}