import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarGUI extends JFrame implements ActionListener{
    private JButton rockTower, scissorTower, paperTower, destroyButton;
    public SidebarGUI() {
        setName("Tools");
        setLayout(new GridLayout(10, 1));
        setLocation(GUI.TILE_SIZE * Game.getInstance().getMap().getWidth(), 0);
        rockTower = new JButton("Rock");
        scissorTower = new JButton("Scissor");
        paperTower = new JButton("Paper");
        destroyButton = new JButton("Destroy");

        rockTower.addActionListener(this);
        scissorTower.addActionListener(this);
        paperTower.addActionListener(this);
        destroyButton.addActionListener(this);

        add(rockTower);
        add(scissorTower);
        add(paperTower);
        add(destroyButton);

        setVisible(true);
        pack();
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
}
