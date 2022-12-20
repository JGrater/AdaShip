package main.java;

import java.awt.Color;
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
        setBackground(Color.red);
        render();
    }

    public JTextField getCoordinateField() {
        return this.coordinateField;
    }

    public void setCoordinateField(JTextField coordinateField) {
        this.coordinateField = coordinateField;
    }

    public JComboBox<String> getDirectionBox() {
        return this.directionBox;
    }

    public void setDirectionBox(JComboBox<String> directionBox) {
        this.directionBox = directionBox;
    }

    public Ship getShip() {
        return this.ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void render() {
        int coordinateStart[] = ship.getCoords().get(0);

        shipLabel.setPreferredSize(AdaShipConfig.getDimension(270, 25));
        shipLabel.setText("Name: " + ship.getType() + "  ,  Length: " + ship.getHealth());
        shipLabel.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 20));
        shipLabel.setForeground(Color.white);
        shipLabel.setHorizontalAlignment(JLabel.LEFT);

        coordinateField.setPreferredSize(AdaShipConfig.getDimension(100, 25));
        coordinateField.setText(coordinateStart[1] + "," + coordinateStart[0]);

        for (int i = 0; i < directions.length; i++) {
            if (directions[i].equalsIgnoreCase(ship.getDirection())) {
                directionBox.setSelectedIndex(i);
            }
        }
        
        add(shipLabel);
        add(coordinateField);
        add(directionBox);
    }


}
