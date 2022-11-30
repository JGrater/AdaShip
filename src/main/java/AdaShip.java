package main.java;

import java.io.FileNotFoundException;

public class AdaShip {
    public static void main(String args[]) {
        //Initialise initialise = new Initialise();
        //initialise.parseConfig();
        FileParser fileParser;
        try {
            fileParser = new FileParser("config\\adaship_config.ini");
            fileParser.readConfig();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();
        System.out.println("l: " + adaShipConfig.getBoard_length() + ", w: "+ adaShipConfig.getBoard_width());
    }
}
