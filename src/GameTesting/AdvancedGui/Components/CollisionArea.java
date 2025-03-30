package GameTesting.AdvancedGui.Components;

import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

public class CollisionArea extends Drawable implements GameComponent{

    private boolean isRendered;
    private Rectangle area;
    public CollisionArea(Point point, int width, int height) {
        area = new Rectangle(point, width, height);
        this.width = width;
        this.height = height;
        this.position = point;
        isRendered = false;
    }

    public CollisionArea(Point point, int width, int height, boolean isRendered) {
        this(point, width, height);
        this.isRendered = isRendered;
    }

    public Rectangle getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "CollisionArea{" +
                "area=" + area +
                '}';
    }

    public void render() {
        RenderHelper.renderColor(this, 0xddaadd);
    }

    @Override
    public void update() {

    }
}
