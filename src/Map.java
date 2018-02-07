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

    Tile getTile (int x, int y) {
        return this.tiles[y][x];
    }

    void createPath () {
        
    }

    int getWidth () {
        return this.width;
    }
    int getHeight () {
        return this.height;
    }
}