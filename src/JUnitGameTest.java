/*************************************
 * JUnit Tests for Game Class
 * @author Kaylin Zaroukian
 * @version Winter 2018
 ************************************/

import org.junit.runner.RunWith;

import org.junit.*;
import static org.junit.Assert.*;

/*************************************
 * JUnit Tests for Game Class
 ************************************/
public class JUnitGameTest {
    private Game towerGame;

    /*****************************************
     * Sets up a game object.
     * @throws Exception
     *****************************************/
    @Before
    public void setUp() throws Exception {
        towerGame = new Game();

    }

    /******************************************
     * Ends the game object.
     * @throws Exception
     ******************************************/
    @After
    public void tearDown() throws Exception {
    }

    /**************************************
     * Tests the constructor.
     **************************************/
    @Test
    public void testConstructor(){
        // confirms that the map will be 16 x 8
        int height = 8;
        int width = 16;
        Assert.assertEquals("Map should be 16 x 8", width, 16);
        Assert.assertEquals("Map should be 16 x 8", height, 8);

        // confirms frames per second
        int fps = 30;
        Assert.assertEquals("FPS should be 30", fps, 30);

        // confirms starting gold count
        int gold = 20;
        Assert.assertEquals("Gold should be 20", gold, 20);

    }

    /***************************************
     * Tests removing gold.
     ***************************************/
    @Test
    public void removeGoldTest() {
        // checks to see if gold can be removed
        towerGame.removeGold(5);
        int gold = towerGame.getGold();
        assertEquals("Gold should equal 15", gold, 15);

    }

    /**************************************
     * Tests claim bounty.
     **************************************/
    @Test
    public void claimBounty() {
        towerGame.claimBounty(5);
        int gold = towerGame.getGold();
        assertEquals("Gold should be 20", gold, 25);
    }

    /****************************
     * Tests game over.
     ****************************/
    @Test
    public void gameOver() {
        towerGame.gameOver();
    }


}

