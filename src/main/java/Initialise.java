package main.java;

import java.io.FileNotFoundException;

public class Initialise {

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

    }

    public void launch() {

    }
}
