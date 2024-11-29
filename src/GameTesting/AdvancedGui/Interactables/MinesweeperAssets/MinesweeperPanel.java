package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

import GameTesting.AdvancedGui.Interactables.IntractableHelper;
import GameTesting.AdvancedGui.Interactables.ViewPanel;

import java.awt.*;

public class MinesweeperPanel extends ViewPanel {

    int numOfRows, numOfColumns;

    private final int generalSpacing = 5, mineSize = 15;

    MineButton[][] buttonArray;

    public MinesweeperPanel(int width, int height, int numOfColumns, int numOfRows) {
        super(width, height, 0, 0);
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.buttonArray = new MineButton[numOfRows][numOfColumns];
        setUpField();
        setUpAdjacent();
    }

    private void setUpField() {
        int currX = 0, currY = 0;
        for (int i = 0; i < numOfRows; i++) {
            currY += generalSpacing;
            currX = generalSpacing;
            for (int j = 0; j < numOfColumns; j++) {
                boolean containsMine = (Math.random() * 10) > 7;
                currX += generalSpacing;
                MineButton mine = new MineButton(currX, currY, mineSize, mineSize, containsMine);
                buttonArray[i][j] = mine;
                currX += mineSize;
            }
            currY += mineSize;
        }

    }

    public void setUpAdjacent() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                MineButton currentMine = buttonArray[i][j];

                if (i - 1 >= 0) {
                    if (j - 1 >= 0) {
                        currentMine.addAdjacentMine(buttonArray[i-1][j-1]);
                    }
                    if (j + 1 < numOfColumns) {
                        currentMine.addAdjacentMine(buttonArray[i-1][j+1]);
                    }
                    currentMine.addAdjacentMine(buttonArray[i-1][j]);
                }

                if (i + 1 < numOfRows) {
                    if (j - 1 >= 0) {
                        currentMine.addAdjacentMine(buttonArray[i+1][j-1]);
                    }
                    if (j + 1 < numOfColumns) {
                        currentMine.addAdjacentMine(buttonArray[i+1][j+1]);
                    }
                    currentMine.addAdjacentMine(buttonArray[i+1][j]);
                }

                if (j - 1 >= 0) {
                    currentMine.addAdjacentMine(buttonArray[i][j-1]);
                }

                if (j + 1 < numOfColumns) {
                    currentMine.addAdjacentMine(buttonArray[i][j+1]);
                }
            }
        }
    }


    public void onLeftClick(int x, int y) {
        for (MineButton[] row : buttonArray) {
            for (MineButton mine : row) {
                if (IntractableHelper.isInClickArea(mine, x,y)) {
                    mine.onLeftClick(x, y);
                }
            }
        }
    }

    @Override
    public void onRightClick(int x, int y) {
        for (MineButton[] row : buttonArray) {
            for (MineButton mine : row) {
                if (IntractableHelper.isInClickArea(mine, x,y)) {
                    mine.onRightClick(x, y);
                }
            }
        }
    }

    public void onPaint(Graphics g) {
        for (MineButton[] row : buttonArray) {
            for (MineButton mine : row) {
                mine.onPaint(g);
            }
        }
    }




}
