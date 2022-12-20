package main.java;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFrame extends JFrame implements ActionListener{
    private JLabel label;
    private JPanel menuPanel, buttonPanel;
    private JButton newGameButton, exitButton;
    private NewGame newGameMenu;

    public MenuFrame() {
        menuPanel = new JPanel();
        newGameMenu = new NewGame(this);
        buttonPanel = new JPanel();
        newGameButton = new JButton();
        exitButton= new JButton();
        label = new JLabel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(AdaShipConfig.getFlowLayout(1, 0, 150));
        setVisible(true);
        setTitle("AdaShip");
        setResizable(true);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());      
    }

    public void render() {
        getContentPane().setBackground(Color.cyan);
        menuPanel.setPreferredSize(AdaShipConfig.getDimension(500,500));
        menuPanel.setLayout(AdaShipConfig.getFlowLayout(1, 0, 30));
        menuPanel.setBackground(Color.red);
        label.setFont(AdaShipConfig.getFont("Calibri", Font.PLAIN, 50));
        label.setText("AdaShips");

        buttonPanel.setLayout(AdaShipConfig.getFlowLayout(1, 0, 40));
        buttonPanel.setBackground(Color.red);
        buttonPanel.setPreferredSize(AdaShipConfig.getDimension(400, 350));
        
        newGameButton.setText("New Game");
        newGameButton.setFont(AdaShipConfig.getFont("Calibiri", Font.BOLD, 20));
        newGameButton.setBackground(Color.white);
        newGameButton.setPreferredSize(AdaShipConfig.getDimension(300, 50));
        newGameButton.addActionListener(this);
        
        exitButton.setText("Exit");
        exitButton.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 20));
        exitButton.setBackground(Color.white);
        exitButton.setPreferredSize(AdaShipConfig.getDimension(300, 50));
        exitButton.addActionListener(this);

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);
        menuPanel.add(label);
        menuPanel.add(buttonPanel);
        getContentPane().add(menuPanel);
        getContentPane().add(newGameMenu);
        pack();
        setLocationRelativeTo(null);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == newGameButton) {
            menuPanel.setVisible(false);
            newGameMenu.setVisible(true);
        }
    }
}
