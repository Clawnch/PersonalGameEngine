package GameTesting.PaintGui;

import GameTesting.PaintGui.Input.Mouse;
import GameTesting.PaintGui.Interactables.MinesweeperAssets.MinesweeperPanel;
import GameTesting.PaintGui.Interactables.ViewPanel.ViewPanel;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {

    private ViewPanel minesweeperPanel, overall;
    private Mouse mouse;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        overall = new ViewPanel(500, 500,0,0);
        minesweeperPanel = new MinesweeperPanel(this.getPreferredSize().width, this.getPreferredSize().height);

        //ViewPanel secondPanel = new ViewPanel(500, 500,250,250);

        //X and Y still use absolute coordinates and not relative to the panel they are a part of
        //secondPanel.addButton(new Button(0, 0, 50, 50));

        overall.addButton(minesweeperPanel);

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

        //System.out.println(this.getWidth() + ":" + this.getHeight());
    }


}
