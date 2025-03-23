package GameTesting.AdvancedGui.PongGame.Models.Particles;

import GameTesting.AdvancedGui.PongGame.Models.ActivePoint;
import GameTesting.AdvancedGui.PongGame.HelperClasses.MovementHelper;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ParticleBehavior {

    public static BiConsumer<Particle, ActivePoint> towardsGravity = (particle, point) -> {
        Double moveDir = particle.getMoveDir();
        if (Objects.isNull(moveDir)) {
            Double dir = MovementHelper.calculateDirectionToPoint(particle.getCenter(), point);
            particle.setMoveDir(dir);
        }
        particle.moveParticle();

    };

}
