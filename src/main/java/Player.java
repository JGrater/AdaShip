package main.java;

// Player class
public class Player {
    private AdaShipConfig adaShipConfig;

    public Player(AdaShipConfig adaShipConfig) {
        this.adaShipConfig = adaShipConfig;
    }

    // Most of players interactions are completed through other classes, except for completing turn
    public void completeTurn() {
        adaShipConfig.getFiringPanel().enableButtons();
    }

}
