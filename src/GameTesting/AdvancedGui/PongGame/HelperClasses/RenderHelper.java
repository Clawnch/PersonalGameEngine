package GameTesting.AdvancedGui.PongGame.HelperClasses;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.Renderable;
import GameTesting.AdvancedGui.Main;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Sprites.SpriteBase;

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

    public static void renderPixels(int[] spritePixels, int startX, int startY, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int xShift = x + startX;
                int yShift = y + startY;
                int spriteIndex = x + (y * width);
                int screenIndex = xShift + (yShift * Main.width);
                if (isOnScreen(xShift, yShift) && screenIndex > 0 && screenIndex < pixels.length) {
                    pixels[screenIndex] = spritePixels[spriteIndex];
                }
            }
        }
    }

    public static void renderSprite(SpriteBase sprite, int posX, int posY) {
        int[] spritePixels = sprite.getPixels();
        int height = sprite.getHeight();
        int width = sprite.getWidth();
        renderPixels(spritePixels, posX, posY, width, height);
    }

    private static boolean isOnScreen(int x, int y) {
        return x > 0 && y > 0 && x < Main.width && y < Main.height;
    }
}
