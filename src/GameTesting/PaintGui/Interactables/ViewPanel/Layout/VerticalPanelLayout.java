package GameTesting.PaintGui.Interactables.ViewPanel.Layout;

import GameTesting.PaintGui.Interactables.ViewPanel.ViewPanel;

public class VerticalPanelLayout extends ViewPanel {

    private static final int DEFAULT_SPACING = 5;
    private int spacingValue;

    public VerticalPanelLayout(int width, int height, int x, int y) {
        this(width, height, x, y, DEFAULT_SPACING);
    }
    public VerticalPanelLayout(int width, int height, int x, int y, int spacing) {
        super(width, height, x, y);
        this.spacingValue = spacing;

    }

    public void adjustSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
