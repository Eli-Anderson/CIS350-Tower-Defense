import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***********************************************
 * Creates Side Bar for GUI.
 ***********************************************/
public class SidebarGUI extends JPanel implements ActionListener {

    /** Creates JButtons. **/
    private JButton rockTower, scissorTower, paperTower, destroyButton;

    /** creates JLabels. **/
    private JLabel roundLabel, goldLabel, healthLabel;

    /**************************************
     * Constructor to create side bar GUI.
     **************************************/
    SidebarGUI() {
        setLayout(new GridLayout(7, 1));

        BufferedImage rockTowerImage;
        BufferedImage scissorTowerImage;
        BufferedImage paperTowerImage;
        try {
            rockTowerImage = ImageIO.read(new File("resources/towers/rockTower.png"));
            paperTowerImage = ImageIO.read(new File("resources/towers/paperTower.png"));
            scissorTowerImage = ImageIO.read(new File("resources/towers/scissorTower.png"));
        } catch (IOException e) {
            System.out.println("An error occurred when attempting to load images");
            return;
        }

        rockTower = new TowerSelectButton(rockTowerImage);
        scissorTower = new TowerSelectButton(scissorTowerImage);
        paperTower = new TowerSelectButton(paperTowerImage);
        destroyButton = new JButton("Sell");
        healthLabel = new JLabel("Lives: " + Game.getInstance().getMap().getBase().getHealth());
        roundLabel = new JLabel("Round: " + RoundManager.getRound());
        goldLabel = new JLabel("Gold: " + Game.getInstance().getGold());

        rockTower.addActionListener(this);
        scissorTower.addActionListener(this);
        paperTower.addActionListener(this);
        destroyButton.addActionListener(this);

        rockTower.setToolTipText("<html><p>Rock Tower</p><br>" +
                "<p>Cost - " + RockTower.getCost() + "</p><br>" +
                "<p>Range - " + RockTower.getStaticAttackRange() + "</p></html>");

        scissorTower.setToolTipText("<html><p>Scissor Tower</p><br>" +
                "<p>Cost - " + ScissorTower.getCost() + "</p><br>" +
                "<p>Range - " + ScissorTower.getStaticAttackRange() + "</p></html>");
        paperTower.setToolTipText("<html><p>Paper Tower</p><br>" +
                "<p>Cost - " + PaperTower.getCost() + "</p><br>" +
                "<p>Range - " + PaperTower.getStaticAttackRange() + "</p></html>");

        add(rockTower);
        add(scissorTower);
        add(paperTower);
        add(destroyButton);

        add(roundLabel);
        add(healthLabel);
        add(goldLabel);

        setVisible(true);
    }

    /*******************************************
     * Checks to see if action was performed.
     * @param e - ActionEvent
     *******************************************/
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

    /************************
     * Updates Gold label.
     ************************/
    public void updateGoldLabel() {
        goldLabel.setText("Gold: " + Game.getInstance().getGold());
    }

    /*********************************
     * Updates round label.
     *********************************/
    public void updateRoundLabel() {
        roundLabel.setText("Round: " + RoundManager.getRound());
    }

    /******************************
     * Updates health label.
     ******************************/
    public void updateHealthLabel() {
        healthLabel.setText("Lives: " + Game.getInstance().getMap().getBase().getHealth());
    }

    /*********************************************
     * Creates tower select button.
     *********************************************/
    private static class TowerSelectButton extends JButton {

        /** Tower button image. **/
        private BufferedImage image;

        /***************************************
         * Constructor for Tower Select Button.
         * @param img - Tower Image
         ***************************************/
        TowerSelectButton(BufferedImage img) {
            super();
            image = img;
            setSize(img.getWidth(), img.getHeight());
        }

        /**********************************
         * Paints the tower select button.
         * @param g
         **********************************/
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
