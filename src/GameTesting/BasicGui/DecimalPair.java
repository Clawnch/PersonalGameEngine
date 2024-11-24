package GameTesting.BasicGui;

public class DecimalPair {

    private double X, Y;

    public DecimalPair(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    @Override
    public String toString() {
        return String.format("X: %s \nY: %s", X, Y);
    }
}
