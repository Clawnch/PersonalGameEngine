package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

import GameTesting.AdvancedGui.Interactables.ViewPanel.ViewPanel;

import java.awt.*;

public class MinesweeperPanel extends ViewPanel {


    private MinesweeperField gameField;



    public MinesweeperPanel(int width, int height) {
        super(width, height, 0, 0);

        gameField = new MinesweeperField(10, 10, 10);

    }






    public void onLeftClick(int x, int y) {
        gameField.onLeftClick(x, y);
    }

    @Override
    public void onRightClick(int x, int y) {
        gameField.onRightClick(x, y);
    }

    public void onPaint(Graphics g) {
        gameField.onPaint(g);
    }




}
