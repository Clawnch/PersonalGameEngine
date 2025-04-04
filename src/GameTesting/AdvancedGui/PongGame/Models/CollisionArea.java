package GameTesting.AdvancedGui.PongGame.Models;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.PongGame.CollisionHelper;
import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

public class CollisionArea extends Drawable implements GameComponent {

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
        if (isRendered) {
            RenderHelper.renderColor(this, 0xddaadd);
        }
    }

    @Override
    public void update() {

    }

    public Point getCollisionPoint(Rectangle area) {
        Point topLeft, topRight, botLeft, botRight;

        topLeft = position;
        topRight = new Point(position.getX() + width, position.getY());
        botLeft = new Point(position.getX(), position.getY() + height);
        botRight = new Point(position.getX() + width, position.getY() + height);

        if (CollisionHelper.pointIsInArea(area, topLeft)) {
            return topLeft;
        }
        if (CollisionHelper.pointIsInArea(area, topRight)) {
            return topRight;
        }
        if (CollisionHelper.pointIsInArea(area, botRight)) {
            return botRight;
        }
        if (CollisionHelper.pointIsInArea(area, botLeft)) {
            return botLeft;
        }

        return null;
    }
}
