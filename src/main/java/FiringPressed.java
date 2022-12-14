package main.java;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

// The listener for the players turn
public class FiringPressed implements ActionListener{
    private int row, col, grid[][];
    private AdaShipConfig adaShipConfig;
    private JButton buttonPressed;
    private Game gameplay;

    public FiringPressed(int row, int column, JButton buttonPressed, int[][] grid) {
        this.row = row;
        this.col = column;
        this.buttonPressed = buttonPressed;
        this.grid = grid;
        adaShipConfig = AdaShipConfig.getInstance();
        gameplay = adaShipConfig.getGame();
    }

    // Listener method checks the outcome, updating the players firing grid with the correct colour
    // Also records the play and completes the turn by changing the game state
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] coords = {row, col};
        switch(grid[row][col]) {
            case AdaShipConfig.OCEAN:
                buttonPressed.setBackground(AdaShipConfig.MISS_COLOR);
                buttonPressed.setEnabled(false);
                grid[row][col] = AdaShipConfig.MISS;
                adaShipConfig.setEnemyGrid(grid);
                break;
            case AdaShipConfig.SHIP:
                buttonPressed.setBackground(AdaShipConfig.HIT_COLOR);
                buttonPressed.setEnabled(false);
                grid[row][col] = AdaShipConfig.HIT;
                adaShipConfig.setEnemyGrid(grid);
                gameplay.recordTurn(coords, adaShipConfig.getEnemyFleet());  
                break;
        }
        if (!gameplay.checkWin(adaShipConfig.getEnemyFleet())) {
            // Next turn                
            adaShipConfig.setGameState(AdaShipConfig.ENEMY_TURN);
        } else {
            // Win
            adaShipConfig.setGameState(AdaShipConfig.WIN);
        }
        adaShipConfig.getFiringPanel().disableButtons();
        gameplay.endTurn();
    }
}
