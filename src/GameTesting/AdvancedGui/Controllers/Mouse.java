package GameTesting.AdvancedGui.Controllers;

import GameTesting.AdvancedGui.PongGame.Models.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener {

    private final int LEFT_CLICK = MouseEvent.BUTTON1;
    private final int RIGHT_CLICK = MouseEvent.BUTTON3;
    private final int MIDDLE_CLICK = MouseEvent.BUTTON2;

    private List<MouseInteractable> leftClickActions, rightClickActions;

    public Mouse() {
        super();
        leftClickActions = new ArrayList<>();
        rightClickActions = new ArrayList<>();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickPoint = new Point(e.getX(), e.getY());
        int buttonClick = e.getButton();

        if (buttonClick == LEFT_CLICK) {
            for (MouseInteractable m : leftClickActions) {
                m.onLeftClick(clickPoint);
            }
        } else if (buttonClick == RIGHT_CLICK) {
            for (MouseInteractable m : rightClickActions) {
                m.onRightClick(clickPoint);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point clickPoint = new Point(e.getX(), e.getY());
        int buttonClick = e.getButton();

        if (buttonClick == LEFT_CLICK) {
            for (MouseInteractable m : leftClickActions) {
                m.onLeftRelease(clickPoint);
            }
        } else if (buttonClick == RIGHT_CLICK) {
            for (MouseInteractable m : rightClickActions) {
                m.onRightRelease(clickPoint);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void addLeftClickAction(MouseInteractable clickAction) {
        leftClickActions.add(clickAction);
    }

    public void addRightClickAction(MouseInteractable clickAction) {
        rightClickActions.add(clickAction);
    }

    private void debugPrint(MouseEvent e, String eventSource) {
        System.out.printf("Mouse Event: %s at x:%s y:%s%n", eventSource, e.getX(), e.getY());
    }
}
