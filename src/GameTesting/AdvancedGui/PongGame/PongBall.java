package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.PongGame.HelperClasses.MovementHelper;
import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

public class PongBall extends Drawable implements GameComponent {

    public enum Side {
        top,
        right,
        bottom,
        left
    }

    private int moveDir = 45;
    private int speed = 3;

    private Rectangle bounds;

    public PongBall(int initX, int initY, Rectangle bounds) {
        height = 16;
        width = height;
        position = new Point(initX, initY);
        this.bounds = bounds;
    }

    @Override
    public void render() {
        RenderHelper.renderColor(this, 0xDDFFDD);
    }

    @Override
    public void update() {
        Point updatedPos = MovementHelper.getPointFromDirAndSpeed(moveDir, speed, position);
        Rectangle updatedRect = new Rectangle(updatedPos, width, height);

        if (!CollisionHelper.isOutsideArea(bounds, updatedRect)) {
            position = updatedPos;
        } else {
            updateAngleOnCollision(updatedRect);
        }

    }

    private void updateAngleOnCollision(Rectangle updatedRect) {
        Side side = getCollisionSide(bounds, updatedRect);
        if (side.equals(Side.bottom)) {
            if (moveDir < 90) {
                moveDir = 360 - moveDir;
            }
            else if (moveDir > 90) {
                moveDir = 180 + (180 - moveDir);
            } else {
                moveDir += 180;
            }
        } else if (side.equals(Side.right)) {
            if (moveDir == 0) moveDir += 180;
            if (moveDir > 270) {
                moveDir = 360 - moveDir + 180;
            }
            if (moveDir < 90) {
                moveDir = 90 - moveDir + 90;
            }
        } else if (side.equals(Side.left)) {
            if (moveDir > 180) {
                moveDir = 270 - moveDir + 270;
            }
            if (moveDir < 180) {
                moveDir = 90 - (moveDir - 90);
            }
            if (moveDir == 180) {
                moveDir += 180;
            }
        } else if (side.equals(Side.top)) {
            if (moveDir < 270) {
                moveDir = 180 - (moveDir - 180);
            }
            if (moveDir > 270) {
                moveDir = 360 - moveDir;
            }
            if (moveDir == 270) {
                moveDir -= 180;
            }
        }
        update();
    }

    public Side getCollisionSide(Rectangle boundary, Rectangle object) {
        Point origin, east, south, far;
        origin = object.getPoint();
        east = new Point(origin.getX() + object.getWidth(), origin.getY());
        south = new Point(origin.getX(), origin.getY() + object.getHeight());
        far = new Point(east.getX(), south.getY());

        boolean topL = !CollisionHelper.isInArea(boundary, origin);
        boolean topR = !CollisionHelper.isInArea(boundary, east);
        boolean botL = !CollisionHelper.isInArea(boundary, south);
        boolean botR = !CollisionHelper.isInArea(boundary, far);

        if (topL && topR) {
            return Side.top;
        }
        if (topR && botR) {
            return Side.right;
        }
        if (topL && botL) {
            return Side.left;
        }
        return Side.bottom;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Point getCenter() {
        int x = getX() + (width / 2);
        int  y = getY() + (height / 2);
        return new Point(x,  y);
    }

    public void adjustDirFromGravity(double directionToGravity, double distance) {
        int diffSub = 0;
        int diffAdd = 0;

        for (int add = (int)directionToGravity; !withinRange(moveDir, add, 5); add++) {
            if (add > 360) add -= 360;
            diffAdd++;
        }
        for (int sub = (int)directionToGravity; !withinRange(moveDir, sub, 5); sub--) {
            if (sub < 0) sub += 360;
            diffSub++;
        }

        if (diffSub < diffAdd) {
            moveDir += 5;
            if (moveDir >= 360) moveDir -= 360;
        } else {
            moveDir -= 5;
            if (moveDir <= 0) moveDir += 360;
        }

    }

    private boolean withinRange(int baseValue, int checkedValue, int range) {
        return Math.abs(baseValue - checkedValue) <= range;
    }

    @Override
    public String toString() {
        return "PongBall{" +
                "position=" + position +
                ", moveDir=" + moveDir +
                ", speed=" + speed +
                ", bounds=" + bounds +
                '}';
    }
}
