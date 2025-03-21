package GameTesting.AdvancedGui.PongGame.Models.Particles;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Point;

import java.util.function.BiConsumer;

public class Particle extends Drawable implements GameComponent {

    class ParticleFactory {

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

        public ParticleFactory setBehavior(BiConsumer<Particle, Point> behavior) {
            particle.behavior = behavior;
            return this;
        }

        public ParticleFactory setDuration(int maxUpdates) {
            particle.maxUpdates = maxUpdates;
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

    BiConsumer<Particle, Point> behavior;
    Point position;

    boolean isActive = true;
    private int maxUpdates, updatesCompleted = 0;

    private Particle() {

    }

    @Override
    public void render(int[] pixels) {
        if (isActive) {
            for (int y = position.getY(); y < height + position.getY(); y++) {
                for (int x = position.getX(); x < width + position.getX(); x++) {
                    pixels[x + (y * Main.width)] = 0x11FFDD;
                }
            }
        }
    }

    @Override
    public void update() {
        if (isActive) behavior.accept(this, position);
        if (updatesCompleted >= maxUpdates) isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
