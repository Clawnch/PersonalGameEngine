package GameTesting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoordinateGui extends JFrame {

    private JLabel hourLongitude, minuteLongitude, secondLongitude,
            hourLatitude, minuteLatitude, secondLatitude,
            feet, distance, direction,
            calculatedLongitude, calculatedLatitude;

    private JButton submitButton;

    private JTextField hourLongInput, minuteLongInput, secondLongInput,
            hourLatInput, minuteLatInput, secondLatInput,
            distanceInput, directionInput;

    public CoordinateGui() {
        instantiateFields();
        initComponents();
    }

    private void instantiateFields() {
        hourLongitude = new JLabel();
        minuteLongitude = new JLabel();
        secondLongitude = new JLabel();
        hourLatitude = new JLabel();
        minuteLatitude = new JLabel();
        secondLatitude = new JLabel();

        feet = new JLabel();
        distance = new JLabel();
        direction = new JLabel();
        submitButton = new JButton();

        hourLongInput = new JTextField();
        minuteLongInput = new JTextField();
        secondLongInput = new JTextField();
        hourLatInput = new JTextField();
        minuteLatInput = new JTextField();
        secondLatInput = new JTextField();

        distanceInput = new JTextField();
        directionInput= new JTextField();

        calculatedLongitude = new JLabel();
        calculatedLatitude = new JLabel();

        hourLongitude.setText("hour");
        minuteLongitude.setText("minutes");
        secondLongitude.setText("seconds");

        hourLatitude.setText("hour");
        minuteLatitude.setText("minutes");
        secondLatitude.setText("seconds");

        direction.setText("Degrees (0 is N, 180 is S)");

        feet.setText("ft");
        distance.setText("Distance: ");

        submitButton.setText("Submit");

        calculatedLongitude.setText("Calculated Longitude");
        calculatedLatitude.setText("Calculated Latitude");
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coordinate GUI");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonActionOnPress(e);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup() // input label space, creates the input row
                                                .addComponent(hourLongInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(hourLongitude)
                                                .addComponent(minuteLongInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(minuteLongitude)
                                                .addComponent(secondLongInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(secondLongitude))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(hourLatInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(hourLatitude)
                                                .addComponent(minuteLatInput,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(minuteLatitude)
                                                .addComponent(secondLatInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(secondLatitude)
                                        )
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
                                                .addComponent(calculatedLongitude)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(calculatedLatitude)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, submitButton,
                hourLongInput, minuteLongInput, secondLongInput,
                hourLatInput, minuteLatInput, secondLatInput,
                distanceInput, directionInput);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(hourLongInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hourLongitude)
                                        .addComponent(minuteLongInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(minuteLongitude)
                                        .addComponent(secondLongInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(secondLongitude)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(hourLatInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hourLatitude)
                                        .addComponent(minuteLatInput,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(minuteLatitude)
                                        .addComponent(secondLatInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(secondLatitude))
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
                                        .addComponent(calculatedLongitude)
                                        .addComponent(calculatedLatitude)
                                )

                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton))
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();


    }

    private void buttonActionOnPress (ActionEvent event) {
        int hourLong, minuteLong, secondLong,
                hourLat, minuteLat, secondLat,
                feet;
        double directionDegrees = Double.parseDouble(directionInput.getText());

        hourLong = Integer.parseInt(hourLongInput.getText());
        minuteLong = Integer.parseInt(minuteLongInput.getText());
        secondLong = Integer.parseInt(secondLongInput.getText());

        hourLat = Integer.parseInt(hourLatInput.getText());
        minuteLat = Integer.parseInt(minuteLatInput.getText());
        secondLat = Integer.parseInt(secondLatInput.getText());

        feet = Integer.parseInt(distanceInput.getText());

        CoordinateDegrees degrees = new CoordinateDegrees(hourLong, minuteLong, secondLong, hourLat, minuteLat, secondLat);
        DecimalPair debugPair = degrees.calculateCoordinates(feet, directionDegrees);

        System.out.println("Button pressed! decimal coordinates are \n" + debugPair");

        calculatedLongitude.setText(String.format("Longitude: %sD %sM %sS", degrees.getHourNS(), degrees.getMinuteNS(), degrees.getSecondNS()));
        calculatedLatitude.setText(String.format("Latitude: %sD %sM %sS", degrees.getHourEW(), degrees.getMinuteEW(), degrees.getSecondEW()));

        //Convert to degree Calculator object or create a math method to handle object creation and math


    }


}
