package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.CollisionArea;
import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Components.Renderable;
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

    private static List<CollisionArea> walls;
    private List<Renderable> renderObjects;
    private int width = Main.width, height = Main.height;

    public Pong() {
        particleList = new ArrayList<>();
        background = new PongBackground(width, height);
        renderObjects = new ArrayList<>();
        createCollisionBorders();
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
        for (Renderable r : renderObjects) {
            r.render();
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

    private void createCollisionBorders() {
        walls = new ArrayList<>();
        //Order Left, Top, Right, Bottom
        walls.add(new CollisionArea(new Point(-50, 0), 50, height));
        walls.add(new CollisionArea(new Point(0, -50), width, 50));
        walls.add(new CollisionArea(new Point(width, 0), 50, height));
        walls.add(new CollisionArea(new Point(0, height), width, 50));
        CollisionArea floatingWall = new CollisionArea(new Point(200, 50), 50, 100, true);
        renderObjects.add(floatingWall);
        walls.add(floatingWall);
    }

    public static List<CollisionArea> getWalls() {
        return walls;
    }
}
