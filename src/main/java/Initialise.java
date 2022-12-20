package main.java;

import java.io.FileNotFoundException;

public class Initialise {
    private AdaShipConfig adaShipConfig;
    private Game game;

    public Initialise() {
        parseConfig();
        adaShipConfig = AdaShipConfig.getInstance();
        this.game = new Game(adaShipConfig);
        adaShipConfig.setGame(this.game);
    }

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

    public void setup() {
        for (int i = 0; i < adaShipConfig.getFleet().size(); i++) {
            game.deployShipRandom(adaShipConfig.getFleet().get(i), adaShipConfig.getGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols());
            game.deployShipRandom(adaShipConfig.getEnemyFleet().get(i), adaShipConfig.getEnemyGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols());
        }
    }

    public void launch() {
        game.runMenu();
    }
}
