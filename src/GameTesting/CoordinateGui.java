package GameTesting;

import javax.swing.*;
import java.awt.*;

public class CoordinateGui extends JFrame {

    private JLabel hour, minute, second, feet, distance, calcHour, calcMinute, calcSecond, direction;

    private JButton submitButton;

    private JTextField hourInput, minuteInput, secondInput, distanceInput, directionInput;

    public CoordinateGui() {
        setConstants();
        initComponents();
    }

    private void setConstants() {
        hour = new JLabel();
        minute = new JLabel();
        second = new JLabel();
        feet = new JLabel();
        distance = new JLabel();
        direction = new JLabel();
        submitButton = new JButton();
        hourInput = new JTextField();
        minuteInput = new JTextField();
        secondInput  = new JTextField();
        distanceInput = new JTextField();
        directionInput= new JTextField();
        calcHour = new JLabel();
        calcMinute = new JLabel();
        calcSecond = new JLabel();

        hour.setText("hour");
        minute.setText("minutes");
        second.setText("seconds");
        direction.setText("Degrees (0 is N, 180 is S)");

        feet.setText("ft");
        distance.setText("Distance: ");

        submitButton.setText("Submit");

        calcHour.setText("Calculated Hour");
        calcMinute.setText("Calculated Minute");
        calcSecond.setText("Calculated Second");
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coordinate GUI");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup() // input label space, creates the input row
                                                .addComponent(hourInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(hour)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(minuteInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(minute)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(secondInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(second))
                                        .addGroup(layout.createSequentialGroup() //creates Distance input row
                                                .addComponent(distance)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(distanceInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(feet))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(direction)
                                                .addComponent(directionInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                                        .addGroup(layout.createSequentialGroup() //Button Row
                                                .addComponent(submitButton))
                                        .addGroup(layout.createSequentialGroup() //Calculated coordinates row, label label gap
                                                .addComponent(calcHour)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(calcMinute)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(calcSecond)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, submitButton, hourInput, minuteInput, secondInput, distanceInput, directionInput);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(hourInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hour)
                                        .addComponent(minuteInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(minute)
                                        .addComponent(secondInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(second)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(distance)
                                        .addComponent(distanceInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(feet))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(directionInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(direction)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(calcHour)
                                        .addComponent(calcMinute)
                                        .addComponent(calcSecond)
                                )

                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton))
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();


    }


}
