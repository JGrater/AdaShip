package main.java;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

public class Grid extends JPanel {

    private JPanel gridPanels[][];
    private JButton gridButtons[][];
    private Color background, buttonColor;
    private boolean enabled;
    private int length, width, grid[][];


    public Grid(Color background, Color buttonColor, boolean enabled, int length, int width, int[][] grid) {
        this.background = background;
        this.buttonColor = buttonColor;
        this.enabled = enabled;
        this.length = length;
        this.width = width;
        this.grid = grid;
        gridPanels = new JPanel[length][width];
        gridButtons = new JButton[length][width];

        setLayout(new FlowLayout(1,-5,-5));
        setPreferredSize(new Dimension(530, 525));
        setBackground(background);
    }

    public void build() {
        for (int row = 0; row < this.length; row++) {
            for (int col = 0; col < this.width; col++) {                
                gridPanels[row][col] = new JPanel();
                gridPanels[row][col].setBackground(this.background);
                gridButtons[row][col] = new JButton();
                gridPanels[row][col].setLayout(new FlowLayout());
                gridButtons[row][col].setPreferredSize(new Dimension(48,48));
                if (grid[row][col] == AdaShipConfig.SHIP) {
                    gridButtons[row][col].setBackground(Color.yellow);
                } else {
                    gridButtons[row][col].setBackground(this.buttonColor);
                }
                gridButtons[row][col].setEnabled(this.enabled);
                gridButtons[row][col].addActionListener(new ButtonPressed(row, col, gridButtons[row][col], this.grid));
                gridPanels[row][col].add(gridButtons[row][col]);
                add(gridPanels[row][col]);
            }
        }
    }

}
