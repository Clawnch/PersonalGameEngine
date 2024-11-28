package GameTesting.AdvancedGui.Interactables;

import java.awt.*;

public class Button extends ViewPanel {
    int x, y;
    private boolean isClicked = false;
    private final Color green, red;

    public Button(int x, int y, int width, int height) {
        super(width, height);
        this.x = x;
        this.y = y;
        green = new Color(75, 230, 100);
        red = new Color(230, 75, 100);
        //System.out.println("Instantiated: " + this);
    }

    boolean isClickInArea(int x, int y) {
        boolean betweenWidth = x >= this.x && x <= this.x + this.width;
        boolean betweenHeight = y >= this.y && y <= this.y + this.height;
//        System.out.printf("Click check! In Width:%s In Height:%s%n",
//                betweenWidth, betweenHeight);
        return betweenWidth && betweenHeight;
    }

    public void onPaint(Graphics g){
        g.setColor(isClicked ? green : red);
        g.fillRect(x, y ,width, height);
        g.setColor(isClicked ? red : green);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void onClick(int x, int y) {

        if (isClickInArea(x, y)) {
            isClicked = !isClicked;
            //debugClick(x, y);
        }
    }

    @Override
    public String toString() {
        return String.format("Button (X:%s,Y:%s Width:%s,Height:%s)%n",
                this.x, this.y, this.width, this.height);
    }
}
