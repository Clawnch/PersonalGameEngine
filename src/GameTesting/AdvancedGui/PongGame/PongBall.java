package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.CollisionArea;
import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Console.Debug;
import GameTesting.AdvancedGui.PongGame.HelperClasses.MovementHelper;
import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

import java.util.Objects;

public class PongBall extends Drawable implements GameComponent {

    public enum Side {
        top,
        right,
        bottom,
        left
    }

    private int moveDir = 45;
    private int speed = 3;
    private int rotationSpeed = 3;


    public PongBall(int initX, int initY, Rectangle bounds) {
        height = 16;
        width = height;
        position = new Point(initX, initY);
    }

    @Override
    public void render() {
        RenderHelper.renderColor(this, 0xDDFFDD);
    }

    @Override
    public void update() {
        Point updatedPos = MovementHelper.getPointFromDirAndSpeed(moveDir, speed, position);
        Rectangle predictedArea = new Rectangle(updatedPos, width, height);

        CollisionArea collidingWall = Pong.getWalls().stream()
                .filter(area -> CollisionHelper.isOverlapping(predictedArea, area.getArea()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(collidingWall)) {
            position = updatedPos;
        } else {
            updateAngleOnCollision(predictedArea, collidingWall);
        }

    }

    private void updateAngleOnCollision(Rectangle predictedArea, CollisionArea collidingWall) {
        Side side = getCollisionSide(collidingWall, predictedArea);
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

    public Side getCollisionSide(CollisionArea collidingWall, Rectangle predictedArea) {
        Point origin, east, south, far;
        origin = predictedArea.getPoint();
        east = new Point(origin.getX() + predictedArea.getWidth(), origin.getY());
        south = new Point(origin.getX(), origin.getY() + predictedArea.getHeight());
        far = new Point(east.getX(), south.getY());

        boolean topL = CollisionHelper.pointIsInArea(collidingWall, origin);
        boolean topR = CollisionHelper.pointIsInArea(collidingWall, east);
        boolean botL = CollisionHelper.pointIsInArea(collidingWall, south);
        boolean botR = CollisionHelper.pointIsInArea(collidingWall, far);

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

    public Point getCenter() {
        int x = position.getX() + (width / 2);
        int  y = position.getY() + (height / 2);
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
            moveDir += rotationSpeed;
            if (moveDir >= 360) moveDir -= 360;
        } else {
            moveDir -= rotationSpeed;
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
                '}';
    }
}
