package main.java;

import java.util.Random;

public class Game {
    Random rand;
    final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4; 
    
    public void deployShip(int lengthOfShip, int[][] grid) {
        
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
                if (y + lengthOfShip <= 9) {
                    for (int i = y; i < y + lengthOfShip; i++) {
                        if (grid[i][x] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = y; i < y + lengthOfShip; i++) {
                            grid[i][x] = AdaShipConfig.SHIP;
                        }
                        return;
                    }
                }
            } else if (direction == DOWN) {
                if (y - lengthOfShip >= 0) {
                    for (int i = y; i > y - lengthOfShip; i--) {
                        if (grid[i][x] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = y; i > y - lengthOfShip; i--) {
                            grid[i][x] = AdaShipConfig.SHIP;
                        }
                        return;
                    }
                }
            } else if (direction == LEFT) {
                if (x + lengthOfShip <= 9) {
                    for (int i = x; i < x + lengthOfShip; i++) {
                        if (grid[y][i] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = x; i < x + lengthOfShip; i++) {
                            grid[y][i] = AdaShipConfig.SHIP;
                        }
                        return;
                    }
                }
            } else if (direction == RIGHT) {
                if (x - lengthOfShip >= 0) {
                    for (int i = x; i > x - lengthOfShip; i--) {
                        if (grid[y][i] != AdaShipConfig.OCEAN) {
                            emptySquare = false;
                        }
                    }
                    if (emptySquare) {
                        for (int i = x; i > x - lengthOfShip; i--) {
                            grid[y][i] = AdaShipConfig.SHIP;
                        }
                        return;
                    }
                } 
            }
            
        }
    }

}
