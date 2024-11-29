package GameTesting.AdvancedGui;

import GameTesting.AdvancedGui.Input.Mouse;
import GameTesting.AdvancedGui.Interactables.Button;
import GameTesting.AdvancedGui.Interactables.MinesweeperAssets.MinesweeperPanel;
import GameTesting.AdvancedGui.Interactables.ViewPanel;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;

    private ViewPanel minesweeperPanel, overall;
    private Mouse mouse;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        overall = new ViewPanel(500, 500,0,0);
        minesweeperPanel = new MinesweeperPanel(this.getPreferredSize().width, this.getPreferredSize().height, 10, 10);

        //ViewPanel secondPanel = new ViewPanel(500, 500,250,250);

        //X and Y still use absolute coordinates and not relative to the panel they are a part of
        //secondPanel.addButton(new Button(0, 0, 50, 50));

        overall.addButton(minesweeperPanel);
        //overall.addButton(secondPanel);

        System.out.println(overall);
        //System.out.println(secondPanel);

        mouse = new Mouse(overall, this);
        addMouseListener(mouse);
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,250, 250);
        overall.onPaint(g);
    }


}
