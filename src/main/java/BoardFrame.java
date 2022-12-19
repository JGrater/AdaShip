package main.java;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardFrame extends JFrame {
    private FiringPanel firingPanel, enemyFiringPanel;
    private JPanel header, footer;
    private JLabel title;

    public BoardFrame(AdaShipConfig adaShipConfig) {
        header = new JPanel();
        footer = new JPanel();
        title = new JLabel();
        firingPanel = new FiringPanel(AdaShipConfig.FIRING_COLOR, AdaShipConfig.BUTTON_COLOR, true, adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols(), adaShipConfig.getEnemyGrid());
        enemyFiringPanel = new FiringPanel(AdaShipConfig.OCEAN_COLOR, AdaShipConfig.OCEAN_COLOR, false, adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols(), adaShipConfig.getGrid());
        adaShipConfig.setFiringPanel(firingPanel);
        adaShipConfig.setEnemyFiringPanel(enemyFiringPanel);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(AdaShipConfig.getBorderLayout());
        setVisible(true);
        setTitle("AdaShip");
        setResizable(true); //false
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void render() {
        header.setLayout(AdaShipConfig.getBorderLayout());
        header.setPreferredSize(AdaShipConfig.getDimension(1070, 50));
        title.setText("AdaShip");
        title.setFont(AdaShipConfig.getFont("Calibri", Font.PLAIN, 30));
        header.add(title, BorderLayout.CENTER);

        firingPanel.setLayout(AdaShipConfig.getFlowLayout(1, -5, -5));
        firingPanel.setPreferredSize(AdaShipConfig.getDimension(530, 525));
        firingPanel.setBackground(AdaShipConfig.FIRING_COLOR);

        enemyFiringPanel.setLayout(AdaShipConfig.getFlowLayout(1, -5, -5));
        enemyFiringPanel.setPreferredSize(AdaShipConfig.getDimension(530, 525));
        enemyFiringPanel.setBackground(AdaShipConfig.OCEAN_COLOR);

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
