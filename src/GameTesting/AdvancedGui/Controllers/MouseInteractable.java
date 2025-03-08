package GameTesting.AdvancedGui.Controllers;

import GameTesting.AdvancedGui.PongGame.Models.Point;

public interface MouseInteractable {

    void onRightClick(Point point);
    void onLeftClick(Point point);
    void onRightRelease(Point point);
    void onLeftRelease(Point point);
}
