import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*******************************
 * Tests base class.
 *******************************/
public class BaseTest {

    /** Creates a base object. **/
    private Base base;

    /***************************
     * Instantiates base.
     * @throws Exception
     ***************************/
    @Before
    public void setUp() throws Exception {
        base = new Base(100, 1, 1);

    }

    /************************************
     * Tears down base.
     * @throws Exception
     ************************************/
    @After
    public void tearDown() throws Exception {
    }

    /********************************
     * Tests the constructor.
     ********************************/
    @Test
    public void constructorTest() {
        int health = 100;
        int col = 1;
        int row = 1;
        assertEquals(base.getHealth(), 100);
        assertEquals(base.getCol(), 1);
        assertEquals(base.getRow(), 1);

    }

    /***************************
     * Tests removing health.
     ***************************/
    @Test
    public void removeHealth() {
        base.removeHealth(10);
        assertEquals(base.getHealth(), 90);
    }

    /*******************************
     * Tests setting health.
     *******************************/
    @Test
    public void setHealth() {
        base.setHealth(100);
        assertEquals(base.getHealth(), 100);
    }
}
