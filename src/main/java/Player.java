package main.java;

public class Player {
    private AdaShipConfig adaShipConfig;

    public Player(AdaShipConfig adaShipConfig) {
        this.adaShipConfig = adaShipConfig;
    }

    public void completeTurn() {
        adaShipConfig.getFiringPanel().enableButtons();
    }

}
