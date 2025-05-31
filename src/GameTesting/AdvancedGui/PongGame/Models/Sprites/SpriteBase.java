package GameTesting.AdvancedGui.PongGame.Models.Sprites;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Console.Debug;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteBase extends Drawable {

    private int[] pixels;
    private static final String RESOURCE_PATH = "EulerProjects\\src\\GameTesting\\AdvancedGui\\ExternalResources\\";

    public SpriteBase(String filename) {
        BufferedImage loadedImage = null;
        try {
            File file = new File(RESOURCE_PATH + filename);
            loadedImage = ImageIO.read(file);
            height = loadedImage.getHeight();
            width = loadedImage.getWidth();

            pixels = loadedImage.getRGB(0,0,width,height, null,0,width);

        } catch (IOException e) {
            Debug.printMessageToConsole(this, "Unable to load: " + filename);
            e.printStackTrace();

        }

    }

    public int[] getPixels() {
        return pixels;
    }



}
