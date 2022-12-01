package main.java;

import java.util.ArrayList;

class AdaShipConfig {
    
    private static AdaShipConfig config_instance = null;

    private static int board_length, board_width; 

    private ArrayList<String[]> fleet = new ArrayList<>();

    public AdaShipConfig() {

    }

    public AdaShipConfig(int board_length, int board_width) {
        AdaShipConfig.board_length = board_length;
        AdaShipConfig.board_width = board_width;
    }


    public static AdaShipConfig getInstance() {
        if (config_instance == null) {
            config_instance = new AdaShipConfig();
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

    public ArrayList<String[]> getFleet() {
        return this.fleet;
    }

    public void displayFleet() {
        System.out.println("\nYour fleet:");
        for (String[] ship : this.fleet) {
            System.out.println("> " + ship[0] + ", with a health of " + ship[1]);
        }
    }

    public void addShip(String[] ship) {
        this.fleet.add(ship);
    }

}
