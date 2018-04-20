import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundManagerTest {

    @Before
    public void setUp() {
    }

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

    @Test
    public void update() {
        RoundManager.update();
        assertEquals("Round should be 1", RoundManager.getRound(), 1);

    }
}