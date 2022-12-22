package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

// Gameplay class, functions the turn-based gameplay using state design pattern
public class Game implements ActionListener{
    private Random rand;
    private Timer timer;
    private final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;  
    private AdaShipConfig adaShipConfig;
    private Enemy enemy;
    private Player player;
    private MenuFrame menuFrame;
    private BoardFrame boardFrame;

    public Game(AdaShipConfig adaShipConfig) {
        this.adaShipConfig = adaShipConfig;
        player = new Player(adaShipConfig);
        enemy = new Enemy(adaShipConfig, this);
        rand = new Random();
        timer = new Timer(1000, this);
        timer.setRepeats(false);
    }

    // Renders the menu frame
    public void runMenu() {
        menuFrame = new MenuFrame(adaShipConfig);
        menuFrame.render();
    }

    // Renders the board frame
    public void runGame() {
        boardFrame = new BoardFrame(this.adaShipConfig);
        boardFrame.render();
    }

    // Checks the current game state, called at the end of each turn
    // Allows for turn-based gameplay
    public void endTurn() {
        switch(adaShipConfig.getGameState()) {
            case AdaShipConfig.PLAYER_TURN:
                player.completeTurn();
                break; 
            case AdaShipConfig.ENEMY_TURN: 
                enemy.completeTurn();
                break;
            case AdaShipConfig.WIN:
                //Win screen
                boardFrame.message("YOU HAVE WON!!");
                break;
            case AdaShipConfig.LOSS:
                // Lose Screen
                boardFrame.message("YOU HAVE LOST!");
                break;
        }
    }

    // Records the turns played and displays the correct pop-up message for one second
    public void recordTurn(int[] coords, ArrayList<Ship> fleet) {
        for (int i = 0; i < fleet.size(); i++) {
            if (fleet.get(i).checkHit(coords)) {
                if (fleet.get(i).checkDestroyed()) {
                    if (fleet == adaShipConfig.getFleet()) {
                        boardFrame.message("LOST A SHIP!");
                        timer.start();
                    } else {
                        boardFrame.message("SHIP SUNK!!!");
                        timer.start();
                    }
                } else {
                    if (fleet == adaShipConfig.getFleet()) {
                        boardFrame.message("TAKEN DAMAGE!!!");
                        timer.start();
                    } else {
                        boardFrame.message("THAT'S A HIT!!!");
                        timer.start();
                    }
                }
            }
        }
    }

    // Returns true if all of the given fleets ships have been sunk
    public boolean checkWin(ArrayList<Ship> fleet) {
        boolean win = false;
        int destroyedShips = 0;
        for (int i = 0; i < fleet.size(); i++) {
            if(fleet.get(i).isDestroyed()) {
                destroyedShips++;
            }
        }
        if (destroyedShips == fleet.size()) {
            win = true;
        } 
        return win;
    }
    
    // Deploys ships with random starting coordinates and facing directions
    // Checks their lengths fit against the borders of the board
    public void deployShipRandom(Ship ship, int[][] grid, int rows, int cols) {
        boolean cont = true, emptySquare = true;
        int direction, x, y; // Col , Row

        while(cont) {
            emptySquare = true;
            direction = rand.nextInt(1, 5);
            x = rand.nextInt(rows);
            y = rand.nextInt(cols);

            if (direction == UP) {
                if (y + ship.getHealth() <= rows-1) {
                    for (int i = y; i < y + ship.getHealth(); i++) {
                        if (grid[i][x] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = y; i < y + ship.getHealth(); i++) {
                            grid[i][x] = AdaShipConfig.SHIP;
                            int[] coords = {i,x};
                            ship.addCoords(coords);
                        }
                        return;
                    }
                }
            } else if (direction == DOWN) {
                if (y - ship.getHealth() >= 0) {
                    for (int i = y; i > y - ship.getHealth(); i--) {
                        if (grid[i][x] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = y; i > y - ship.getHealth(); i--) {
                            grid[i][x] = AdaShipConfig.SHIP;
                            int[] coords = {i,x};
                            ship.addCoords(coords);
                        }
                        return;
                    }
                }
            } else if (direction == LEFT) {
                if (x + ship.getHealth() <= cols-1) {
                    for (int i = x; i < x + ship.getHealth(); i++) {
                        if (grid[y][i] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = x; i < x + ship.getHealth(); i++) {
                            grid[y][i] = AdaShipConfig.SHIP;
                            int[] coords = {y,i};
                            ship.addCoords(coords);
                        }
                        return;
                    }
                }
            } else if (direction == RIGHT) {
                if (x - ship.getHealth() >= 0) {
                    for (int i = x; i > x - ship.getHealth(); i--) {
                        if (grid[y][i] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = x; i > x - ship.getHealth(); i--) {
                            grid[y][i] = AdaShipConfig.SHIP;
                            int[] coords = {y,i};
                            ship.addCoords(coords);
                        }
                        return;
                    }
                } 
            }
        }
    }

