import org.junit.*;

import static org.junit.Assert.*;

/******************************
 * Test for map class.
 ******************************/
public class MapTest {
    static Map map;
    static int WIDTH = 8;
    static int HEIGHT = 8;

    /***************************
     * Sets up a map object.
     ***************************/
    @BeforeClass
    public static void setUp() {
        map = new Map(WIDTH, HEIGHT);
    }

    /***********************************
     * Removes towers and monsters from
     * the map.
     ***********************************/
    @After
    public void tearDown() {
        while (map.getTowers().size() > 0)
            map.destroyTower(map.getTowers().get(0).col, map.getTowers().get(0).row);
        while (map.getMonsters().size() > 0)
            map.removeMonster(map.getMonsters().get(0));
    }

    /*****************************
     * Tests if valid path exists.
     *****************************/
    @Test
    public void checkPathIsValid() {
        assertEquals(0, map.getPath().get(0).col);
        assertEquals(WIDTH-1, map.getPath().get(map.getPath().size()-1).col);

        Tile lastTile = map.getPath().get(0);
        for (Tile t : map.getPath()) {
            if (t == lastTile) continue;
            // make sure the distance between each path Tile is exactly 1
            assertEquals(1, Math.abs(t.col - lastTile.col) + Math.abs(t.row - lastTile.row));
            lastTile = t;
        }
    }

    /************************************
     * Tests adding a tower to the map.
     ************************************/
    @Test
    public void addTower() {
        Tower p = new PaperTower(0,0);
        Tower r = new RockTower(WIDTH-1,HEIGHT-1);
        map.addTower(p, 0, 0);
        map.addTower(r, WIDTH-1, HEIGHT-1);
        assertEquals(p, map.getTower(0,0));
        assertEquals(r, map.getTower(WIDTH-1,HEIGHT-1));

        map.addTower(p, WIDTH-1, HEIGHT-1);
        assertEquals(r, map.getTower(WIDTH-1,HEIGHT-1));
    }

    /*********************************
     * Tests removing tower from map.
     *********************************/
    @Test
    public void destroyTower() {
        int row = 0;
        if (!map.isBuildable(0,0)) {
            row = HEIGHT-1; // makes sure it is buildable to begin with
        }
        Tower p = new PaperTower(0, row);
        map.addTower(p, 0, row);

        map.destroyTower(0,0);

        assertEquals(null, map.getTower(0,row));
        //assertEquals(null, map.getTower(WIDTH-1, row));
    }

    /*****************************************
     * Tests if location on map is buildable.
     *****************************************/
    @Test
    public void isBuildable() {
        System.out.println(map.getTowers().size());
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                boolean isOnPath = false;
                for (Tile t : map.getPath()) {
                    if (t.col == col && t.row == row) {
                        isOnPath = true;
                        break;
                    }
                }
                if (!isOnPath)
                    assertTrue(map.isBuildable(col, row));
                else
                    assertFalse(map.isBuildable(col, row));
            }
        }
    }

    /******************************************
     * Tests adding monster to the map.
     ******************************************/
    @Test
    public void addMonster() {
        assertEquals(0, map.getMonsters().size());
        map.addMonster(new PaperMonster(0,0));
        assertEquals(1, map.getMonsters().size());
        map.addMonster(new PaperMonster(0,0));
        assertEquals(2, map.getMonsters().size());
    }

    /*************************************************
     * Tests removing a monster from the map.
     *************************************************/
    @Test
    public void removeMonster() {
        assertEquals(0, map.getMonsters().size());
        map.addMonster(new PaperMonster(0,0));
        map.addMonster(new PaperMonster(0,0));
        map.addMonster(new PaperMonster(0,0));

        assertEquals(3, map.getMonsters().size());
        map.removeMonster(map.getMonsters().get(0));
        assertEquals(2, map.getMonsters().size());
        map.removeMonster(map.getMonsters().get(0));
        assertEquals(1, map.getMonsters().size());
        map.removeMonster(map.getMonsters().get(0));
        assertEquals(0, map.getMonsters().size());
    }
}