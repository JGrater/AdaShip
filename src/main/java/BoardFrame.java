package main.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class BoardFrame extends JFrame implements ActionListener{
    private AdaShipConfig adaShipConfig;
    private FiringPanel firingPanel, enemyFiringPanel;
    private JPanel header, footer, messagePanel;
    private JButton exitButton;
    private JLabel title, messageLabel;
    private Popup popup;
    private PopupFactory popupFactory;

    public BoardFrame(AdaShipConfig adaShipConfig) {
        this.adaShipConfig = adaShipConfig;
        header = new JPanel();
        footer = new JPanel();
        title = new JLabel();
        exitButton = new JButton();
        popupFactory = new PopupFactory();
        messagePanel = new JPanel();
        messageLabel = new JLabel();
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

    public void message(String text) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        messageLabel.setText(text);
        messagePanel.add(messageLabel);
        if (this.adaShipConfig.getGameState() == AdaShipConfig.WIN || this.adaShipConfig.getGameState() == AdaShipConfig.LOSS) {
            messagePanel.setBackground(AdaShipConfig.HIT_COLOR);
            messageLabel.setForeground(AdaShipConfig.BUTTON_COLOR);
            popup = popupFactory.getPopup(this, messagePanel, (screenSize.width/2) - 200 , (screenSize.height/2)- 100);
        } else {
            popup = popupFactory.getPopup(this, messagePanel, (screenSize.width/2) - 533, (screenSize.height/2) + 173);
        }
        popup.show();
    }

    public void hideMessage() {
        popup.hide();
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

        messageLabel.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 50));
        messageLabel.setForeground(AdaShipConfig.FIRING_COLOR);
        messagePanel.setBackground(AdaShipConfig.BUTTON_COLOR);
        messagePanel.add(messageLabel);

        exitButton.setText("Exit");
        exitButton.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 20));
        exitButton.setBackground(AdaShipConfig.MISS_COLOR);
        exitButton.setForeground(AdaShipConfig.OCEAN_COLOR);
        exitButton.setPreferredSize(AdaShipConfig.getDimension(300, 50));
        exitButton.addActionListener(this);
        footer.add(exitButton);

        firingPanel.build();
        enemyFiringPanel.build();

        add(firingPanel, BorderLayout.WEST);
        add(enemyFiringPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        pack();     
        setLocationRelativeTo(null);              
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            dispose();
            adaShipConfig.getGame().runMenu();
        }
    }

}
