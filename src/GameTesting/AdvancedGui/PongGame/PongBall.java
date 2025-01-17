package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.Components.Updatable;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

import java.util.Arrays;

public class PongBall extends Drawable implements GameComponent {

    private Point position;

    private int moveDir = 45;
    private int speed = 10;

    private Rectangle bounds;

    private final double radianToDegreeRatio = 57.2958;

    public PongBall(int initX, int initY, Rectangle bounds) {
        height = 16;
        width = height;
        position = new Point(initX, initY);
        pixels = new int[height * width];
        this.bounds = bounds;

        Arrays.fill(pixels, 0xDDFFDD);
    }

    @Override
    public void update() {

        double distanceEW = Math.cos(moveDir / radianToDegreeRatio) * speed;
        double distanceNS = Math.sin(moveDir / radianToDegreeRatio) * speed;
        int updatedX = (int)distanceEW + position.getX();
        int updatedY = (int)distanceNS + position.getY();
        Point updatedPos = new Point(updatedX, updatedY);

        System.out.println(updatedPos);
        if (bounds.isInArea(updatedPos)) {
            position = updatedPos;
        }

    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    @Override
    public void render(int[] pixels) {
        for (int y = this.getY(); y < height + this.getY(); y++) {
            for (int x = this.getX(); x < width + this.getX(); x++) {
                pixels[x + (y * Main.width)] = 0xDDFFDD;
            }
        }
    }
}
