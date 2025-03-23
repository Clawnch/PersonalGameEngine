package GameTesting.AdvancedGui.PongGame.HelperClasses;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.Renderable;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Point;

public class RenderHelper {

    private static int[] pixels;
    public static void setPixels(int[] pixels) {
        RenderHelper.pixels = pixels;
    }

    public static void renderColor(Drawable object, int color) {
        Point position = object.getPosition();
        int height = object.getHeight();
        int width = object.getWidth();
        for (int y = position.getY(); y < height + position.getY(); y++) {
            for (int x = position.getX(); x < width + position.getX(); x++) {
                int index = x + (y * Main.width);
                if (isOnScreen(x, y) && index > 0 && index < pixels.length) pixels[index] = color;
            }
        }
    }

    private static boolean isOnScreen(int x, int y) {
        return x > 0 && y > 0 && x < Main.width && y < Main.height;
    }
}
