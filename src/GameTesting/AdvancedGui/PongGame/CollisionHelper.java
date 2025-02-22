package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

public class CollisionHelper {

    public static boolean isOverlapping(Rectangle area1, Rectangle area2) {
        int rect1X = area1.getPoint().getX(), rect1Width = area1.getWidth();
        int rect1Y = area1.getPoint().getY(), rect1Height = area1.getHeight();
        int rect2X = area2.getPoint().getX(), rect2Width = area2.getWidth();
        int rect2Y = area2.getPoint().getY(), rect2Height = area2.getHeight();

        boolean horizontalCheck = rect1X >= rect2X && rect1X <= rect2X + rect2Width;
        boolean widthCheck = rect1X + rect1Width >= rect2X && rect1X + rect1Width <= rect2X + rect2Width;

        boolean xOverlap = horizontalCheck || widthCheck;

        boolean verticalCheck = rect1Y >= rect2Y && rect1Y <= rect2Y + rect2Height;
        boolean heightCheck = rect1Y + rect1Height >= rect2Y && rect1Y + rect1Height <= rect2Y + rect2Height;

        boolean yOverlap = verticalCheck || heightCheck;

        return xOverlap && yOverlap;
    }


    public static boolean isInArea(Rectangle area, Point point) {
        int x1 = point.getX(), y1 = point.getY();
        int x2 = area.getPoint().getX(), y2 = area.getPoint().getY();
        boolean xComp = x1 >= x2 && x1 < x2 + area.getWidth();
        boolean yComp = y1 >= y2 && y1 < y2 + area.getHeight();

        return xComp && yComp;
    }
}
