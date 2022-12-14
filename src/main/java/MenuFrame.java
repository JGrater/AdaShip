package main.java;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;
import java.awt.geom.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;

public class MenuFrame extends JFrame {
    private AdaShipConfig adaShipConfig;
    private JLabel label;
    private JPanel menuPanel;

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

    public MenuFrame(AdaShipConfig adaShipConfig) {
        this.adaShipConfig = adaShipConfig;
        menuPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton newGame = new JButton();
        JButton exit = new JButton();
        label = new JLabel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(getFlowLayout(1, 0, 30));
        setVisible(true);
        setTitle("AdaShip");
        setResizable(true);
        setPreferredSize(getDimension(600, 600));
        getContentPane().setBackground(Color.cyan);

        menuPanel.setPreferredSize(getDimension(500,500));
        menuPanel.setLayout(getFlowLayout(1, 0, 30));
        menuPanel.setBackground(Color.red);
        // menuPanel.setVisible(false);
        label.setFont(getFont("Calibri", Font.PLAIN, 50));
        label.setText("AdaShips");
        

        buttonPanel.setLayout(getFlowLayout(1, 0, 40));
        buttonPanel.setBackground(Color.red);
        buttonPanel.setPreferredSize(getDimension(400, 350));
        
        newGame.setText("New Game");
        newGame.setFont(getFont("Calibiri", Font.BOLD, 20));
        newGame.setBackground(Color.white);
        newGame.setPreferredSize(getDimension(300, 50));
        newGame.addActionListener(new ActionListener(){ // New buttonPressed class

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BoardFrame boardFrame = new BoardFrame(AdaShipConfig.getInstance());
                boardFrame.render();
            }
            
        });
        
        exit.setText("Exit");
        exit.setFont(getFont("Calibri", Font.BOLD, 20));
        exit.setBackground(Color.white);
        exit.setPreferredSize(getDimension(300, 50));
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);                
            }
            
        });

        
        buttonPanel.add(newGame);
        buttonPanel.add(exit);
        menuPanel.add(label);
        menuPanel.add(buttonPanel);
        getContentPane().add(menuPanel);
        pack();
        setLocationRelativeTo(null);
    }
}
