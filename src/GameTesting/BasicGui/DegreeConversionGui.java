package GameTesting.BasicGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DegreeConversionGui extends JFrame {

    private JLabel label1, label2;
    private JButton button;
    private JTextField textField;

    public DegreeConversionGui() {
        initComponents();
    }

    private void initComponents() {
        textField = new JTextField();
        button = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Converter");

        button.setText("Press Button");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonActionPerformed(e);
            }
        });

        label1.setText("Celsius");
        label2.setText("Fahrenheit");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label1))
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(button)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label2)))
                                    .addContainerGap(27, Short.MAX_VALUE))
        );

       layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {button, textField});

       layout.setVerticalGroup(
               layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                       .addGroup(layout.createSequentialGroup()
                               .addContainerGap()
                               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                       .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                       .addComponent(label1))
                               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                       .addComponent(button)
                                       .addComponent(label2))
                               .addContainerGap(21, Short.MAX_VALUE))
       );
       pack();
    }

    private void buttonActionPerformed(ActionEvent event) {
        int tempFahr = (int)((Double.parseDouble(textField.getText())) * 1.8 + 32);
        label2.setText(tempFahr + " Fahrenheit");
    }
}
