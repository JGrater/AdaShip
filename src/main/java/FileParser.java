package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {

    private File fileToParse;

    public FileParser(String path) throws FileNotFoundException {
        this.fileToParse = new File(path);
    }

    public void readConfig() {

        AdaShipConfig adaShipConfig = AdaShipConfig.getInstance();

        try {
            Scanner readScanner = new Scanner(fileToParse);
            while (readScanner.hasNextLine()) {
                String data = readScanner.nextLine();
                
                String[] splitData = data.split(":");

                if (splitData[0].equalsIgnoreCase("board")) {
                    String[] dimensions = splitData[1].split("x");
                    adaShipConfig.setBoard_length(Integer.parseInt(dimensions[0]));
                    adaShipConfig.setBoard_width(Integer.parseInt(dimensions[1]));
                }

                if (splitData[0].equalsIgnoreCase("boat")) {
                    String[] boats = splitData[1].split(",");
                    System.out.println("Type: "+boats[0] + ", Size: " + boats[1]);
                }

            }
            readScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
