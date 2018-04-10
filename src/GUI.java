import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GUI extends JFrame implements Observer {
    public static final int TILE_SIZE = 64;
    private SidebarGUI sidebar;
    private Map map;

    private TileButton[][] mapArray;
    private BufferedImage monsterImage1;
    public BufferedImage rockTowerImage, scissorTowerImage, paperTowerImage,
            paperTowerImage_large, rockTowerImage_large, scissorTowerImage_large;
    private static GUI instance;
    private ButtonListener buttonListener;

    public enum ToolType {
        BUILD, DESTROY, SELECT
    }

    public ToolType selectedTool = ToolType.SELECT;
    public TowerType selectedTowerType = TowerType.ROCK;

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    private GUI() {
        setName("Tower Defense");
        try {
            monsterImage1 = ImageIO.read(new File("resources/beetle.png"));
            rockTowerImage = ImageIO.read(new File("resources/rockTower.png"));
            paperTowerImage = ImageIO.read(new File("resources/paperTower.png"));
            scissorTowerImage = ImageIO.read(new File("resources/scissorTower.png"));

            paperTowerImage_large = ImageIO.read(new File("resources/paperTowerLarge.png"));
            rockTowerImage_large = ImageIO.read(new File("resources/rockTowerLarge.png"));
            scissorTowerImage_large = ImageIO.read(new File("resources/scissorTowerLarge.png"));
        } catch (IOException e) {
            System.out.println("Error reading images");
            return;
        }

        setLayout(new BorderLayout());
        Game.getInstance(); // initialize the Game
        map = Game.getInstance().getMap();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createMapPanel(), BorderLayout.CENTER);
        setVisible(true);

        Game.getInstance().addObserver(this);
        sidebar = new SidebarGUI();
        add(sidebar, BorderLayout.EAST);
        setSize(sidebar.getWidth() + map.getWidth() * TILE_SIZE, 22 + map.getHeight() * TILE_SIZE);
    }

    private JPanel createMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(map.getHeight(), map.getWidth(), 0, 0));
        mapArray = new TileButton[map.getHeight()][map.getWidth()];


        try {
            // create the images
            BufferedImage grass = ImageIO.read(new File("resources/grass.png"));
            BufferedImage vertical = ImageIO.read(new File("resources/vertical.png"));
            BufferedImage horizontal = ImageIO.read(new File("resources/horizontal.png"));
            BufferedImage rightToDown = ImageIO.read(new File("resources/rightToDown.png"));
            BufferedImage rightToUp = ImageIO.read(new File("resources/rightToUp.png"));
            BufferedImage downToRight = ImageIO.read(new File("resources/downToRight.png"));
            BufferedImage upToRight = ImageIO.read(new File("resources/upToRight.png"));

            for (int row = 0; row < map.getHeight(); row++) {
                for (int col = 0; col < map.getWidth(); col++) {
                    //create a new JPanel for each Tile
                    mapArray[row][col] = new TileButton();

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


        } catch (IOException e) {
            System.out.println("An error occurred when loading the images");
        }
        return mapPanel;
    }

    public void clearMonsterImages() {
        for (int row = 0; row < mapArray.length; row++) {
            for (int col = 0; col < mapArray[row].length; col++) {
                mapArray[row][col].monsterImage = null;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        sidebar.updateGoldLabel();
        sidebar.updateRoundLabel();
        sidebar.updateHealthLabel();

        for (Monster m : map.getMonsters()) {
            int col = m.getCol();
            int row = m.getRow();
            mapArray[row][col].rotation = m.getRotation();
            switch (m.getType()) {
                case PAPER:
                    mapArray[row][col].monsterImage = monsterImage1;
                    break;
                case ROCK:
                    mapArray[row][col].monsterImage = monsterImage1;
                    break;
                case SCISSORS:
                    mapArray[row][col].monsterImage = monsterImage1;
                    break;
            }
        }

        for (Tower t : map.getTowers()) {
            int col = t.getCol();
            int row = t.getRow();
            mapArray[row][col].rotation = t.getRotation();
            if (t.getFramesSinceLastAttack() <= 1) {
                switch(t.getType()) {
                    case PAPER:
                        mapArray[row][col].towerImage = paperTowerImage_large;
                        break;
                    case ROCK:
                        mapArray[row][col].towerImage = rockTowerImage_large;
                        break;
                    case SCISSORS:
                        mapArray[row][col].towerImage = scissorTowerImage_large;
                        break;
                }
            } else {
                switch(t.getType()) {
                    case PAPER:
                        mapArray[row][col].towerImage = paperTowerImage;
                        break;
                    case ROCK:
                        mapArray[row][col].towerImage = rockTowerImage;
                        break;
                    case SCISSORS:
                        mapArray[row][col].towerImage = scissorTowerImage;
                        break;
                }
            }
        }

        repaint();
    }

    public class TileButton extends JButton {
        BufferedImage tileImage;
        Image monsterImage;
        Image towerImage;
        double rotation;

        TileButton() {
            super();
            setSize(GUI.TILE_SIZE, GUI.TILE_SIZE);
            if (buttonListener == null)
                buttonListener = new ButtonListener();
            addActionListener(buttonListener);
        }

        @Override
        public void paintComponent(Graphics g) {
            //super.paintComponent(g);
            Graphics2D g1 = (Graphics2D) g;
            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g1.drawImage(tileImage, 0, 0, getWidth(), getHeight(), null);

            int tileImageWidth = tileImage.getWidth(this);
            int tileImageHeight = tileImage.getHeight(this);
            g1.rotate(rotation, getWidth()/2, getHeight()/2);

            double widthPercent = (double) getWidth() / (double) tileImageWidth;
            double heightPercent = (double) getHeight() / (double) tileImageHeight;
            if (monsterImage != null) {
                int monsterImageWidth = (int) (monsterImage.getWidth(this) * widthPercent);
                int monsterImageHeight = (int) (monsterImage.getHeight(this) * heightPercent);
                //@TODO: Null pointer here?
                g1.drawImage(monsterImage, (getWidth() / 2) - (monsterImageWidth / 2),
                        (getHeight() / 2) - (monsterImageHeight / 2),
                        monsterImageWidth,
                        monsterImageHeight, this);
            }
            if (towerImage != null) {
                int towerImageWidth = (int) (towerImage.getWidth(this) * widthPercent);
                int towerImageHeight = (int) (towerImage.getHeight(this) * widthPercent);
                g1.drawImage(towerImage, (getWidth() / 2) - (towerImageWidth / 2),
                        (getHeight() / 2) - (towerImageHeight / 2), towerImageWidth, towerImageHeight, this);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int row = 0; row < map.getHeight(); row++) {
                for (int col = 0; col < map.getWidth(); col++) {
                    if (e.getSource() == mapArray[row][col]) {

                        if (selectedTool == ToolType.BUILD) {
                            if (map.isBuildable(col, row)) {
                                Tower t;
                                switch (selectedTowerType) {
                                    case ROCK:
                                        t = new RockTower(col, row);
                                        if (t.getCost() <= Game.getInstance().getGold()) {
                                            Game.getInstance().removeGold(t.getCost());
                                            map.addTower(t, col, row);
                                            mapArray[row][col].towerImage = rockTowerImage;
                                        }
                                        break;
                                    case PAPER:
                                        t = new PaperTower(col, row);
                                        if (t.getCost() <= Game.getInstance().getGold()) {
                                            Game.getInstance().removeGold(t.getCost());
                                            map.addTower(t, col, row);
                                            mapArray[row][col].towerImage = paperTowerImage;
                                        }
                                        break;
                                    case SCISSORS:
                                        t = new ScissorTower(col, row);
                                        if (t.getCost() <= Game.getInstance().getGold()) {
                                            Game.getInstance().removeGold(t.getCost());
                                            map.addTower(t, col, row);
                                            mapArray[row][col].towerImage = scissorTowerImage;
                                        }
                                        break;
                                }
                            }
                        } else if (selectedTool == ToolType.DESTROY){
                            // is in DESTROY mode
                            if (map.getTower(col, row) != null) {
                                // a tower exists here, so destroy it
                                map.destroyTower(col, row);
                                mapArray[row][col].towerImage = null;
                            }
                        }
                    }
                }
            }
        }
    }

    public static class GameOverDialog extends JDialog implements ActionListener {
        JButton quitButton, newGameButton;

        public GameOverDialog() {
            setTitle("Game Over!");
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    Game.getInstance().reset();
                    dispose();
                }
            });
            setLayout(new FlowLayout());
            /* create the various elements */

            quitButton = new JButton("Quit");
            quitButton.addActionListener(this);

            newGameButton = new JButton("New Game");
            newGameButton.addActionListener(this);

            /* add the elements to the panel */
            add(BorderLayout.CENTER, new JLabel("You lost on round " + Game.getInstance().getCurrentRound() + "!"));

            add(BorderLayout.SOUTH, quitButton);
            add(BorderLayout.SOUTH, newGameButton);

            setVisible(true);
            pack();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newGameButton) {
                Game.getInstance().reset();
            } else if (e.getSource() == quitButton) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        GUI.getInstance();
        Game.getInstance().start();
    }
}