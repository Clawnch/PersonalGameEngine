package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

import GameTesting.AdvancedGui.Interactables.Button;
import GameTesting.AdvancedGui.Interactables.IntractableHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MineButton extends Button {

    enum buttonState {
        inactive,
        mine,
        number
    }

    int x, y, width, height;
    private int numberValue;
    private boolean containsMine = false;
    private buttonState state = buttonState.inactive;

    private List<MineButton> adjacentMines;

    public MineButton(int x, int y, int width, int height, boolean containsMine) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.numberValue = 0;
        this.containsMine = containsMine;
        adjacentMines = new ArrayList<>();
    }

    @Override
    public void onPaint(Graphics g) {
        if (state == buttonState.inactive) {
            drawCovered(g);
        }
        if (state == buttonState.mine) {
            drawMine(g);
        }
        if (state == buttonState.number) {
            drawNumber(g);
        }
        drawButtonBorder(g);
    }

    private void drawCovered(Graphics g) {
        g.setColor(new Color(190, 160, 130));
        g.fillRect(x, y, width, height);
        g.setColor(new Color(210, 190, 175));
        g.fillRect(x + 2, y + 2, width - 3, height - 3);
    }

    private void drawNumber(Graphics g) {
        g.setColor(Color.black);
        g.drawString(String.valueOf(numberValue),
                x + (width / 4),
                y + (height * 3/4)
        );
    }

    private void drawMine(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
        g.setColor(Color.black);
        g.drawString("X", x + (width / 4), y + (height * 3/4));
    }

    private void drawButtonBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void onClick(int x, int y) {
        if (containsMine) {
            state = buttonState.mine;
        } else {
            state = buttonState.number;
            if (numberValue == 0) {
                onClickAdjacent();
            }
        }
    }

    private void onClickAdjacent() {
        for (MineButton button : adjacentMines) {
            if (button.getState() == buttonState.inactive) button.onClick(0,0);
        }
    }

    public boolean containsMine() {
        return containsMine;
    }

    public buttonState getState() {
        return state;
    }

    public void addAdjacentMine(MineButton mine) {
        if (!adjacentMines.contains(mine)) {
            adjacentMines.add(mine);
        }
        updateNumberValue();
    }

    private void updateNumberValue() {
        int adjacentMineCount = 0;
        for (MineButton b : adjacentMines) {
            if (b.containsMine()) adjacentMineCount++;
        }
        numberValue = adjacentMineCount;
    }
}
