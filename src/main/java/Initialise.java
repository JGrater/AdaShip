package main.java;

import java.io.FileNotFoundException;

public class Initialise {
    
    AdaShipConfig adaShipConfig;
    BoardFrame boardFrame;
    Game gameplay;

    public Initialise() {
        parseConfig();
        adaShipConfig = AdaShipConfig.getInstance();
        this.gameplay = new Game(adaShipConfig);
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
            gameplay.deployShip(adaShipConfig.getFleet().get(i), adaShipConfig.getGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols());
            gameplay.deployShip(adaShipConfig.getEnemyFleet().get(i), adaShipConfig.getEnemyGrid(), adaShipConfig.getBoard_rows(), adaShipConfig.getBoard_cols());
        }
    }

    public void launch() {
        boardFrame = new BoardFrame(adaShipConfig);
        boardFrame.render();
    }
}
