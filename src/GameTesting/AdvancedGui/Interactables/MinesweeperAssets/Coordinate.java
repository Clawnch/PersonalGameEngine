package GameTesting.AdvancedGui.Interactables.MinesweeperAssets;

public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinate) {
            Coordinate other = (Coordinate) object;
            return (other.getX() == this.x) && (other.getY() == this.y);
        }

        return false;
    }

}
