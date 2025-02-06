package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

public class CollisionHelper {

    public static boolean isOverlapping(Rectangle area1, Rectangle area2) {


        return false;
    }


    public static boolean isInArea(Rectangle area, Point point) {
        int x1 = point.getX(), y1 = point.getY();
        int x2 = area.getPoint().getX(), y2 = area.getPoint().getY();
        boolean xComp = x1 >= x2 && x1 < x2 + area.getWidth();
        boolean yComp = y1 >= y2 && y1 < y2 + area.getHeight();

        return xComp && yComp;
    }
}
