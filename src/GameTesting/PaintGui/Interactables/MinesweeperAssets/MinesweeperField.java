package GameTesting.PaintGui.Interactables.MinesweeperAssets;

import GameTesting.PaintGui.Interactables.ViewPanel.Interactable;
import GameTesting.PaintGui.Interactables.ViewPanel.ViewPanelHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MinesweeperField implements Interactable {

    enum GameStatus {
        gameOver,
        currentGame,
        gameWon
    }

    private int numOfMines = 10;
    private int startX, startY;
    private Random random;

    private MineButton[][] minefield;

    private int rows, cols;

    private boolean firstClick = true, gameOverClick = false;

    private List<MineButton> currentMines, nonMineTiles;
    private Thread stopwatchThread;

    public MinesweeperField(int startX, int startY, int rows, int cols, int numOfMines) {
        this.rows = rows;
        this.cols = cols;
        this.numOfMines = numOfMines;
        this.startX = startX;
        this.startY = startY;

        currentMines = new ArrayList<>();
        nonMineTiles = new ArrayList<>();
        minefield = setUpField(rows, cols);
    }

    public void onLeftClick(int x, int y) {
        if (!gameOverClick) {
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
            handleStopwatchOnGameState();
        }

        if (checkGameStatus() == GameStatus.gameOver || checkGameStatus() == GameStatus.gameWon) {
            handleGameOver();
        }
    }

    private void handleGameOver() {
        gameOverClick = true;
        if (checkGameStatus() == GameStatus.gameWon) {
            for (MineButton mine : currentMines) {
                if (mine.getState() != MineButton.buttonState.flag) {
                    mine.onRightClick(0, 0);
                }
            }
        } else {
            for (MineButton mine : currentMines) {
                mine.onLeftClick(0, 0);
            }
        }
    }

    public void onRightClick(int x, int y) {
        if (!firstClick && !gameOverClick) {
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
                currentMines.add(minefield[x][y]);
                nonMineTiles.remove(minefield[x][y]);
            }
        }
        setUpAdjacent();
        stopwatchThread.start();
    }

    public void addStopwatch(Thread stopwatchThread) {
        this.stopwatchThread = stopwatchThread;
    }

    private void handleStopwatchOnGameState() {
        if (Objects.nonNull(stopwatchThread)) {
            MinesweeperField.GameStatus gameStatus = checkGameStatus();
            if (gameStatus == MinesweeperField.GameStatus.gameWon || gameStatus == MinesweeperField.GameStatus.gameOver) {
                stopwatchThread.interrupt();
            }
        }
    }

    private MineButton[][] setUpField(int numOfRows, int numOfColumns) {
        MineButton[][] buttonArray = new MineButton[numOfRows][numOfColumns];
        int generalSpacing = 5;
        int currX, currY = startY;
        for (int i = 0; i < numOfRows; i++) {
            currY += generalSpacing;
            currX = startX + generalSpacing;
            int mineSize = 15;
            for (int j = 0; j < numOfColumns; j++) {
                currX += generalSpacing;
                MineButton mine = new MineButton(currX, currY, mineSize, mineSize, false);
                buttonArray[i][j] = mine;
                currX += mineSize;
                nonMineTiles.add(mine);
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

    public GameStatus checkGameStatus() {
        for (MineButton m : currentMines) {
            if (m.getState().equals(MineButton.buttonState.mine)) {
                return GameStatus.gameOver;
            }
        }
        return checkVictoryCondition();
    }

    private GameStatus checkVictoryCondition() {
        boolean passedCheck = true;
        for (MineButton mine : nonMineTiles) {
            if (mine.getState() != MineButton.buttonState.number) {
                return GameStatus.currentGame;
            }
        }
        return GameStatus.gameWon;
    }


    public void resetBoard() {
        stopwatchThread.interrupt();
        firstClick = true;
        gameOverClick = false;
        currentMines = new ArrayList<>();
        for (MineButton[] mineRow : minefield) {
            for (MineButton mine : mineRow) {
                mine.setContainsMine(false);
                mine.resetAdjacent();
                mine.resetState();
            }
        }
        stopwatchThread = new Thread(stopwatchThread);
    }



}
