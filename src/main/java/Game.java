package main.java;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    Random rand;
    final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4; 
    AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();
    ArrayList<Ship> fleet = adaShipConfig.getFleet(); 
    
    public void deployShip(Ship ship, int[][] grid) {
        rand = new Random();
        boolean cont = true, emptySquare = true;
        int direction;
        int x, y; // Col , Row

        while(cont) {
            emptySquare = true;
            direction = rand.nextInt(1, 5);
            x = rand.nextInt(10);
            y = rand.nextInt(10);

            if (direction == UP) {
                if (y + ship.getHealth() <= 9) {
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
                if (x + ship.getHealth() <= 9) {
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

}
