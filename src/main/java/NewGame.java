package main.java;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class NewGame extends JPanel implements ActionListener{
    AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();
    JPanel header, footer;
    JLabel instruction;
    JButton randomPlay, play, exit;
    NewShipPanel[] fleet;

    public NewGame() {
        header = new JPanel();
        footer = new JPanel();
        instruction = new JLabel();
        randomPlay = new JButton();
        play = new JButton();
        exit = new JButton();

        setPreferredSize(AdaShipConfig.getDimension(750,500));
        setLayout(AdaShipConfig.getFlowLayout(FlowLayout.CENTER, 0, 30));
        setBackground(Color.red);
        setVisible(false);
    }

    public void build() {
        instruction.setText("Pick a starting coordinate and a direction to face your ship!");
        instruction.setFont(AdaShipConfig.getFont("Calibri", Font.BOLD, 25));
        instruction.setForeground(Color.WHITE);

        header.setPreferredSize(AdaShipConfig.getDimension(750, 40));
        header.setBackground(Color.red);
        header.setLayout(AdaShipConfig.getFlowLayout(FlowLayout.CENTER, 75, 5));
        header.add(instruction);
        add(header);

        fleet = new NewShipPanel[adaShipConfig.getFleet().size()];
        for (int i = 0; i < fleet.length; i++) {
            fleet[i] = new NewShipPanel(adaShipConfig.getFleet().get(i));
            add(fleet[i]);
        }

        footer.setPreferredSize(AdaShipConfig.getDimension(750, 40));
        footer.setBackground(Color.red);
        footer.setLayout(AdaShipConfig.getFlowLayout(FlowLayout.RIGHT, 50, 2));

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            adaShipConfig.getGame().runMenu();
        }
    }
}
