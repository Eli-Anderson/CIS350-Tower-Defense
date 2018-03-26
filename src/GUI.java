import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GUI extends JFrame implements Observer {
    private final int TILE_SIZE = 64;

    private Map map;

    private JPanel mapPanel;
    private TilePanel[][] mapArray;
    private BufferedImage monsterImage1;
    private BufferedImage towerImage1;
    private static GUI instance;
    private ButtonListener buttonListener;

    public static GUI getInstance () {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }
    private GUI() {
        setName("Tower Defense");
        try {
            monsterImage1 = ImageIO.read(new File("resources/beetle.png"));
            towerImage1 = ImageIO.read(new File("resources/tower1.png"));
        } catch (IOException e) {
            System.out.println("Error reading images");
            return;
        }


        Game.getInstance(); // initialize the Game
        map = Game.getInstance().getMap();
        setSize(map.getWidth() * TILE_SIZE, 22 + map.getHeight() * TILE_SIZE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createMapDisplay();
        setVisible(true);

        Game.getInstance().addObserver(this);
    }

    private void createMapDisplay() {
        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(map.getHeight(), map.getWidth(), 0, 0));
        mapArray = new TilePanel[map.getHeight()][map.getWidth()];


        try {
            // create the images
            BufferedImage grass = ImageIO.read(new File("resources/grass.png"));
            BufferedImage vertical = ImageIO.read(new File("resources/vertical.png"));
            BufferedImage horizontal = ImageIO.read(new File("resources/horizontal.png"));
            BufferedImage rightToDown = ImageIO.read(new File("resources/rightToDown.png"));
            BufferedImage rightToUp = ImageIO.read(new File("resources/rightToUp.png"));
            BufferedImage downToRight = ImageIO.read(new File("resources/downToRight.png"));
            BufferedImage upToRight = ImageIO.read(new File("resources/upToRight.png"));

            for (int row=0; row < map.getHeight(); row++) {
                for (int col=0; col < map.getWidth(); col++) {
                    //create a new JPanel for each Tile
                    mapArray[row][col] = new TilePanel();

                    switch (map.getTile(col, row).type) {
                        // add an image, depending on what the Tile's type is
                        case Map.R:
                            mapArray[row][col].tileImage = horizontal;
                            break;
                        case Map.U:
                        case Map.D:
                            mapArray[row][col].tileImage = vertical;
                            break;
                        case Map.UR:
                            mapArray[row][col].tileImage = upToRight;
                            break;
                        case Map.DR:
                            mapArray[row][col].tileImage = downToRight;
                            break;
                        case Map.RU:
                            mapArray[row][col].tileImage = rightToUp;
                            break;
                        case Map.RD:
                            mapArray[row][col].tileImage = rightToDown;
                            break;
                        default:
                            mapArray[row][col].tileImage = grass;
                            break;
                    }

                    mapPanel.add(mapArray[row][col]);
                }
            }
            add(mapPanel);



        } catch (IOException e) {
            System.out.println("An error occurred when loading the images");
        }
    }

    public void clearMonsterImages () {
        for (int row=0; row < mapArray.length; row++) {
            for (int col=0; col < mapArray[row].length; col++) {
                mapArray[row][col].monsterImage = null;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Monster) {
            mapArray[((Monster)arg).getRow()][((Monster)arg).getCol()].monsterImage = monsterImage1;
            repaint();
        }
    }

    public class TilePanel extends JButton {
        public BufferedImage tileImage;
        public Image monsterImage;
        public Image towerImage;
        TilePanel() {
            super();
            if (buttonListener == null)
                buttonListener = new ButtonListener();
            addActionListener(buttonListener);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g1 = (Graphics2D) g;
            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g1.drawImage(tileImage,0,0,null);

            int tileImageWidth = tileImage.getWidth(this);
            int tileImageHeight = tileImage.getHeight(this);

            if (monsterImage != null) {
                int monsterImageWidth = monsterImage.getWidth(this);
                int monsterImageHeight = monsterImage.getHeight(this);
                g1.drawImage(monsterImage, (tileImageWidth / 2) - (monsterImageWidth / 2),
                        (tileImageHeight / 2) - (monsterImageHeight / 2), this);
            }
            if (towerImage != null) {
                int towerImageWidth = towerImage.getWidth(this);
                int towerImageHeight = towerImage.getHeight(this);
                g1.drawImage(towerImage, (tileImageWidth / 2) - (towerImageWidth / 2),
                        (tileImageHeight / 2) - (towerImageHeight / 2), this);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int row=0; row < map.getHeight(); row++) {
                for (int col = 0; col < map.getWidth(); col++) {
                    if (e.getSource() == mapArray[row][col]) {

                        System.out.println("Col: " + col +", Row: " + row);
                        if (map.isBuildable(col, row)) {
                            Tower t = new RockTower(col, row);
                            map.addTower(t, col, row);
                            mapArray[row][col].towerImage = towerImage1;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GUI.getInstance();
        Game.getInstance().start();
    }
}