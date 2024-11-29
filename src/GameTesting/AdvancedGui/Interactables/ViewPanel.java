package GameTesting.AdvancedGui.Interactables;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewPanel {

    int width, height, x, y;
    private List<ViewPanel> panelsToUpdate;

    public ViewPanel(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        panelsToUpdate = new ArrayList<>();
        //System.out.printf("Width: %s - Height %s%n", width, height);
    }

    public void onLeftClick(int x, int y) {
        for (ViewPanel panel : panelsToUpdate) {
            if (IntractableHelper.isInClickArea(panel, x, y)) panel.onLeftClick(x, y);
        }
    }

    public void onRightClick(int x, int y) {
        System.out.println("ViewPanel right click");
        for (ViewPanel panel : panelsToUpdate) {
            if (IntractableHelper.isInClickArea(panel, x, y)) panel.onRightClick(x, y);
        }
    }

    protected void debugClick(int x, int y) {
        System.out.printf("%s registered a click at X:%s Y:%s%n", this, x, y);
    }

    public void onPaint(Graphics g) {
        for (ViewPanel b : panelsToUpdate) {
            b.onPaint(g);
        }
    }

    public void addButton(ViewPanel b) {
        panelsToUpdate.add(b);
    }

    @Override
    public String toString() {
        return String.format("ViewPanel X:%s,Y:%s, width:%s,height:%s, with %s children",
                x, y, width, height, panelsToUpdate.size());
    }
}
