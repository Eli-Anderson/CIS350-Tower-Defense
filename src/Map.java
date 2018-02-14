public class Map {
    private int width, height;
    private Tile[][] tiles;

    Map (int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[height][width];
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                tiles[y][x] = new Tile(x, y);

            }
        }
    }
    public int getWidth () {
        return this.width;
    }
    public int getHeight () {
        return this.height;
    }
    Tile getTile (int x, int y) {
        return this.tiles[y][x];
    }

    public void createPath (int col, int row) {
        createPath(col, row, null);
    }
    private void createPath (int col, int row, Tile lastTilePlaced) {
        System.out.println(this);
        if (col >= getWidth()) {
            return;
        }
        boolean canGoUp = true;
        boolean canGoDown = true;
        if (lastTilePlaced != null) {
            if (lastTilePlaced.type == 'u') {
                // last tile was going up, so we can't go down (no overwriting)
                canGoDown = false;
            } else if (lastTilePlaced.type == 'd') {
                // last tile was going down, so we can't go up
                canGoUp = false;
            }
        }

        char[] options = {'r','r','r'};
        if (row <= 0) {
            // at the top of the map
            if (canGoDown) {
                options[1] = 'd';
                options[2] = 'd';
            }
        } else {
            // not at the top
            if (row >= getHeight() - 1) {
                // at the bottom
                if (canGoUp) {
                    options[1] = 'u';
                    options[2] = 'u';
                }
            } else {
                // in the middle somewhere
                if (canGoUp) {
                    options[1] = 'u';
                }
                if (canGoDown) {
                    options[2] = 'd';
                }
            }
        }


        int r = (int) Math.floor(Math.random() * 3);

        tiles[row][col].type = options[r];
        switch (options[r]) {
            case 'u':
                createPath(col, row-1, tiles[row][col]);
                break;
            case 'r':
                createPath(col+1, row, tiles[row][col]);
                break;
            case 'd':
                createPath(col, row+1, tiles[row][col]);
                break;
        }
    }

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
        m.createPath(0,3);
        System.out.println(m);
    }
}