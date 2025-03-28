package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.Renderable;
import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.Point;

public class PongBackground extends Drawable implements Renderable {

    public PongBackground(int width, int height) {
        this.width = width;
        this.height = height;
        position = new Point(0,0);
    }
    @Override
    public void render() {
        RenderHelper.renderColor(this, 0x130555);
    }

}
