import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SidebarGUI extends JPanel implements ActionListener{
    private JButton rockTower, scissorTower, paperTower, destroyButton;
    private BufferedImage rockTowerImage, scissorTowerImage, paperTowerImage;
    private JLabel roundLabel, goldLabel, healthLabel;
    SidebarGUI() {
        setLayout(new GridLayout(7, 1));

        try {
            loadImages();
        } catch (IOException e) {
            System.out.println("An error occurred when attempting to load images");
            return;
        }

        rockTower = new TowerSelectButton(rockTowerImage);
        scissorTower = new TowerSelectButton(scissorTowerImage);
        paperTower = new TowerSelectButton(paperTowerImage);
        destroyButton = new JButton("Sell");
        healthLabel = new JLabel("Lives: "+Game.getInstance().getMap().getBase().getHealth());
        roundLabel = new JLabel("Round: "+RoundManager.getRound());
        goldLabel = new JLabel("Gold: "+Game.getInstance().getGold());

        rockTower.addActionListener(this);
        scissorTower.addActionListener(this);
        paperTower.addActionListener(this);
        destroyButton.addActionListener(this);

        rockTower.setToolTipText("Rock Tower - " + RockTower.getCost() + " gold");
        scissorTower.setToolTipText("Scissor Tower - " + ScissorTower.getCost() + " gold");
        paperTower.setToolTipText("Paper Tower - " + PaperTower.getCost() + " gold");

        add(rockTower);
        add(scissorTower);
        add(paperTower);
        add(destroyButton);

        add(roundLabel);
        add(healthLabel);
        add(goldLabel);

        setVisible(true);
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
    public void updateRoundLabel() {
        roundLabel.setText("Round: "+RoundManager.getRound());
    }
    public void updateHealthLabel() {
        healthLabel.setText("Lives: "+Game.getInstance().getMap().getBase().getHealth());
    }

    private class TowerSelectButton extends JButton {
        private BufferedImage image;
        TowerSelectButton(BufferedImage img) {
            super();
            image = img;
            setSize(img.getWidth(), img.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g1 = (Graphics2D) g;
            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            g1.drawImage(image, (getWidth() / 2) - (imageWidth / 2), (getHeight() / 2) - (imageHeight / 2), null);
        }
    }
}
