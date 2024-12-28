package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

import GameTesting.AdvancedGui.Interactables.ViewPanel.Button;
import GameTesting.AdvancedGui.Interactables.ViewPanel.Interactable;
import GameTesting.AdvancedGui.Interactables.ViewPanel.ViewPanelHelper;

public class GameStartButton extends Button implements Interactable {

    private MinesweeperField field;

    public GameStartButton(int x, int y, int width, int height, MinesweeperField minesweeperField) {
        super(x, y, width, height);
        this.field = minesweeperField;
    }


    @Override
    public void onLeftClick(int x, int y) {
        if (ViewPanelHelper.isInClickArea(this, x, y)) {
            field.resetBoard();
        }
    }

    @Override
    public void onRightClick(int x, int y) {
        //Ignore right click
        super.onRightClick(x, y);
    }


}
