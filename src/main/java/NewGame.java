package main.java;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class NewGame extends JPanel {
    AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();
    public NewGame() {
        setPreferredSize(AdaShipConfig.getDimension(750,500));
        setLayout(AdaShipConfig.getFlowLayout(FlowLayout.CENTER, 0, 30));
        setBackground(Color.red);
        setVisible(false);
        build();
    }

    public void build() {
        JPanel header = new JPanel();
        JPanel footer = new JPanel();
        JLabel instruction = new JLabel();

        instruction.setText("Pick a starting coordinate and a direction to face your ship!");
        instruction.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 25));
        instruction.setForeground(Color.WHITE);

        header.setPreferredSize(AdaShipConfig.getDimension(750, 40));
        header.setBackground(Color.red);
        header.setLayout(AdaShipConfig.getFlowLayout(FlowLayout.CENTER, 75, 5));
        header.add(instruction);
        add(header);

        MenuShip[] fleet = new MenuShip[adaShipConfig.getFleet().size()];
        for (int i = 0; i < fleet.length; i++) {
            fleet[i] = new MenuShip(adaShipConfig.getFleet().get(i));
            add(fleet[i]);
        }

        footer.setPreferredSize(AdaShipConfig.getDimension(750, 40));
        footer.setBackground(Color.red);
        footer.setLayout(AdaShipConfig.getFlowLayout(FlowLayout.RIGHT, 50, 2));

        JButton randomPlay = new JButton();
        JButton play = new JButton();
        JButton exit = new JButton();

        exit.setText("Exit");
        exit.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 18));
        exit.setBackground(Color.white);
        exit.setPreferredSize(AdaShipConfig.getDimension(100, 30));

        randomPlay.setText("Play Random!");
        randomPlay.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 18));
        randomPlay.setBackground(Color.white);
        randomPlay.setPreferredSize(AdaShipConfig.getDimension(150, 30));

        play.setText("Play!");
        play.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 18));
        play.setBackground(Color.white);
        play.setPreferredSize(AdaShipConfig.getDimension(100, 30));

        footer.add(randomPlay);
        footer.add(play);
        footer.add(exit);
        add(footer);
    }
}

class MenuShip extends JPanel {

    private Ship ship;

    public MenuShip(Ship ship) {
        this.ship = ship;
        setPreferredSize(AdaShipConfig.getDimension(750, 40));
        setLayout(AdaShipConfig.getFlowLayout(1, 100, 5));
        setBackground(Color.red);
        String directions[] = {"NORTH", "SOUTH", "EAST", "WEST"};
        JTextField coordinateField = new JTextField();
        JComboBox<String> directionBox = new JComboBox<>(directions);
        JLabel shipLabel = new JLabel();

        shipLabel.setPreferredSize(AdaShipConfig.getDimension(270, 25));
        shipLabel.setText("Name: " + ship.getType() + "  ,  Length: " + ship.getHealth());
        shipLabel.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 20));
        shipLabel.setForeground(Color.WHITE);
        shipLabel.setHorizontalAlignment(JLabel.LEFT);

        coordinateField.setPreferredSize(AdaShipConfig.getDimension(100, 25));
        add(shipLabel);
        add(coordinateField);
        add(directionBox);
    }
}
