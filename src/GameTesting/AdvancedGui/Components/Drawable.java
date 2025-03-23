package GameTesting.AdvancedGui.Components;

import GameTesting.AdvancedGui.PongGame.Models.Point;

public abstract class Drawable {

    protected int width, height;
    protected Point position;
    protected int[] pixels;

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }
}
