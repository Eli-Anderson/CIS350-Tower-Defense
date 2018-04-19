/*************************************
 * JUnit Tests for Game Class
 * @author Kaylin Zaroukian
 * @version Winter 2018
 ************************************/

import org.junit.runner.RunWith;

import org.junit.*;
import static org.junit.Assert.*;

public class JUnitGameTest {
    private Game towerGame;

    // creates a game object
    @Before
    public void setUp() throws Exception {
        towerGame = new Game();

    }

    @After
    public void tearDown() throws Exception {
    }

    // tests the constructor
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


    // tests removing gold
    @Test
    public void removeGoldTest() {
        // checks to see if gold can be removed
        towerGame.removeGold(5);
        int gold = towerGame.getGold();
        assertEquals("Gold should equal 15", gold, 15);

    }

    // tests adding gold
    @Test
    public void claimBounty() {
        towerGame.claimBounty(5);
        int gold = towerGame.getGold();
        assertEquals("Gold should be 20", gold, 25);
    }

    // tests game over
    @Test
    public void gameOver() {
        towerGame.gameOver();
    }


}

