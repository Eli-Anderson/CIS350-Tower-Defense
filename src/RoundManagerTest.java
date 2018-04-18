import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundManagerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void reset() {
        RoundManager.update();
        assertTrue(Game.getInstance().getMap().getMonsters().size() != 0);
        RoundManager.reset();
        assertTrue(Game.getInstance().getMap().getMonsters().size() == 0);
    }

    @Test
    public void update() {

    }
}