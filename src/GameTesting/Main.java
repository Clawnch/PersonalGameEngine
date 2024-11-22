package GameTesting;

import java.awt.*;

public class Main {


    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new DegreeConversionGui().setVisible(true);
//            }
//        });

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CoordinateGui().setVisible(true);
            }
        });
    }
}
