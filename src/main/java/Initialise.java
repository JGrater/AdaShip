package main.java;

import java.io.FileNotFoundException;

public class Initialise {
    
    AdaShipConfig adaShipConfig;
    BoardFrame boardFrame;
    Game game;

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
        adaShipConfig = AdaShipConfig.getInstance();
        game = new Game();
        for (int i = 0; i < adaShipConfig.getFleet().size(); i++) {
            game.deployShip(adaShipConfig.getFleet().get(i).getHealth(), adaShipConfig.getGrid());
            game.deployShip(adaShipConfig.getEnemyFleet().get(i).getHealth(), adaShipConfig.getEnemyGrid());
        }
    }

    public void launch() {
        boardFrame = new BoardFrame(adaShipConfig);
        boardFrame.render();
    }
}
