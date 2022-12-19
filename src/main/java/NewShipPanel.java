package main.java;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewShipPanel extends JPanel {

    private Ship ship;
    private String directions[] = {"NORTH", "SOUTH", "EAST", "WEST"};
    private JTextField coordinateField; 
    private JComboBox<String> directionBox;
    private JLabel shipLabel;

    public NewShipPanel(Ship ship) {
        this.ship = ship;
        coordinateField = new JTextField();
        directionBox = new JComboBox<>(directions);
        shipLabel = new JLabel();

        setPreferredSize(AdaShipConfig.getDimension(750, 40));
        setLayout(AdaShipConfig.getFlowLayout(1, 100, 5));
        setBackground(AdaShipConfig.HIT_COLOR);
    }

    public void build() {
        shipLabel.setPreferredSize(AdaShipConfig.getDimension(270, 25));
        shipLabel.setText("Name: " + ship.getType() + "  ,  Length: " + ship.getHealth());
        shipLabel.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 20));
        shipLabel.setForeground(AdaShipConfig.MISS_COLOR);
        shipLabel.setHorizontalAlignment(JLabel.LEFT);

        coordinateField.setPreferredSize(AdaShipConfig.getDimension(100, 25));
        add(shipLabel);
        add(coordinateField);
        add(directionBox);
    }


}
