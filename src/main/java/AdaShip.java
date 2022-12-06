package main.java;

public class AdaShip {
    public static void main(String args[]) {
        Initialise initialise = new Initialise();
        initialise.parseConfig();
        initialise.setup();
        initialise.launch();

        
    }
}
