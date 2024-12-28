package GameTesting.AdvancedGui.Interactables.ViewPanel;

import java.awt.*;

public class TextPanel extends ViewPanel {

    private String text;

    public TextPanel(int width, int height, int x, int y, String text) {
        super(width, height, x, y);
        this.text = text;
    }

    @Override
    public void onPaint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(text, x, y);
    }

    public void setText(String text) {
        this.text = text;
    }
}
