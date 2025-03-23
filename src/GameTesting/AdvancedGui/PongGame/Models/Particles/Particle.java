package GameTesting.AdvancedGui.PongGame.Models.Particles;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.ActivePoint;
import GameTesting.AdvancedGui.PongGame.Models.Point;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static GameTesting.AdvancedGui.PongGame.PongBall.radianToDegreeRatio;

public class Particle extends Drawable implements GameComponent {

    public static class ParticleFactory {

        private Particle particle;

        public ParticleFactory generateParticle() {
            particle = new Particle();
            return this;
        }

        public ParticleFactory setStartingPoint(Point p) {
            particle.position = p;

            return this;
        }

        public ParticleFactory setSize(int size) {
            particle.width = size;
            particle.height = size;
            return this;
        }

        public ParticleFactory setBehavior(BiConsumer<Particle, ActivePoint> behavior) {
            particle.behavior = behavior;
            return this;
        }

        public ParticleFactory setDuration(int maxUpdates) {
            particle.maxUpdates = maxUpdates;
            return this;
        }

        public ParticleFactory setGravityPoint(ActivePoint gravityPoint) {
            particle.gravityPoint = gravityPoint;
            return this;
        }

        public Particle returnParticle() {
            return particle;
        }
    }


    /*
        This class is intended to be mainly a graphical effect, adding an object that has a timed behavior
        Doesn't  need pixels[], a color will suffice
        boolean active? - would allow to easily change if allowed to be updated or rendered

        How to implement behavior? Can lambda's be added to the object to determine behavior?

     */

    private BiConsumer<Particle, ActivePoint> behavior;
    private Point position;

    private ActivePoint gravityPoint;

    private Double moveDir;

    boolean isActive = true;
    private int maxUpdates, updatesCompleted = 0;
    private final int MOVE_SPEED = 5;

    private Particle() {

    }

    public Double getMoveDir() {
        return moveDir;
    }

    public void setMoveDir(Double moveDir) {
        if (Objects.isNull(this.moveDir)) {
            this.moveDir = moveDir;
        }
    }

    public Point getCenter() {
        return new Point(position.getX() + (width / 2), position.getY() + (height / 2));
    }

    public void moveParticle() {
        double distanceEW = Math.cos(moveDir / radianToDegreeRatio) * MOVE_SPEED;
        double distanceNS = Math.sin(moveDir / radianToDegreeRatio) * MOVE_SPEED;
        int updatedX = (int)distanceEW + position.getX();
        int updatedY = (int)distanceNS + position.getY();
        position = new Point(updatedX, updatedY);
    }

    @Override
    public void render(int[] pixels) {
        if (isActive) {
            for (int y = position.getY(); y < height + position.getY(); y++) {
                for (int x = position.getX(); x < width + position.getX(); x++) {
                    int index = x + (y * Main.width);
                    if (isOnScreen(x, y) && index > 0 && index < pixels.length) pixels[index] = 0x11FFDD;
                }
            }
        }
    }

    private boolean isOnScreen(int x, int y) {
        return x > 0 && y > 0 && x < Main.width && y < Main.width;
    }

    @Override
    public void update() {
        if (isActive) {
            behavior.accept(this, gravityPoint);
            updatesCompleted++;
        }
        if (updatesCompleted >= maxUpdates) isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", gravityPoint=" + gravityPoint +
                ", moveDir=" + moveDir +
                ", isActive=" + isActive +
                '}';
    }
}
