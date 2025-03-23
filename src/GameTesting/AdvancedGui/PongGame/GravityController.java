package GameTesting.AdvancedGui.PongGame;


import GameTesting.AdvancedGui.Components.Updatable;
import GameTesting.AdvancedGui.Console.Debug;
import GameTesting.AdvancedGui.Controllers.MouseInteractable;
import GameTesting.AdvancedGui.PongGame.Models.ActivePoint;
import GameTesting.AdvancedGui.PongGame.Models.Particles.Particle;
import GameTesting.AdvancedGui.PongGame.Models.Particles.ParticleBehavior;
import GameTesting.AdvancedGui.PongGame.Models.Point;

import java.util.List;
import java.util.Random;

public class GravityController implements MouseInteractable, Updatable {

    private PongBall ball;
    private boolean active = false;
    private ActivePoint gravityPoint;
    private List<Particle> particleList;

    public GravityController(PongBall ball, List<Particle> particleList) {
        this.ball = ball;
        this.particleList = particleList;
    }

    @Override
    public void onRightClick(Point point) {

    }

    @Override
    public void onLeftClick(Point point) {
        active = true;
        gravityPoint = new ActivePoint(point);
    }

    @Override
    public void onRightRelease(Point point) {

    }

    @Override
    public void onLeftRelease(Point point) {
        active = false;
        gravityPoint.setInactive();
    }

    @Override
    public void update() {
        if (active) {
            double degrees = getAdjustedDegrees();
            ball.adjustDirFromGravity(degrees, 0);
            for (int i = 0; i < 5; i++) {
                Particle p = new Particle.ParticleFactory().generateParticle()
                        .setDuration(15)
                        .setSize(5)
                        .setBehavior(ParticleBehavior.towardsGravity)
                        .setStartingPoint(generatePointInRadius(150))
                        .setGravityPoint(gravityPoint)
                        .returnParticle();
                particleList.add(p);
                //Debug.printMessageToConsole(this, p.toString());
            }
        }
    }

    private Point generatePointInRadius(int distance) {
        int x = 0, y = 0;
        Random random = new Random();
        int dist = random.nextInt(distance);
        double xDist = distance * Math.cos(dist);
        double yDist = distance * Math.sin(dist);

        x = gravityPoint.getX() + (int)xDist;
        y = gravityPoint.getY() + (int)yDist;


        return new Point(x, y);
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
