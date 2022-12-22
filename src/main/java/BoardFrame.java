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

// The Board frame class, the main game
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
        
        // Frame/window properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(AdaShipConfig.getBorderLayout());
        setVisible(true);
        setTitle("AdaShip");
        setResizable(true); //false
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Creates a new pop-up message using the text provided
    // If its a win/lose message the positioning is different
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

    // Hides theh pop-up message
    public void hideMessage() {
        popup.hide();
    }

    // Displays the main board
    // Includes, a header with title, a firing grid, a fleet grid, and a footer with an exit button
    public void render() {
        // Title
        header.setLayout(AdaShipConfig.getBorderLayout());
        header.setPreferredSize(AdaShipConfig.getDimension(1070, 50));
        title.setText("AdaShip");
        title.setFont(AdaShipConfig.getFont("Calibri", Font.PLAIN, 30));
        header.add(title, BorderLayout.CENTER);

        // Your firing panel / Enemies fleet panel
        firingPanel.setLayout(AdaShipConfig.getFlowLayout(1, -5, -5));
        firingPanel.setPreferredSize(AdaShipConfig.getDimension(530, 525));
        firingPanel.setBackground(AdaShipConfig.FIRING_COLOR);

        // Your fleet panel / Enemies firing panel
        enemyFiringPanel.setLayout(AdaShipConfig.getFlowLayout(1, -5, -5));
        enemyFiringPanel.setPreferredSize(AdaShipConfig.getDimension(530, 525));
        enemyFiringPanel.setBackground(AdaShipConfig.OCEAN_COLOR);

        // Pop-up message text
        messageLabel.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 50));
        messageLabel.setForeground(AdaShipConfig.FIRING_COLOR);
        messagePanel.setBackground(AdaShipConfig.BUTTON_COLOR);
        messagePanel.add(messageLabel);

        // Exit button
        exitButton.setText("Exit");
        exitButton.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 20));
        exitButton.setBackground(AdaShipConfig.MISS_COLOR);
        exitButton.setForeground(AdaShipConfig.OCEAN_COLOR);
        exitButton.setPreferredSize(AdaShipConfig.getDimension(300, 50));
        exitButton.addActionListener(this);
        footer.add(exitButton);

        firingPanel.build();
        enemyFiringPanel.build();

        // Adds to components to frame
        add(firingPanel, BorderLayout.WEST);
        add(enemyFiringPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        pack();     
        setLocationRelativeTo(null);              
    }

    // Listens for the exit button being pressed, will take player back to the start menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            dispose();
            adaShipConfig.getGame().runMenu();
        }
    }

}
