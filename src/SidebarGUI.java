import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SidebarGUI extends JFrame implements ActionListener{
    private JButton rockTower, scissorTower, paperTower, destroyButton;
    private BufferedImage rockTowerImage, scissorTowerImage, paperTowerImage;
    private JLabel goldLabel;
    public SidebarGUI() {
        setName("Tools");
        setLayout(new GridLayout(5, 1));
        setSize(GUI.TILE_SIZE, 22 + GUI.TILE_SIZE * 5);
        setLocation(GUI.TILE_SIZE * Game.getInstance().getMap().getWidth(), 0);

        try {
            loadImages();
        } catch (IOException e) {
            System.out.println("An error occurred when attempting to load images");
            dispose();
            return;
        }

        rockTower = new TowerSelectButton(rockTowerImage, "Rock");
        scissorTower = new TowerSelectButton(scissorTowerImage, "Scissors");
        paperTower = new TowerSelectButton(paperTowerImage, "Paper");
        destroyButton = new JButton("Destroy");
        goldLabel = new JLabel("Gold: "+Game.getInstance().getGold());

        rockTower.addActionListener(this);
        scissorTower.addActionListener(this);
        paperTower.addActionListener(this);
        destroyButton.addActionListener(this);

        add(rockTower);
        add(scissorTower);
        add(paperTower);
        add(destroyButton);
        add(goldLabel);

        setVisible(true);
        //pack();
    }

    private void loadImages() throws IOException {
        rockTowerImage = ImageIO.read(new File("resources/rockTower.png"));
        paperTowerImage = ImageIO.read(new File("resources/paperTower.png"));
        scissorTowerImage = ImageIO.read(new File("resources/scissorTower.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rockTower) {
            GUI.getInstance().selectedTowerType = TowerType.ROCK;
            GUI.getInstance().selectedTool = GUI.ToolType.BUILD;
        }
        if (e.getSource() == scissorTower) {
            GUI.getInstance().selectedTowerType = TowerType.SCISSORS;
            GUI.getInstance().selectedTool = GUI.ToolType.BUILD;
        }
        if (e.getSource() == paperTower) {
            GUI.getInstance().selectedTowerType = TowerType.PAPER;
            GUI.getInstance().selectedTool = GUI.ToolType.BUILD;
        }
        if (e.getSource() == destroyButton) {
            GUI.getInstance().selectedTool = GUI.ToolType.DESTROY;
        }
    }
    public void updateGoldLabel() {
        goldLabel.setText("Gold: "+Game.getInstance().getGold());
    }

    private class TowerSelectButton extends JButton {
        private BufferedImage image;
        private String text;
        TowerSelectButton(BufferedImage img, String txt) {
            super();
            image = img;
            text = txt;
            setSize(img.getWidth(), img.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g1 = (Graphics2D) g;
            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g1.drawImage(image, 0, 8, null);
            g1.drawString(text, 32, 14);
            //@TODO: Figure out how to center text and position image and text nicely
        }
    }
}
