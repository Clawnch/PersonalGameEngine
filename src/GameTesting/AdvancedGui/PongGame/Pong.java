package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Console.Debug;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Particles.Particle;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pong implements GameComponent {

    private PongBackground background;
    private PongBall ball;
    private GravityController gravityController;
    private List<Particle> particleList;

    public Pong() {
        int width = Main.width;
        int height = Main.height;
        particleList = new ArrayList<>();
        background = new PongBackground(width, height);
        ball = new PongBall(width/2, height/2, new Rectangle(new Point(0, 0), width, height));
        gravityController = new GravityController(ball, particleList);
        Main.getMouse().addLeftClickAction(gravityController);
    }

    @Override
    public void render() {
        background.render();
        for (Particle p : particleList) {
            p.render();
        }
        ball.render();
    }

    @Override
    public void update() {
        gravityController.update();
        for (Particle p : particleList) {
            p.update();
        }
        ball.update();
        particleCleanup();
    }

    private void particleCleanup() {
        List<Particle> nonActive = particleList.stream()
                .filter(p -> !p.isActive())
                .collect(Collectors.toList());
        for (Particle p : nonActive) {
            particleList.remove(p);
        }
    }
}
