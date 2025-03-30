package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Components.CollisionArea;
import GameTesting.AdvancedGui.PongGame.Models.Pair;
import GameTesting.AdvancedGui.PongGame.Models.Point;
import GameTesting.AdvancedGui.PongGame.Models.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CollisionHelper {

    public static boolean isOverlapping(Rectangle rect1, Rectangle rect2) {
        Point rect1Start = rect1.getPoint(), rect2Start = rect2.getPoint();
        Pair<Integer> rect1XPair = new Pair<>(rect1Start.getX(), rect1Start.getX() + rect1.getWidth());
        Pair<Integer> rect2XPair = new Pair<>(rect2Start.getX(), rect2Start.getX() + rect2.getWidth());

        boolean overlappingX = isOverlappingSegment(rect1XPair, rect2XPair);

        Pair<Integer> rect1YPair = new Pair<>(rect1Start.getY(), rect1Start.getY() + rect1.getHeight());
        Pair<Integer> rect2YPair = new Pair<>(rect2Start.getY(), rect2Start.getY() + rect2.getHeight());

        boolean overlappingY = isOverlappingSegment(rect1YPair, rect2YPair);

        return overlappingY && overlappingX;
    }

    private static boolean isOverlappingSegment(Pair<Integer> pair1, Pair<Integer> pair2) {
        Pair<Integer> longestSegment, shortestSegment;
        int p1Length = pair1.getSecond() - pair1.getFirst();
        int p2Length = pair2.getSecond() - pair2.getFirst();

        longestSegment = (p1Length >= p2Length) ? pair1 : pair2;
        shortestSegment = (p1Length >= p2Length) ? pair2 : pair1;

        int x1 = shortestSegment.getFirst();
        int x2 = shortestSegment.getSecond();

        int y1 = longestSegment.getFirst();
        int y2 = longestSegment.getSecond();

        boolean overlap = x1 >= y1 && x1 <= y2;
        boolean overlap2 = x2 >= y1 && x2 <= y2;

        return overlap2 || overlap;
    }


    /**
     *
     * @param collisionArea Larger rectangle, the area you are testing if the object is in
     * @param object Smaller rectangle, the item you are testing if it is in the larger rectangle
     * @return true if any of the object is outside the boundary
     */
    public static boolean isOutsideArea(CollisionArea collisionArea, Rectangle object) {
        List<Point> objectCorners = new ArrayList<>();
        Point origin, east, south, far;
        origin = object.getPoint();
        east = new Point(origin.getX() + object.getWidth(), origin.getY());
        south = new Point(origin.getX(), origin.getY() + object.getHeight());
        far = new Point(east.getX(), south.getY());

        objectCorners.add(origin);
        objectCorners.add(east);
        objectCorners.add(south);
        objectCorners.add(far);

        for (Point p : objectCorners) {
            if (!pointIsInArea(collisionArea, p)) return true;
        }
        return false;
    }


    public static boolean pointIsInArea(CollisionArea area, Point point) {
        int x1 = point.getX(), y1 = point.getY();
        int x2 = area.getArea().getPoint().getX(), y2 = area.getArea().getPoint().getY();
        boolean xComp = x1 >= x2 && x1 <= x2 + area.getWidth();
        boolean yComp = y1 >= y2 && y1 <= y2 + area.getHeight();

        return xComp && yComp;
    }


}
