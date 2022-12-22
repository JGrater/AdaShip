package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

// The enemy computer opponent
public class Enemy implements ActionListener{
    private AdaShipConfig adaShipConfig;
    private Game gameplay;
    private Random rand;
    private Timer timer;
    private int grid[][], gridCoordinates[];
    private JButton buttonPressed;

    public Enemy(AdaShipConfig adaShipConfig, Game game) {
        this.adaShipConfig = adaShipConfig;
        this.gameplay = game;
        rand = new Random();
        timer = new Timer(2000, this);
    }

    // Chooses the coordinates strategically
    // Consult flowchart for explanation
    public int[] getCoords(int[][] grid) {
        int[] coords = {rand.nextInt(adaShipConfig.getBoard_rows()), rand.nextInt(adaShipConfig.getBoard_cols())};
        boolean valid = false, validDirection = true;
        int places = 1;
        for (int row = 0; row < adaShipConfig.getBoard_rows(); row++) {
            for (int col = 0; col < adaShipConfig.getBoard_cols(); col++) {

                if (grid[row][col] == AdaShipConfig.HIT) {
                    
                    for (Ship ship : adaShipConfig.getFleet()) {
                        for (int[] coordinate : ship.getCoords()) {
                            int[] hitCoordinate = {row, col}; 
                            if (Arrays.equals(hitCoordinate, coordinate)) {
                                if(ship.isDestroyed() == false) {
                                    while (validDirection) { // UP
                                        if (row - places >= 0) {
                                            switch (grid[row - places][col]) {
                                                case AdaShipConfig.HIT:
                                                    places++;
                                                    break;
                                                case AdaShipConfig.MISS:
                                                    validDirection = false;
                                                    break;
                                                default:
                                                    coords[0] = row - places;
                                                    coords[1] = col;
                                                    return coords;
                                            }
                                        } else {
                                            validDirection = false;
                                        }
                                    }
                                    
                                    validDirection = true;
                                    places = 1;
                                    
                                    while (validDirection) { // RIGHT
                                        if (col + places < adaShipConfig.getBoard_cols()) {
                                            switch (grid[row][col + places]) {
                                                case AdaShipConfig.HIT:
                                                    places++;
                                                    break;
                                                case AdaShipConfig.MISS:
                                                    validDirection = false;
                                                    break;
                                                default:
                                                    coords[0] = row;
                                                    coords[1] = col + places;
                                                    return coords;
                                            }
                                        } else {
                                            validDirection = false;
                                        }
                                    }
                
                                    validDirection = true;
                                    places = 1;
                                    
                                    while (validDirection) { // DOWN
                                        if (row + places < adaShipConfig.getBoard_rows()) {
                                            switch (grid[row + places][col]) {
                                                case AdaShipConfig.HIT:
                                                    places++;
                                                    break;
                                                case AdaShipConfig.MISS:
                                                    validDirection = false;
                                                    break;
                                                default:
                                                    coords[0] = row + places;
                                                    coords[1] = col;
                                                    return coords;
                                            }
                                        } else {
                                            validDirection = false;
                                        }
                                    }
                
                                    validDirection = true;
                                    places = 1;
                                    
                                    while (validDirection) { // LEFT
                                        if (col - places >= 0) {
                                            switch (grid[row][col - places]) {
                                                case AdaShipConfig.HIT:
                                                    places++;
                                                    break;
                                                case AdaShipConfig.MISS:
                                                    validDirection = false;
                                                    break;
                                                default:
                                                    coords[0] = row;
                                                    coords[1] = col - places;
                                                    return coords;
                                            }
                                        } else {
                                            validDirection = false;
                                        }
                                    }
                                    validDirection = true;
                                    places = 1;
                                }
                            }
                        }
                    }
                }
            } 
        }
        // Checks random coordinates haven't already been played
        while (valid == false) {
            if (grid[coords[0]][coords[1]] != AdaShipConfig.HIT && grid[coords[0]][coords[1]] != AdaShipConfig.MISS) {
                valid = true;
            } else {
                coords[0] = rand.nextInt(adaShipConfig.getBoard_rows());
                coords[1] = rand.nextInt(adaShipConfig.getBoard_cols());
            }
        }
        return coords;
    }
    
    // Runs the timer 
    // Timer for effect
    public void completeTurn() {
        timer.setRepeats(false);
        timer.start();
    }
   
    // Fire method checks the outcome, updating the players fleet grid with the correct colour
    // Also records the play and completes the turn by changing the game state
    // Similar to the implemented method in FiringPressed class
    public void fire(int[] coords, JButton button, int[][] grid) {        
        switch(grid[coords[0]][coords[1]]) {
            case AdaShipConfig.OCEAN:
                button.setBackground(Color.white);
                button.setEnabled(false);
                grid[coords[0]][coords[1]] = AdaShipConfig.MISS;
                adaShipConfig.setGrid(grid);
                break;
            case AdaShipConfig.SHIP:
                button.setBackground(Color.red);
                button.setEnabled(false);
                grid[coords[0]][coords[1]] = AdaShipConfig.HIT;
                adaShipConfig.setGrid(grid);
                if (!gameplay.checkWin(adaShipConfig.getFleet())) {
                    gameplay.recordTurn(coords, adaShipConfig.getFleet());
                }
                break;
        }
        if (!gameplay.checkWin(adaShipConfig.getFleet())) {
            // Players turn
            adaShipConfig.setGameState(AdaShipConfig.PLAYER_TURN);
        } else {
            // Enemy Win
            adaShipConfig.setGameState(AdaShipConfig.ENEMY_WIN);
        }
        gameplay.endTurn();
    }

    // Once timer is finished, this code is performed
    @Override
    public void actionPerformed(ActionEvent e) {
        grid = adaShipConfig.getGrid();
        gridCoordinates = getCoords(grid);
        buttonPressed = adaShipConfig.getEnemyFiringPanel().getGridButtons()[gridCoordinates[0]][gridCoordinates[1]];
        
        fire(gridCoordinates, buttonPressed, grid);
    }
}
