package GameTesting.AdvancedGui;

import java.awt.*;

public class Tile {

    private int x, y;
    private int width, height;
    private Color color;

    public Tile(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColor() {
        return color.getRGB() & 0xFFFFFF;
    }

    public String toString() {
        return String.format("Tile (X:%s,Y:%s; Width:%s, Height:%s), - %s", x, y, width, height, color);
    }

}
