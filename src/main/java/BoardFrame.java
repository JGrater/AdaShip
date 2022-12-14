package main.java;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardFrame extends JFrame {
    private AdaShipConfig adaShipConfig;
    private FiringPanel firingPanel, enemyFiringPanel;
    private JPanel header, footer;
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
        firingPanel = new FiringPanel(this.firingColour, this.firingButtonColour, true, adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols(), adaShipConfig.getEnemyGrid());
        enemyFiringPanel = new FiringPanel(this.oceanColour, this.oceanColour, false, adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols(), adaShipConfig.getGrid());
        
        adaShipConfig.setFiringPanel(firingPanel);
        adaShipConfig.setEnemyFiringPanel(enemyFiringPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(getBorderLayout());
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

        enemyFiringPanel.setLayout(getFlowLayout(1, -5, -5));
        enemyFiringPanel.setPreferredSize(getDimension(530, 525));
        enemyFiringPanel.setBackground(oceanColour);

        firingPanel.build();
        enemyFiringPanel.build();

        add(firingPanel, BorderLayout.WEST);
        add(enemyFiringPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        pack();     
        setLocationRelativeTo(null);              
    }

}
