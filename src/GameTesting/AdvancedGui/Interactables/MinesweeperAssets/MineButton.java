package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

import GameTesting.AdvancedGui.Interactables.Button;

import java.awt.*;

public class MineButton extends Button {

    int x, y, width, height;

    public MineButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void onPaint(Graphics g) {
        drawCovered(g);
    }

    private void drawCovered(Graphics g) {
        g.setColor(new Color(190, 160, 130));
        g.fillRect(x, y, width, height);
        g.setColor(new Color(210, 190, 175));
        g.fillRect(x + 2, y + 2, width - 3, height - 3);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    private void drawNumber(Graphics g) {

    }

    private void drawMine(Graphics g) {
        
    }

    @Override
    public void onClick(int x, int y) {

    }
}
