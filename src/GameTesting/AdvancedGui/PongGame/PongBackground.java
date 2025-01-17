package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Renderable;

public class PongBackground implements Renderable {

    private int width, height;
    private int[] pixels;

    public PongBackground(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }
    @Override
    public void render(int[] pixels) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + (y * width)] = 0x130555;
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
