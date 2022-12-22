package main.java;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

// New game panel, replaces the menu panel when the new game button is clicked
public class NewGame extends JPanel implements ActionListener{
    private AdaShipConfig adaShipConfig;
    private MenuFrame menuFrame;
    private JPanel header, footer;
    private JLabel instruction;
    private JButton randomPlay, play, exit;
    private NewShipPanel[] newShipPanels;

    public NewGame(MenuFrame menuFrame, AdaShipConfig adaShipConfig) {
        this.menuFrame = menuFrame;
        this.adaShipConfig = adaShipConfig;
        header = new JPanel();
        footer = new JPanel();
        instruction = new JLabel();
        randomPlay = new JButton();
        play = new JButton();
        exit = new JButton();

        // Panel properties
        setPreferredSize(AdaShipConfig.getDimension(750,500));
        setLayout(AdaShipConfig.getFlowLayout(FlowLayout.CENTER, 0, 30));
        setBackground(Color.red);
        setVisible(false);
        render();
    }

    // Displays the new game menu
    // Includes a header with instructions, and an array of new ship panels, as well as a footer with random-play, play and exit buttons
    public void render() {
        // Instruction label
        instruction.setText("Pick a starting coordinate and a direction to face your ship!");
        instruction.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 25));
        instruction.setForeground(Color.WHITE);

        // Header panel
        header.setPreferredSize(AdaShipConfig.getDimension(750, 40));
        header.setBackground(Color.red);
        header.setLayout(AdaShipConfig.getFlowLayout(FlowLayout.CENTER, 75, 5));
        header.add(instruction);
        add(header);

        // Array of new ship panels class
        newShipPanels = new NewShipPanel[adaShipConfig.getFleet().size()];
        for (int i = 0; i < newShipPanels.length; i++) {
            newShipPanels[i] = new NewShipPanel(adaShipConfig.getFleet().get(i));
            add(newShipPanels[i]);
        }

        // Footer panel
        footer.setPreferredSize(AdaShipConfig.getDimension(750, 40));
        footer.setBackground(Color.red);
        footer.setLayout(AdaShipConfig.getFlowLayout(FlowLayout.RIGHT, 50, 2));

        // Exit button
        exit.setText("Exit");
        exit.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 18));
        exit.setBackground(Color.white);
        exit.setPreferredSize(AdaShipConfig.getDimension(100, 30));
        exit.addActionListener(this);

        // Random play button
        randomPlay.setText("Play Random!");
        randomPlay.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 18));
        randomPlay.setBackground(Color.white);
        randomPlay.setPreferredSize(AdaShipConfig.getDimension(150, 30));
        randomPlay.addActionListener(this);

        // Play button
        play.setText("Play!");
        play.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 18));
        play.setBackground(Color.white);
        play.setPreferredSize(AdaShipConfig.getDimension(100, 30));
        play.addActionListener(this);

        // Add components
        footer.add(randomPlay);
        footer.add(play);
        footer.add(exit);
        add(footer);
    }

    // UNFINISHED
    // Method checks the inputted values in the NewShipPanels textfield and the selected direction from the combobox
    // Returns true if ship can be deployed and highlights textfield green
    // Returns false if ship can't be deployed and highlights textfield red

/*
    public boolean checkCoords() {
        boolean valid = true;
        for (int i = 0; i < newShipPanels.length; i++) {
            String[] coords = newShipPanels[i].getCoordinateField().getText().split(",");
            if (adaShipConfig.getGame().deployShip(newShipPanels[i].getShip(), adaShipConfig.getGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols(), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), newShipPanels[i].getDirectionBox().getSelectedItem().toString())) {
                newShipPanels[i].getCoordinateField().setBorder(new LineBorder(Color.GREEN, 3));
            } else {
                newShipPanels[i].getCoordinateField().setBorder(new LineBorder(Color.RED, 3));
                valid = false;
            }
        }
        return valid;
    }
*/

    // Listens for buttons pressed
    // Exit button takes player back to starting menu
    // Random play starts the game with the current random placement
    // Play checks the inputted positions, if all true then game starts
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            menuFrame.dispose();
            adaShipConfig.getGame().runMenu();
        } else if (e.getSource() == randomPlay) {
            menuFrame.dispose();
            adaShipConfig.getGame().runGame();
        } else if (e.getSource() == play) {
            adaShipConfig.setGrid(new int[adaShipConfig.getBoard_rows()][adaShipConfig.getBoard_cols()]);
            // UNFINISHED
            /* 
            if (checkCoords()) {
                menuFrame.dispose();
                adaShipConfig.getGame().runGame();
            }
            */
        }
    }
}