    // UNFINISHED
    // A manual re-work of the method above
/* 
    public boolean deployShip(Ship ship, int[][] grid, int rows, int cols, int x, int y, String direction) {
        boolean emptySquare = true;

        if (direction.equalsIgnoreCase(AdaShipConfig.NORTH)) {
            if (y + ship.getHealth() <= rows-1) {
                for (int i = y; i < y + ship.getHealth(); i++) {
                    if (grid[i][x] != AdaShipConfig.OCEAN) {
                        emptySquare = false;
                    }
                }
                if (emptySquare) {
                    for (int i = y; i < y + ship.getHealth(); i++) {
                        grid[i][x] = AdaShipConfig.SHIP;
                        int[] coords = {i,x};
                        ship.addCoords(coords);
                        ship.setDirection(AdaShipConfig.NORTH);
                    }
                    return true;
                }
            }
        } else if (direction.equalsIgnoreCase(AdaShipConfig.SOUTH)) {
            if (y - ship.getHealth() >= 0) {
                for (int i = y; i > y - ship.getHealth(); i--) {
                    if (grid[i][x] != AdaShipConfig.OCEAN) {
                        emptySquare = false;
                    }
                }
                if (emptySquare) {
                    for (int i = y; i > y - ship.getHealth(); i--) {
                        grid[i][x] = AdaShipConfig.SHIP;
                        int[] coords = {i,x};
                        ship.addCoords(coords);
                        ship.setDirection(AdaShipConfig.SOUTH);
                    }
                    return true;
                }
            }
        } else if (direction.equalsIgnoreCase(AdaShipConfig.WEST)) {
            if (x + ship.getHealth() <= cols-1) {
                for (int i = x; i < x + ship.getHealth(); i++) {
                    if (grid[y][i] != AdaShipConfig.OCEAN) {
                        emptySquare = false;
                    }
                }
                if (emptySquare) {
                    for (int i = x; i < x + ship.getHealth(); i++) {
                        grid[y][i] = AdaShipConfig.SHIP;
                        int[] coords = {y,i};
                        ship.addCoords(coords);
                        ship.setDirection(AdaShipConfig.WEST);
                    }
                    return true;
                }
            }
        } else if (direction.equalsIgnoreCase(AdaShipConfig.EAST)) {
            if (x - ship.getHealth() >= 0) {
                for (int i = x; i > x - ship.getHealth(); i--) {
                    if (grid[y][i] != AdaShipConfig.OCEAN) {
                        emptySquare = false;
                    }
                }
                if (emptySquare) {
                    for (int i = x; i > x - ship.getHealth(); i--) {
                        grid[y][i] = AdaShipConfig.SHIP;
                        int[] coords = {y,i};
                        ship.addCoords(coords);
                        ship.setDirection(AdaShipConfig.EAST);
                    }
                    return true;
                }
            } 
        }
        return false;
    }
*/

    // Hides the pop-up message after one second
    @Override
    public void actionPerformed(ActionEvent e) {
        boardFrame.hideMessage();
    }
}
