package GameTesting.AdvancedGui.PongGame.Models.Particles;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.HelperClasses.MovementHelper;
import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.ActivePoint;
import GameTesting.AdvancedGui.PongGame.Models.Point;

import java.util.Objects;
import java.util.function.BiConsumer;


public class Particle extends Drawable implements GameComponent {

    public static class ParticleFactory {

        private Particle particle;

        public ParticleFactory() {
            particle = new Particle();
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

        /**
         * Provides created particle, verifies that all properties have been initialized
         * if any property has not been set, returns inactive particle
         * @return created particle or inactive particle if property not set
         */
        public Particle returnParticle() {
            if (!verifyParticle()) {
                particle = new Particle();
                particle.isActive = false;
            }
            return particle;
        }

        private boolean verifyParticle() {
            return Objects.nonNull(particle) &&
                    Objects.nonNull(particle.position) &&
                    Objects.nonNull(particle.gravityPoint) &&
                    Objects.nonNull(particle.behavior) &&
                    particle.maxUpdates != 0 &&
                    particle.width != 0 &&
                    particle.height != 0;
        }
    }


    /*
        This class is intended to be mainly a graphical effect, adding an object that has a timed behavior
        Doesn't  need pixels[], a color will suffice
        boolean active? - would allow to easily change if allowed to be updated or rendered

        How to implement behavior? Can lambda's be added to the object to determine behavior?

     */

    private BiConsumer<Particle, ActivePoint> behavior;

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
        position = MovementHelper.getPointFromDirAndSpeed(moveDir, MOVE_SPEED, position);
    }

    @Override
    public void render() {
        if (isActive) {
            RenderHelper.renderColor(this, 0x11ffDD);
        }
    }

    @Override
    public void update() {
        if (isActive) {
            behavior.accept(this, gravityPoint);
            updatesCompleted++;
            if (updatesCompleted >= maxUpdates) isActive = false;
        }
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
