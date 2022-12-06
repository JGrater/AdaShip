package main.java;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonPressed implements ActionListener{
    private int row, col;
    private int[][] grid;
    
    JButton button;

    public ButtonPressed(int row, int column, JButton button, int[][] grid) {
            this.row = row;
            this.col = column;
            this.button = button;
            this.grid = grid;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(grid[row][col]) {
            case AdaShipConfig.OCEAN:
                button.setBackground(Color.white);
                button.setEnabled(false);
                break;
            case AdaShipConfig.SHIP:
                button.setBackground(Color.red);
                break;
        }
    }
    
}
