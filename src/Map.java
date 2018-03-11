public class Map {
    private int width, height;
    private Tile[][] tiles;
    private Tower[][] towers;
    private static final char R = '\u21E2';
    private static final char U = '\u21E1';
    private static final char D = '\u21E3';
    private static final char UR = '\u21B1';
    private static final char DR = '\u21B3';
    private static final char RD = '\u21B4';
    private static final char RU = '\u2197';

    /**
     * Creates a Map object with a randomized path. Capable of holding one Tower on
     * each Tile.
     * @param width the number of columns
     * @param height the number of rows
     */
    public Map (int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                tiles[y][x] = new Tile(x, y);
            }
        }
        this.createPath(0, height / 2);
        this.towers = new Tower[this.height][this.width];
    }

    /**
     * Get the width (number of columns) of the Map
     * @return the width of the Map
     */
    public int getWidth () {
        return this.width;
    }

    /**
     * Get the height (number of rows) of the Map
     * @return the height of the Map
     */
    public int getHeight () {
        return this.height;
    }

    /**
     * Gets the tile at the given position if possible, otherwise returns null
     * @param x the column
     * @param y the row
     * @return the Tile at row y, col x
     */
    Tile getTile (int x, int y) {
        if (x < this.width && x >= 0 && y < this.height && y >= 0)
            return this.tiles[y][x];
        else
            return null;
    }

    /**
     * Creates the path, starting at position col (x), row (y) and ending
     * at the right edge of the Map. Calls its recursive counterpart
     * @param col the starting column position
     * @param row the starting row position
     */
    private void createPath (int col, int row) {
        createPath(col, row, null);
        //System.out.println(this);
        fixCorners();

    }

    /**
     * The recursive counterpart of createPath, which uses pseudo random
     * choices and logic to develop a path going from left to right
     * @param col the current column position
     * @param row the current row position
     * @param lastTilePlaced
     */
    private void createPath (int col, int row, Tile lastTilePlaced) {
        if (col >= getWidth()) {
            return;
        }
        boolean canGoUp = true;
        boolean canGoDown = true;
        if (lastTilePlaced != null) {
            if (lastTilePlaced.type == U) {
                // last tile was going up, so we can't go down (no overwriting)
                canGoDown = false;
            } else if (lastTilePlaced.type == D) {
                // last tile was going down, so we can't go up
                canGoUp = false;
            }
        }

        char[] options = {R,R,R};
        if (row <= 0) {
            // at the top of the map
            if (canGoDown) {
                options[1] = D;
                options[2] = D;
            }
        } else {
            // not at the top
            if (row >= getHeight() - 1) {
                // at the bottom
                if (canGoUp) {
                    options[1] = U;
                    options[2] = U;
                }
            } else {
                // in the middle somewhere
                if (canGoUp) {
                    options[1] = U;
                }
                if (canGoDown) {
                    options[2] = D;
                }
            }
        }


        int r = (int) Math.floor(Math.random() * 3);

        tiles[row][col].type = options[r];

        switch (tiles[row][col].type) {
            case U:
                createPath(col, row-1, tiles[row][col]);
                break;
            case R:
                createPath(col+1, row, tiles[row][col]);
                break;
            case D:
                createPath(col, row+1, tiles[row][col]);
                break;
        }
    }

    /**
     * Used to modify the path so that corners are labeled correctly, so as
     * to draw them as corners and connect the path without any breaks
     */
    private void fixCorners () {
        // start at middle
        int currentX = 0;
        int currentY = this.height / 2;
        Tile lastTile = this.tiles[currentY][currentX];

        while (currentX < this.width) {
            //System.out.println(lastTile.type);
            switch (lastTile.type) {
                case U:
                case RU:
                    currentY--;
                    if (this.tiles[currentY][currentX].type == R)
                        this.tiles[currentY][currentX].type = UR;
                    break;
                case D:
                case RD:
                    currentY++;
                    if (this.tiles[currentY][currentX].type == R)
                        this.tiles[currentY][currentX].type = DR;
                    break;
                case R:
                case UR:
                case DR:
                    currentX++;
                    if (currentX >= this.width)
                        return;

                    if (this.tiles[currentY][currentX].type == U)
                        this.tiles[currentY][currentX].type = RU;
                    else if (this.tiles[currentY][currentX].type == D)
                        this.tiles[currentY][currentX].type = RD;
                    break;
            }
            lastTile = this.tiles[currentY][currentX];
        }

    }

    /**
     * Adds a tower to the array if possible
     * @param tower the Tower to be added
     * @param x the column position
     * @param y the row position
     */
    public void addTower (Tower tower, int x, int y) {
        if (x < this.width && x >= 0 &&
            y < this.height && y >= 0) this.towers[y][x] = tower;
        //else
            // out of bounds
    }

    /**
     * Gets a tower at the given position, otherwise returns null
     * @param x the column position
     * @param y the row position
     * @return the Tower at the given position
     */
    public Tower getTower (int x, int y) {
        if (x < this.width && x >= 0 &&
            y < this.height && y >= 0) return this.towers[y][x];
        return null;
    }

    /**
     * Returns a string representation of the Map, consisting of the path's
     * Tiles' directions of travel
     * @return the String representation of the Map
     */
    public String toString () {
        StringBuilder result = new StringBuilder();
        for (int col = 0; col < tiles.length; col++) {
            for (int row = 0; row < tiles[col].length; row++) {
                result.append(tiles[col][row].type);
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Map m = new Map(8,8);
        System.out.println(m);
    }
}