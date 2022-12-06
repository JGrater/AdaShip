package main.java;

import java.util.ArrayList;

class AdaShipConfig {
    
    private static AdaShipConfig config_instance = null;

    private static int board_length, board_width; 

    static final int OCEAN = 0, SHIP = 1, HIT = 2, MISS = 4; // Add bombs/sharks...

    private static int[][] grid, enemyGrid;

    private ArrayList<Ship> fleet, enemyFleet;

    public AdaShipConfig(int board_length, int board_width) {
        AdaShipConfig.board_length = board_length;
        AdaShipConfig.board_width = board_width;
        AdaShipConfig.grid = new int[10][10]; // Change
        AdaShipConfig.enemyGrid = new int[10][10];
        this.fleet = new ArrayList<>();
        this.enemyFleet = new ArrayList<>();
    }


    public static AdaShipConfig getInstance() {
        if (config_instance == null) {
            config_instance = new AdaShipConfig(board_length, board_width);
        }
        return config_instance;
    }

    public int getBoard_length() {
        return AdaShipConfig.board_length;
    }

    public int getBoard_width() {
        return AdaShipConfig.board_width;
    }

    public void setBoard_length(int length) {
        AdaShipConfig.board_length = length;
    }

    public void setBoard_width(int width) {
        AdaShipConfig.board_width = width;
    }

    public ArrayList<Ship> getFleet() {
        return this.fleet;
    }

    public ArrayList<Ship> getEnemyFleet() {
        return this.enemyFleet;
    }

    public int[][] getGrid() {
        return AdaShipConfig.grid;
    }

    public void setGrid(int[][] grid) {
        AdaShipConfig.grid = grid;
    }

    public int[][] getEnemyGrid() {
        return AdaShipConfig.enemyGrid;
    }

    public void setEnemyGrid(int[][] grid) {
        AdaShipConfig.enemyGrid = grid;
    }

    public void addShip(Ship ship) {
        this.fleet.add(ship);
    }

    public void addEnemyShip(Ship ship) {
        this.enemyFleet.add(ship);
    }
}
