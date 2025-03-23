package GameTesting.AdvancedGui.PongGame.Models.Particles;

import GameTesting.AdvancedGui.PongGame.GravityController;
import GameTesting.AdvancedGui.PongGame.Models.ActivePoint;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.PongBall;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ParticleBehavior {

    public static BiConsumer<Particle, ActivePoint> towardsGravity = (particle, point) -> {
        Double moveDir = particle.getMoveDir();
        if (Objects.isNull(moveDir)) {
            Double dir = calculateDirectionToPoint(particle.getCenter(), point);
            particle.setMoveDir(dir);
        }
        particle.moveParticle();

    };

    private enum Direction {
        nw,
        ne,
        sw,
        se
    }
    private static Double calculateDirectionToPoint(Point p1, Point toPoint) {
        double radian = Math.atan((double)(toPoint.getY() - p1.getY())/(toPoint.getX() - p1.getX()));
        double angle = radian * PongBall.radianToDegreeRatio;
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
