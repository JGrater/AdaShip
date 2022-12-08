package main.java;

import java.util.Random;

public class Game {
    Random rand;
    final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4; 
    
    public void deployShip(Ship ship, int[][] grid, int rows, int cols) {
        rand = new Random();
        boolean cont = true, emptySquare = true;
        int direction;
        int x, y; // Col , Row

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

}
