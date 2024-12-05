package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

import GameTesting.AdvancedGui.Interactables.ViewPanel.Interactable;
import GameTesting.AdvancedGui.Interactables.ViewPanel.ViewPanelHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinesweeperField implements Interactable {

    private int numOfMines = 10;
    private Random random;

    private MineButton[][] minefield;

    private int rows, cols;

    private boolean firstClick = true;

    public MinesweeperField(int rows, int cols, int numOfMines) {
        this.rows = rows;
        this.cols = cols;
        this.numOfMines = numOfMines;

        minefield = setUpField(rows, cols);
    }

    public void onLeftClick(int x, int y) {
        if (firstClick) {
            setUpMinefield(x, y);
            firstClick = false;
        }
        for (MineButton[] row : minefield) {
            for (MineButton mine : row) {
                if (ViewPanelHelper.isInClickArea(mine, x, y)) {
                    mine.onLeftClick(x, y);
                }
            }
        }
    }

    public void onRightClick(int x, int y) {
        if (!firstClick) {
            for (MineButton[] row : minefield) {
                for (MineButton mine : row) {
                    if (ViewPanelHelper.isInClickArea(mine, x, y)) {
                        mine.onRightClick(x, y);
                    }
                }
            }
        }
    }

    public void onPaint(Graphics g) {
        for (MineButton[] row : minefield) {
            for (MineButton mine : row) {
                mine.onPaint(g);
            }
        }
    }


    public void setUpMinefield(int firstX, int firstY) {
        List<Coordinate> mineCoordinates = new ArrayList<>();
        random = new Random();
        Coordinate startingCoordinate = new Coordinate(firstX, firstY);
        while (mineCoordinates.size() < numOfMines) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            Coordinate mine = new Coordinate(x, y);
            if (!mine.equals(startingCoordinate) && !mineCoordinates.contains(mine)) {
                minefield[x][y].setContainsMine(true);
                mineCoordinates.add(mine);
            }
        }
        setUpAdjacent();
    }

    private MineButton[][] setUpField(int numOfRows, int numOfColumns) {
        MineButton[][] buttonArray = new MineButton[numOfRows][numOfColumns];
        int currX = 0, currY = 0;
        for (int i = 0; i < numOfRows; i++) {
            int generalSpacing = 5;
            currY += generalSpacing;
            currX = generalSpacing;
            int mineSize = 15;
            for (int j = 0; j < numOfColumns; j++) {
                currX += generalSpacing;
                MineButton mine = new MineButton(currX, currY, mineSize, mineSize, false);
                buttonArray[i][j] = mine;
                currX += mineSize;
            }
            currY += mineSize;
        }
        return buttonArray;
    }

    public void setUpAdjacent() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                MineButton currentMine = minefield[i][j];

                if (i - 1 >= 0) {
                    if (j - 1 >= 0) {
                        currentMine.addAdjacentMine(minefield[i-1][j-1]);
                    }
                    if (j + 1 < cols) {
                        currentMine.addAdjacentMine(minefield[i-1][j+1]);
                    }
                    currentMine.addAdjacentMine(minefield[i-1][j]);
                }

                if (i + 1 < rows) {
                    if (j - 1 >= 0) {
                        currentMine.addAdjacentMine(minefield[i+1][j-1]);
                    }
                    if (j + 1 < cols) {
                        currentMine.addAdjacentMine(minefield[i+1][j+1]);
                    }
                    currentMine.addAdjacentMine(minefield[i+1][j]);
                }

                if (j - 1 >= 0) {
                    currentMine.addAdjacentMine(minefield[i][j-1]);
                }

                if (j + 1 < cols) {
                    currentMine.addAdjacentMine(minefield[i][j+1]);
                }
            }
        }
    }



    public void resetBoard() {
        firstClick = true;
        for (MineButton[] mineRow : minefield) {
            for (MineButton mine : mineRow) {
                mine.setContainsMine(false);
                mine.resetAdjacent();
            }
        }
    }



}
