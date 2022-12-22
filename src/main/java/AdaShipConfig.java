package main.java;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

// Singleton Class
class AdaShipConfig {
    
    private static AdaShipConfig config_instance = null;

    private static int board_rows, board_cols, gameState; 

    // Tile states
    static final int OCEAN = 0, SHIP = 1, HIT = 2, MISS = 4; // Add bombs/sharks...

    // Ship facing directions
    static final String NORTH = "NORTH", SOUTH = "SOUTH", EAST = "EAST", WEST = "WEST";

    // Game states
    static final int NOT_STARTED = 0, PLAYER_TURN = 1, ENEMY_TURN = 2, WIN = 3, LOSS = 4, ENEMY_WIN = 4, ENEMY_LOSS = 3;

    // Frequently used colours
    static final Color OCEAN_COLOR = new Color(6,66,115), FIRING_COLOR = Color.cyan, BUTTON_COLOR = Color.black, HIT_COLOR = Color.red, MISS_COLOR = Color.white, SHIP_COLOR = Color.yellow;

    private static int[][] grid, enemyGrid;

    private ArrayList<Ship> fleet, enemyFleet;

    private FiringPanel shipPanel, enemyShipPanel;

    private Game game;

    public AdaShipConfig(int board_rows, int board_cols) {
        AdaShipConfig.board_rows = board_rows;
        AdaShipConfig.board_cols = board_cols;
        AdaShipConfig.gameState = AdaShipConfig.NOT_STARTED;
        AdaShipConfig.grid = new int[board_rows][board_cols];
        AdaShipConfig.enemyGrid = new int[board_rows][board_cols];
        fleet = new ArrayList<>();
        enemyFleet = new ArrayList<>();
    }

    // Gets the singleton instance
    public static AdaShipConfig getInstance() {
        if (config_instance == null) {
            config_instance = new AdaShipConfig(AdaShipConfig.board_rows, AdaShipConfig.board_cols);
        }
        return config_instance;
    }

    // Returns the dimension object
    public static Dimension getDimension(int width, int height) {
        return new Dimension(width, height);
    }

    // Returns default FlowLayout
    public static FlowLayout getFlowLayout() {
        return new FlowLayout();
    }

    // Returns custom FlowLayout
    public static FlowLayout getFlowLayout(int align, int hgap, int vgap) {
        return new FlowLayout(align, hgap, vgap);
    }

    // Returns BorderLayout
    public static BorderLayout getBorderLayout() {
        return new BorderLayout();
    }

    // Returns custom font
    public static Font getFont(String name, int style, int size) {
        return new Font(name, style, size);
    }

    // Getters and Setters
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

    // Adds a new ship to the fleet array list
    public void addShip(Ship ship) {
        this.fleet.add(ship);
    }

    // Adds a new ship to the enemy fleet array list
    public void addEnemyShip(Ship ship) {
        this.enemyFleet.add(ship);
    }

    public FiringPanel getFiringPanel() {
        return this.shipPanel;
    }

    public void setFiringPanel(FiringPanel shipPanel) {
        this.shipPanel = shipPanel;
    }

    public FiringPanel getEnemyFiringPanel() {
        return this.enemyShipPanel;
    }

    public void setEnemyFiringPanel(FiringPanel shipPanel) {
        this.enemyShipPanel = shipPanel;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
