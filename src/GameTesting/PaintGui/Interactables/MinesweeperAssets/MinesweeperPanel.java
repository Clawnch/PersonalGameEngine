package GameTesting.PaintGui.Interactables.MinesweeperAssets;

import GameTesting.PaintGui.Interactables.ViewPanel.TextPanel;
import GameTesting.PaintGui.Interactables.ViewPanel.ViewPanel;

import java.awt.*;

public class MinesweeperPanel extends ViewPanel {


    private MinesweeperField gameField;
    private GameStartButton resetButton;
    private TextPanel victoryText;

    public MinesweeperPanel(int width, int height) {
        super(width, height, 0, 0);

        gameField = new MinesweeperField(5, 35, 10, 10, 10);
        resetButton = new GameStartButton(5, 5, 20, 20, gameField);
        victoryText = new TextPanel(0,0, 100, 35, "Game Won!");
    }

    public void onLeftClick(int x, int y) {
        gameField.onLeftClick(x, y);
        resetButton.onLeftClick(x, y);

    }

    @Override
    public void onRightClick(int x, int y) {
        gameField.onRightClick(x, y);
    }

    public void onPaint(Graphics g) {
        gameField.onPaint(g);
        resetButton.onPaint(g);
        if (gameField.checkGameStatus() == MinesweeperField.GameStatus.gameWon) {
            victoryText.setText("Game Won!");
            victoryText.onPaint(g);
        } else if (gameField.checkGameStatus() == MinesweeperField.GameStatus.gameOver) {
            victoryText.setText("Game Over!");
            victoryText.onPaint(g);
        }
    }

}
