package GameTesting.AdvancedGui;

import GameTesting.AdvancedGui.Input.Mouse;
import GameTesting.AdvancedGui.Interactables.Button;
import GameTesting.AdvancedGui.Interactables.MinesweeperAssets.MineButton;
import GameTesting.AdvancedGui.Interactables.ViewPanel;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;

    private ViewPanel viewPanel;
    private Mouse mouse;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        viewPanel = new ViewPanel(this.getPreferredSize().width, this.getPreferredSize().height);
        Button b = new MineButton(75,75,50,50);
        viewPanel.addButton(b);
        mouse = new Mouse(viewPanel, this);
        addMouseListener(mouse);
//
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                moveSquare(e.getX(), e.getY());
//            }
//        });
//
//        addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                moveSquare(e.getX(), e.getY());
//            }
//        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 250);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,250, 250);
        viewPanel.onPaint(g);
//        g.drawString("This is my custom Panel!", 10,20);
//        g.setColor(Color.RED);
//        g.fillRect(squareX,squareY,squareW,squareH);
//        g.setColor(Color.BLACK);
//        g.drawRect(squareX,squareY,squareW,squareH);
    }

    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX != x || squareY != y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        }
    }


}
