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

public class Pong extends Drawable implements GameComponent {

    private PongBackground background;
    private PongBall ball;
    private GravityController gravityController;
    private List<Particle> particleList;

    public Pong(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        particleList = new ArrayList<>();
        background = new PongBackground(width, height);
        ball = new PongBall(width/2, height/2, new Rectangle(new Point(0, 0), width, height));
        gravityController = new GravityController(ball, particleList);
        Main.getMouse().addLeftClickAction(gravityController);
    }

    @Override
    public void render(int[] pixels) {
        background.render(pixels);
        for (Particle p : particleList) {
            p.render(pixels);
        }
        ball.render(pixels);
    }

    @Override
    public void update() {
        gravityController.update();
        Debug.printMessageToConsole(this, particleList + "");
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
