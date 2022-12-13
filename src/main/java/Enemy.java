package main.java;

import java.util.Random;

import javax.swing.JButton;

import java.awt.Color;

public class Enemy {
    AdaShipConfig adaShipConfig;
    Game gameplay;
    Random rand;

    public Enemy(AdaShipConfig adaShipConfig, Game game) {
        this.adaShipConfig = adaShipConfig;
        this.gameplay = game;
        this.rand = new Random();
    }
 
    public void completeTurn() {
        boolean valid = false;
        while (valid == false) {
            int row = rand.nextInt(adaShipConfig.getBoard_rows());
            int col = rand.nextInt(adaShipConfig.getBoard_cols());
            JButton button = adaShipConfig.getEnemyFiringPanel().getGridButtons()[row][col];
            int[][] grid = adaShipConfig.getGrid();
            if (grid[row][col] != AdaShipConfig.HIT && grid[row][col] != AdaShipConfig.MISS) {
                fire(row, col, button, grid);
                valid = true;
            }
        }
    }
   
    public void fire(int row, int col, JButton button, int[][] grid) {
        
        int[] coords = {row, col};
        switch(grid[row][col]) {
            case AdaShipConfig.OCEAN:
                button.setBackground(Color.white);
                button.setEnabled(false);
                grid[row][col] = AdaShipConfig.MISS;
                adaShipConfig.setGrid(grid);
                System.out.println("ENEMY - Col: " + col + "Row: "+ row);
                break;
            case AdaShipConfig.SHIP:
                button.setBackground(Color.red);
                button.setEnabled(false);
                grid[row][col] = AdaShipConfig.HIT;
                adaShipConfig.setGrid(grid);
                gameplay.recordHit(coords, adaShipConfig.getFleet());
                
                System.out.println("ENEMY - Col: " + col + "Row: "+ row);
                break;
        }
        if (!gameplay.checkWin(adaShipConfig.getFleet())) {
            //next turn
            adaShipConfig.setGameState(AdaShipConfig.PLAYER_TURN);
        } else {
            // Win
            adaShipConfig.setGameState(AdaShipConfig.ENEMY_WIN);
        }
        gameplay.endTurn();
    }

}
