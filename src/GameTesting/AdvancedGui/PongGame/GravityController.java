package GameTesting.AdvancedGui.PongGame;


import GameTesting.AdvancedGui.Components.Updatable;
import GameTesting.AdvancedGui.Console.Debug;
import GameTesting.AdvancedGui.Controllers.MouseInteractable;
import GameTesting.AdvancedGui.PongGame.Models.Particles.Particle;
import GameTesting.AdvancedGui.PongGame.Models.Point;

import java.util.List;

public class GravityController implements MouseInteractable, Updatable {

    private PongBall ball;
    private boolean active = false;
    private Point gravityPoint;

    public GravityController(PongBall ball) {
        this.ball = ball;
    }

    @Override
    public void onRightClick(Point point) {

    }

    @Override
    public void onLeftClick(Point point) {
        active = true;
        gravityPoint = point;
    }

    @Override
    public void onRightRelease(Point point) {

    }

    @Override
    public void onLeftRelease(Point point) {
        active = false;
    }

    @Override
    public void update() {
        if (active) {
            double degrees = getAdjustedDegrees();
            ball.adjustDirFromGravity(degrees, 0);
        }
    }

    /**
     *
     * @return
     */
    public Point getGravityPoint() {
        if (active) return gravityPoint;
        return null;
    }

    private enum Direction {
        nw,
        ne,
        sw,
        se
    }

    private double getAdjustedDegrees() {
        Point ballPosition = ball.getCenter();
        double radian = Math.atan((double)(gravityPoint.getY() - ballPosition.getY())/(gravityPoint.getX() - ballPosition.getX()));
        double angle = radian * PongBall.radianToDegreeRatio;
        double absAngle = Math.abs(angle);
        double adjustedAngle;

        Direction direction = getDirectionToGravityPoint(ballPosition);
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

    private Direction getDirectionToGravityPoint(Point ball) {
        int xDiff = gravityPoint.getX() - ball.getX();
        int yDiff = gravityPoint.getY() - ball.getY();
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
