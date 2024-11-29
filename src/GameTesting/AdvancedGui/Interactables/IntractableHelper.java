package GameTesting.AdvancedGui.Interactables;

public class IntractableHelper {

    public static boolean isInClickArea(ViewPanel b, int x, int y) {
        boolean betweenWidth = x >= b.x && x <= b.x + b.width;
        boolean betweenHeight = y >= b.y && y <= b.y + b.height;
//        System.out.printf("Click check! In Width:%s In Height:%s%n",
//                betweenWidth, betweenHeight);
//        System.out.printf("Panel with dimensions x:%s,y:%s width:%S,height%s%n",
//                b.x, b.y, b.width, b.height);
        return betweenWidth && betweenHeight;
    }
}
