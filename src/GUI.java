import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private Game game;
    private Map map;

    private JPanel mapPanel;
    private JPanel[][] mapArray;

    public GUI() {
        game = new Game();
        map = game.getCurrentMap();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createMapDisplay();
        setVisible(true);
        pack();
    }

    private void createMapDisplay() {
        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(map.getWidth(), map.getHeight()));
        mapArray = new JPanel[map.getHeight()][map.getWidth()];
        for (int row=0; row < map.getHeight(); row++) {
            for (int col=0; col < map.getWidth(); col++) {
                mapArray[row][col] = new JPanel();
                mapArray[row][col].add(new JLabel(""+map.getTile(col, row).type));
                mapPanel.add(mapArray[row][col]);
            }
        }
        add(mapPanel);
    }


    public static void main(String[] args) {
        new GUI();
    }
}