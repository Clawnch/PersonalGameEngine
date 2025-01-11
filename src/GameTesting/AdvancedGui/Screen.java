package GameTesting.AdvancedGui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen {

    private int width, height;
    private int[] pixels;

    private List<Tile> tiles = new ArrayList<>();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

    }

    public void render() {
        for (Tile tile : tiles) {
            int yLimit = tile.getY() + tile.getHeight();
            int xLimit = tile.getX() + tile.getWidth();
            for (int y = tile.getY(); y < yLimit; y++) {
                for (int x = tile.getX(); x < xLimit; x++) {
                    pixels[x + (y * width)] = tile.getColor();

                }
            }
        }
    }

    public void update() {
        int size = 4;
        tiles.clear();
        for (int i = 0; i < Main.width; i+= size) {
            Random random = new Random();
            for (int j = 0; j < Main.height; j+= size) {
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                tiles.add(new Tile(i, j, size, size, new Color(r, g, b)));
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }


}
