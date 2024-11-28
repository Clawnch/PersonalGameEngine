package GameTesting.AdvancedGui.Input;

import GameTesting.AdvancedGui.Interactables.ViewPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    private ViewPanel viewPanel;
    private JPanel graphicsPanel;

    public Mouse(ViewPanel viewPanel, JPanel jPanel) {
        super();
        this.viewPanel = viewPanel;
        this.graphicsPanel = jPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        viewPanel.onClick(e.getX(), e.getY());
        graphicsPanel.repaint();
        //System.out.printf("Clicked: x:%s y:%s%n", e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
