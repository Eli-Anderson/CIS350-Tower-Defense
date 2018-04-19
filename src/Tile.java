/******************************************
 * Creates tiles to be used in the map.
 ******************************************/
public class Tile {

    /** position of tile on map. **/
    public int col, row;

    /** tile type. **/
    public char type;

    /*******************************
     * Constructor to create tile.
     * @param col - y position
     * @param row - x position
     ******************************/
    Tile(int col, int row) {
        this.col = col;
        this.row = row;
        this.type = ' ';
    }
}
