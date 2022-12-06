package main.java;

public class AdaShip {
    public static void main(String args[]) {
        //Initialise initialise = new Initialise();
        //initialise.parseConfig();

        //Board board = new Board();
        //board.render();
        AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();
        BoardFrame boardFrame = new BoardFrame(adaShipConfig);
        boardFrame.render();
    }
}
