package GameTesting.AdvancedGui.PongGame.Models;

public class Rectangle {

    private int width, height;
    private Point point;

    public Rectangle(Point point, int width, int height) {
        this.point = point;
        this.width = width;
        this.height = height;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isInArea(Point point) {
        int x1 = point.getX(), y1 = point.getY();
        int x2 = this.point.getX(), y2 = this.point.getY();
        boolean xComp = x1 >= x2 && x1 < x2 + width;
        boolean yComp = y1 >= y2 && y1 < y2 + height;

        return xComp && yComp;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
