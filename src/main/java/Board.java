package main.java;

public class Board {
    private AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();
    private Tile[][] grid;
    private int length;
    private int width;

    public Board() {
        this.grid = new Tile[10][10]; // change back
        this.length = adaShipConfig.getBoard_length();
        this.width = adaShipConfig.getBoard_width();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                grid[row][col] = new OceanTile();
            }
        }
    }

    public void render() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                grid[row][col].render();
            }
            System.out.print("\n");
        }
    }
}
