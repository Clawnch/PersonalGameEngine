package GameTesting.AdvancedGui.PongGame;


import GameTesting.AdvancedGui.Components.Updatable;
import GameTesting.AdvancedGui.Controllers.MouseInteractable;
import GameTesting.AdvancedGui.PongGame.Models.ActivePoint;
import GameTesting.AdvancedGui.PongGame.Models.Particles.Particle;
import GameTesting.AdvancedGui.PongGame.Models.Particles.ParticleBehavior;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.HelperClasses.MovementHelper;

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
            double degrees = MovementHelper.calculateDirectionToPoint(ball.getCenter(), gravityPoint);
            ball.adjustDirFromGravity(degrees, 0);
            for (int i = 0; i < 25; i++) {
                Particle p = new Particle.ParticleFactory()
                        .setDuration(35)
                        .setSize(2)
                        .setBehavior(ParticleBehavior.towardsGravity)
                        .setStartingPoint(generatePointInRadius(150))
                        .setGravityPoint(gravityPoint)
                        .returnParticle();
                particleList.add(p);
            }
        }
    }

    private Point generatePointInRadius(int distance) {
        Random random = new Random();
        int dist = random.nextInt(360);
        double xDist = distance * Math.cos(dist);
        double yDist = distance * Math.sin(dist);

        int x = gravityPoint.getX() + (int)xDist;
        int y = gravityPoint.getY() + (int)yDist;

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
}
