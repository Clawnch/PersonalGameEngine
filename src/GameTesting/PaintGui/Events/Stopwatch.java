package GameTesting.PaintGui.Events;


import GameTesting.PaintGui.Interactables.ViewPanel.TextPanel;

import javax.swing.*;

public class Stopwatch implements Runnable {

    private int currentMilliseconds;
    private TextPanel timerPanel;
    private JPanel paintPanel;
    public Stopwatch(TextPanel textPanel, JPanel paintPanel) {
        currentMilliseconds = 0;
        this.timerPanel = textPanel;
        this.paintPanel = paintPanel;
    }

    @Override
    public void run() {
        resetTimer();
        long lastCheck = System.currentTimeMillis();
        while (!Thread.currentThread().isInterrupted()) {
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                currentMilliseconds++;
                timerPanel.setText(String.valueOf(currentMilliseconds));
                lastCheck = System.currentTimeMillis();
                paintPanel.repaint();
            }
        }
    }

    public void resetTimer() {
        currentMilliseconds = 0;
        timerPanel.setText(String.valueOf(currentMilliseconds));
    }


}
