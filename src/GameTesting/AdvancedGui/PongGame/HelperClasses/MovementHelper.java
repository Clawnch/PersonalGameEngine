package GameTesting.AdvancedGui.PongGame.HelperClasses;

import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.PongBall;

public class MovementHelper {


    public static final double radianToDegreeRatio = 57.2958;

    public static Point getPointFromDirAndSpeed(double moveDir, double speed, Point startPosition) {
        double distanceEW = Math.cos(moveDir / radianToDegreeRatio) * speed;
        double distanceNS = Math.sin(moveDir / radianToDegreeRatio) * speed;
        int updatedX = (int)distanceEW + startPosition.getX();
        int updatedY = (int)distanceNS + startPosition.getY();
        return new Point(updatedX, updatedY);
    }

    private enum Direction {
        nw,
        ne,
        sw,
        se
    }
    public static Double calculateDirectionToPoint(Point p1, Point toPoint) {
        double radian = Math.atan((double)(toPoint.getY() - p1.getY())/(toPoint.getX() - p1.getX()));
        double angle = radian * radianToDegreeRatio;
        double absAngle = Math.abs(angle);
        double adjustedAngle;

        Direction direction = getDirectionToPoint(p1, toPoint);
        if (direction == Direction.se) {
            adjustedAngle = absAngle;
        } else if (direction == Direction.sw) {
            adjustedAngle = 90 + (90 - absAngle);
        } else if (direction == Direction.nw) {
            adjustedAngle = 180 + absAngle;
        } else {
            adjustedAngle = 270 + (90 - absAngle);
        }

        return adjustedAngle;
    }

    private static Direction getDirectionToPoint(Point p1, Point toPoint) {
        int xDiff = toPoint.getX() - p1.getX();
        int yDiff = toPoint.getY() - p1.getY();
        boolean xPos = false, yPos = false;
        Direction dir;

        if (xDiff >= 0) {
            xPos = true;
        }
        if (yDiff >= 0) {
            yPos = true;
        }

        if (!xPos && !yPos) {
            dir = Direction.nw;
        } else if (xPos && !yPos) {
            dir = Direction.ne;
        } else if (!xPos) {
            dir = Direction.sw;
        } else {
            dir = Direction.se;
        }

        return dir;
    }
}
