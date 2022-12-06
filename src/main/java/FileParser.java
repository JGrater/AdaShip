package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {

    private File fileToParse;
    private Scanner readScanner;
    private String data;
    private String[] splitData, dimensions, ship;

    public FileParser(String path) throws FileNotFoundException {
        this.fileToParse = new File(path);
    }

    public int toInt(String str) {
        return Integer.parseInt(str);
    }

    public void readConfig() {

        AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();

        try {
            readScanner = new Scanner(fileToParse);
            while (readScanner.hasNextLine()) {
                data = readScanner.nextLine();
                
                splitData = data.split(":");

                if (splitData[0].equalsIgnoreCase("board")) {
                    dimensions = splitData[1].split("x");
                    adaShipConfig.setBoard_length(toInt(dimensions[0]));
                    adaShipConfig.setBoard_width(toInt(dimensions[1]));
                }

                if (splitData[0].equalsIgnoreCase("boat")) {
                    ship = splitData[1].split(",");
                    adaShipConfig.addShip(new Ship(ship[0], toInt(ship[1])));
                    adaShipConfig.addEnemyShip(new Ship(ship[0], toInt(ship[1])));
                }

            }
            readScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
