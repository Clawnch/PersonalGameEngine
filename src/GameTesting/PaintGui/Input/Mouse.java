package GameTesting.PaintGui.Input;

import GameTesting.PaintGui.Interactables.ViewPanel.ViewPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    private final int leftClick = MouseEvent.BUTTON1;
    private final int rightClick = MouseEvent.BUTTON3;
    private final int middleClick = MouseEvent.BUTTON2;
    private ViewPanel viewPanel;
    private JPanel graphicsPanel;

    public Mouse(ViewPanel viewPanel, JPanel jPanel) {
        super();
        this.viewPanel = viewPanel;
        this.graphicsPanel = jPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseButton = e.getButton();
        //System.out.println("Mouse button: " + mouseButton);
        if (mouseButton == leftClick) {
            viewPanel.onLeftClick(e.getX(), e.getY());
        }
        if (mouseButton == rightClick) {
            viewPanel.onRightClick(e.getX(), e.getY());
        }
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
