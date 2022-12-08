package main.java;

import java.util.ArrayList;

class AdaShipConfig {
    
    private static AdaShipConfig config_instance = null;

    private static int board_rows, board_cols, gameState; 

    static final int OCEAN = 0, SHIP = 1, HIT = 2, MISS = 4; // Add bombs/sharks...

    static final int NOT_STARTED = 0, PLAYER_TURN = 1, ENEMY_TURN = 2, WIN = 3, LOSS = 4, ENEMY_WIN = 4, ENEMY_LOSS = 3;

    private static int[][] grid, enemyGrid;

    private ArrayList<Ship> fleet, enemyFleet;

    public AdaShipConfig(int board_rows, int board_cols) {
        AdaShipConfig.board_rows = board_rows;
        AdaShipConfig.board_cols = board_cols;
        AdaShipConfig.gameState = AdaShipConfig.NOT_STARTED;
        AdaShipConfig.grid = new int[board_rows][board_cols]; //Change back
        AdaShipConfig.enemyGrid = new int[board_rows][board_cols];
        this.fleet = new ArrayList<>();
        this.enemyFleet = new ArrayList<>();
    }

    public static AdaShipConfig getInstance() {
        if (config_instance == null) {
            config_instance = new AdaShipConfig(AdaShipConfig.board_rows, AdaShipConfig.board_cols);
        }
        return config_instance;
    }

    public int getBoard_rows() {
        return AdaShipConfig.board_rows;
    }

    public int getBoard_cols() {
        return AdaShipConfig.board_cols;
    }

    public void setBoard_rows(int rows) {
        AdaShipConfig.board_rows = rows;
        AdaShipConfig.grid = new int[rows][AdaShipConfig.board_cols];
        AdaShipConfig.enemyGrid = new int[rows][AdaShipConfig.board_cols];
    }

    public void setBoard_cols(int cols) {
        AdaShipConfig.board_cols = cols;
        AdaShipConfig.grid = new int[AdaShipConfig.board_rows][cols];
        AdaShipConfig.enemyGrid = new int[AdaShipConfig.board_rows][cols];
    }

    public int getGameState() {
        return AdaShipConfig.gameState;
    }

    public void setGameState(int state) {
        AdaShipConfig.gameState = state;
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
