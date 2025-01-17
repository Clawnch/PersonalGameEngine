package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

public class Pong extends Drawable implements GameComponent {

    private PongBackground background;
    private PongBall ball;

    public Pong(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        background = new PongBackground(width, height);
        ball = new PongBall(width/2, height/2, new Rectangle(new Point(0, 0), width, height));
    }

    @Override
    public void render(int[] pixels) {
        background.render(pixels);
        ball.render(pixels);
    }

    @Override
    public void update() {
        System.out.println("Pong update");
        ball.update();
    }
}
