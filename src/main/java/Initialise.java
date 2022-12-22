package main.java;

import java.io.FileNotFoundException;

// Set-up class
public class Initialise {
    private AdaShipConfig adaShipConfig;
    private Game game;

    public Initialise() {
        parseConfig();
        adaShipConfig = AdaShipConfig.getInstance();
        this.game = new Game(adaShipConfig);
        adaShipConfig.setGame(this.game);
    }

    // Instantiates the file parser class and reads in the given file 
    public void parseConfig() {
        FileParser fileParser;
        try {
            fileParser = new FileParser("config\\adaship_config.ini");
            fileParser.readConfig();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }

    // Sets up the players and enemies ship positions randomly
    public void setup() {
        for (int i = 0; i < adaShipConfig.getFleet().size(); i++) {
            game.deployShipRandom(adaShipConfig.getFleet().get(i), adaShipConfig.getGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols());
            game.deployShipRandom(adaShipConfig.getEnemyFleet().get(i), adaShipConfig.getEnemyGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols());
        }
    }

    // Launches the menu
    public void launch() {
        game.runMenu();
    }
}
