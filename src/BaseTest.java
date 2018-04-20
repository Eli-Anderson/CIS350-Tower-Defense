import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BaseTest {
    private Base base;

    @Before
    public void setUp() throws Exception {
        base = new Base(100, 1, 1);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructorTest() {
        int health = 100;
        int col = 1;
        int row = 1;
        assertEquals(base.getHealth(), 100);
        assertEquals(base.getCol(), 1);
        assertEquals(base.getRow(), 1);

    }

    @Test
    public void removeHealth() {
        base.removeHealth(10);
        assertEquals(base.getHealth(), 90);
    }

    @Test
    public void setHealth() {
        base.setHealth(100);
        assertEquals(base.getHealth(), 100);
    }
}
