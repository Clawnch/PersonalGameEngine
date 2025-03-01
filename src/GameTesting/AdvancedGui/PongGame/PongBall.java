package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Components.Updatable;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PongBall extends Drawable implements GameComponent {

    public enum Side {
        top,
        right,
        bottom,
        left
    }

    private Point position;

    private int moveDir = 45;
    private int speed = 3;

    private Rectangle bounds, collisionBox;

    private final double radianToDegreeRatio = 57.2958;

    public PongBall(int initX, int initY, Rectangle bounds) {
        height = 16;
        width = height;
        position = new Point(initX, initY);
        pixels = new int[height * width];
        this.bounds = bounds;
        this.collisionBox = new Rectangle(position, width, height);

        Arrays.fill(pixels, 0xDDFFDD);
    }

    @Override
    public void update() {

        double distanceEW = Math.cos(moveDir / radianToDegreeRatio) * speed;
        double distanceNS = Math.sin(moveDir / radianToDegreeRatio) * speed;
        int updatedX = (int)distanceEW + position.getX();
        int updatedY = (int)distanceNS + position.getY();
        Point updatedPos = new Point(updatedX, updatedY);
        Rectangle updatedRect = new Rectangle(updatedPos, width, height);

        if (!CollisionHelper.isOutsideArea(bounds, updatedRect)) {
            position = updatedPos;
        } else {
            updateAngle(updatedRect);
        }

    }

    private void updateAngle(Rectangle updatedRect) {
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

    /**
     * Helper method to change movement angle fixed to options of 45 degrees
     * @param updatedRect Used to determine side of collision
     */
    private void updateAngleFixed45(Rectangle updatedRect) {
        Side side = getCollisionSide(bounds, updatedRect);
        if (side.equals(Side.bottom)) {
            if (moveDir == 45) moveDir = 315;
            if (moveDir == 135) moveDir = 225;
        } else if (side.equals(Side.right)) {
            if (moveDir == 45) moveDir = 135;
            if (moveDir == 315) moveDir = 225;
        } else if (side.equals(Side.left)) {
            if (moveDir == 225) moveDir = 315;
            if (moveDir == 135) moveDir = 45;
        } else if (side.equals(Side.top)) {
            if (moveDir == 225) moveDir = 135;
            if (moveDir == 315) moveDir = 45;
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

    @Override
    public void render(int[] pixels) {
        for (int y = this.getY(); y < height + this.getY(); y++) {
            for (int x = this.getX(); x < width + this.getX(); x++) {
                pixels[x + (y * Main.width)] = 0xDDFFDD;
            }
        }
    }
}
