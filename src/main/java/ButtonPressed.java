package main.java;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonPressed implements ActionListener{
    private int row, col;
    private int[][] grid;
    AdaShipConfig adaShipConfig;
    JButton button;

    public ButtonPressed(int row, int column, JButton button, int[][] grid) {
            this.row = row;
            this.col = column;
            this.button = button;
            this.grid = grid;
            
    }


//maybe move to game
    public void recordHit(int[] coords) {
        for (int i = 0; i < adaShipConfig.getEnemyFleet().size(); i++) {
            if (adaShipConfig.getEnemyFleet().get(i).checkHit(coords)) {
                System.out.println("HIT!!");
                if (adaShipConfig.getEnemyFleet().get(i).checkDestroyed()) {
                    System.out.println("SHIP SUNK!!!");
                }
            }
        }
    }

    // Move to computers go 
    public void checkLoss() {
        int destroyedShips = 0;
        for (int i = 0; i < adaShipConfig.getFleet().size(); i++) {
            if (adaShipConfig.getFleet().get(i).isDestroyed()) {
                destroyedShips++;
            }
        }
        if (destroyedShips == adaShipConfig.getFleet().size()) {
            adaShipConfig.setGameState(AdaShipConfig.LOSS);
            System.out.println("YOU HAVE LOST!!");
        } 
    }

    public void checkWin() {
        int destroyedShips = 0;
        for (int i = 0; i < adaShipConfig.getEnemyFleet().size(); i++) {
            if (adaShipConfig.getEnemyFleet().get(i).isDestroyed()) {
                destroyedShips++;
            }
        }
        if (destroyedShips == adaShipConfig.getEnemyFleet().size()) {
            adaShipConfig.setGameState(AdaShipConfig.WIN);
            System.out.println("YOU HAVE WON!!");
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaShipConfig = AdaShipConfig.getInstance();
        int[] coords = {row, col};
        switch(grid[row][col]) {
            case AdaShipConfig.OCEAN:
                button.setBackground(Color.white);
                button.setEnabled(false);
                grid[row][col] = AdaShipConfig.MISS;
                adaShipConfig.setEnemyGrid(grid);
                System.out.println("Col: " + col + "Row: "+ row);
                break;
            case AdaShipConfig.SHIP:
                button.setBackground(Color.red);
                button.setEnabled(false);
                grid[row][col] = AdaShipConfig.HIT;
                adaShipConfig.setEnemyGrid(grid);
                recordHit(coords);
                checkWin();
                System.out.println("Col: " + col + "Row: "+ row);
                break;
        }
    }
}
