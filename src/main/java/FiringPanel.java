package main.java;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

// A firing panel object, a grid with buttons
public class FiringPanel extends JPanel {
    private JPanel gridPanels[][];
    private JButton gridButtons[][];
    private Color background, buttonColor;
    private boolean enabled;
    private int rows, cols, grid[][];

    public FiringPanel(Color background, Color buttonColor, boolean enabled, int rows, int cols, int[][] grid) {
        this.background = background;
        this.buttonColor = buttonColor;
        this.enabled = enabled;
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
        this.gridPanels = new JPanel[rows][cols];
        this.gridButtons = new JButton[rows][cols];

        setLayout(AdaShipConfig.getFlowLayout(1,-5,-5));
        setPreferredSize(AdaShipConfig.getDimension(530, 525));
        setBackground(background);
    }
    
    // Returns the grid
    public int[][] getGrid() {
        return this.grid;
    }

    // Returns the grid buttons for the enemy to access
    public JButton[][] getGridButtons() {
        return this.gridButtons;
    }

    // Disables all buttons for the players turn end
    public void disableButtons() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                gridButtons[row][col].setEnabled(false);
            }
        }
    }

    // Enables the ocean buttons for the players turn
    public void enableButtons() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (grid[row][col] != AdaShipConfig.HIT && grid[row][col] != AdaShipConfig.MISS) {
                    gridButtons[row][col].setEnabled(true);
                }
            }
        }
    }

    // Builds the grid, an array of panels and buttons 
    // Setting the colours to the ones provided
    public void build() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {                
                gridPanels[row][col] = new JPanel();
                gridPanels[row][col].setBackground(this.background);
                gridButtons[row][col] = new JButton();
                gridPanels[row][col].setLayout(AdaShipConfig.getFlowLayout());
                gridButtons[row][col].setPreferredSize(AdaShipConfig.getDimension(48,48)); // Refactor
                
                if (!enabled && grid[row][col] == AdaShipConfig.SHIP) {
                    gridButtons[row][col].setBackground(AdaShipConfig.SHIP_COLOR);  // Refactor
                } else {
                    gridButtons[row][col].setBackground(this.buttonColor);
                }
                gridButtons[row][col].setEnabled(this.enabled);
                gridButtons[row][col].addActionListener(new FiringPressed(row, col, gridButtons[row][col], grid));
                gridPanels[row][col].add(gridButtons[row][col]);
                add(gridPanels[row][col]);
            }
        }
    }
}
