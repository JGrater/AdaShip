package main.java;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardFrame extends JFrame {
    private AdaShipConfig adaShipConfig;
    private JPanel firingPanel, fleetPanel, header, footer;
    private JLabel title;
    private Color oceanColour, firingColour, firingButtonColour;

    public BoardFrame(AdaShipConfig adaShipConfig) {
        this.adaShipConfig = adaShipConfig;
    }

    public Dimension getDimension(int width, int height) {
        return new Dimension(width, height);
    }

    public FlowLayout getFlowLayout(int align, int hgap, int vgap) {
        return new FlowLayout(align, hgap, vgap);
    }

    public BorderLayout getBorderLayout() {
        return new BorderLayout();
    }

    public Font getFont(String name, int style, int size) {
        return new Font(name, style, size);
    }

    public void render() {
        header = new JPanel();
        footer = new JPanel();
        title = new JLabel();
        oceanColour = new Color(6,66,115);
        firingColour = Color.cyan;
        firingButtonColour = Color.black;
        firingPanel = new Grid(this.firingColour, this.firingButtonColour, true, 10, 10, adaShipConfig.getEnemyGrid()); //Change length/width
        fleetPanel = new Grid(this.oceanColour, this.oceanColour, false, 10, 10, adaShipConfig.getGrid());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(getBorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("AdaShip");
        setResizable(true); //false
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        header.setLayout(getBorderLayout());
        header.setPreferredSize(getDimension(1070, 50));
        title.setText("AdaShip");
        title.setFont(getFont("Calibri", Font.PLAIN, 30));
        header.add(title, BorderLayout.CENTER);


        firingPanel.setLayout(getFlowLayout(1, -5, -5));
        firingPanel.setPreferredSize(getDimension(530, 525));
        firingPanel.setBackground(firingColour);

        fleetPanel.setLayout(getFlowLayout(1, -5, -5));
        fleetPanel.setPreferredSize(getDimension(530, 525));
        fleetPanel.setBackground(oceanColour);

        ((Grid) firingPanel).build();
        ((Grid) fleetPanel).build();

        add(firingPanel, BorderLayout.WEST);
        add(fleetPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        pack();                   
    }

}
