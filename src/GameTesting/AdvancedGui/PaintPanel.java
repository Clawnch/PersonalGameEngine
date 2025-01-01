package GameTesting.AdvancedGui;

import GameTesting.AdvancedGui.Events.Stopwatch;
import GameTesting.AdvancedGui.Input.Mouse;
import GameTesting.AdvancedGui.Interactables.MinesweeperAssets.MinesweeperPanel;
import GameTesting.AdvancedGui.Interactables.ViewPanel.TextPanel;
import GameTesting.AdvancedGui.Interactables.ViewPanel.ViewPanel;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {

    private ViewPanel overall;
    private MinesweeperPanel minesweeperPanel;
    private Mouse mouse;
    private TextPanel timerText;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        overall = new ViewPanel(500, 500,0,0);
        minesweeperPanel = new MinesweeperPanel(this.getPreferredSize().width, this.getPreferredSize().height);

        timerText = new TextPanel(0,0, 200, 20, "0");
        Stopwatch stopwatch = new Stopwatch(timerText, this);
        Thread thread = new Thread(stopwatch);

        minesweeperPanel.addStopwatch(thread);

        //ViewPanel secondPanel = new ViewPanel(500, 500,250,250);

        //X and Y still use absolute coordinates and not relative to the panel they are a part of
        //secondPanel.addButton(new Button(0, 0, 50, 50));

        overall.addButton(minesweeperPanel);
        overall.addButton(timerText);

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
