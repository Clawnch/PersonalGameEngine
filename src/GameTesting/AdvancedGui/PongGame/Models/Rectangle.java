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

    public void updatePoint(Point newPoint) {
        this.point.setX(newPoint.getX());
        this.point.setY(newPoint.getY());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", point=" + point +
                '}';
    }
}
