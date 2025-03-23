package GameTesting.AdvancedGui.PongGame.Models;

public class ActivePoint extends Point{

    private boolean active;
    public ActivePoint(int x, int y) {
        super(x, y);
        active = true;
    }

    public ActivePoint(Point p) {
        this(p.getX(), p.getY());
    }

    public boolean isActive() {
        return active;
    }

    public void setInactive() {
        active = false;
    }
}
