package main.java;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonPressed implements ActionListener{
    private int row, col;
    private int[][] grid;
    AdaShipConfig adaShipConfig;
    FiringPanel shipPanel;
    JButton button;
    Game gameplay;

    public ButtonPressed(int row, int column, JButton button, int[][] grid) { //TODO Misleading name
        this.row = row;
        this.col = column;
        this.button = button;
        this.grid = grid;
        adaShipConfig = AdaShipConfig.getInstance();
        gameplay = new Game(adaShipConfig);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                gameplay.recordHit(coords, adaShipConfig.getEnemyFleet());
                System.out.println("Col: " + col + "Row: "+ row);
                break;
        }
        if (!gameplay.checkWin(adaShipConfig.getEnemyFleet())) {
            //next turn
            adaShipConfig.setGameState(AdaShipConfig.ENEMY_TURN);
        } else {
            // Win
            adaShipConfig.setGameState(AdaShipConfig.WIN);
        }
        adaShipConfig.getFiringPanel().disableButtons();
        gameplay.endTurn();
    }
}
