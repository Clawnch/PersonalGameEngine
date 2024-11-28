package GameTesting.AdvancedGui.Interactables;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewPanel {

    int width, height;
    private List<Button> panelsToUpdate;

    public ViewPanel(int width, int height) {
        this.width = width;
        this.height = height;
        panelsToUpdate = new ArrayList<>();
        System.out.printf("Width: %s - Height %s%n", width, height);
    }

    public void onClick(int x, int y) {
        for (Button panel : panelsToUpdate) {
            panel.onClick(x, y);
        }
    }

    protected void debugClick(int x, int y) {
        System.out.printf("%s registered a click at X:%s Y:%s%n", this, x, y);
    }

    public void onPaint(Graphics g) {
        for (Button b : panelsToUpdate) {
            b.onPaint(g);
        }
    }

    public void addButton(Button b) {
        panelsToUpdate.add(b);
    }
}
