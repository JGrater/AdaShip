package main.java;

public abstract class Tile {
    private String colour;
    private String clear = "\u001B[0m";

    public String getColour() {
        return this.colour;
    }

    public void render() {
        System.out.print(getColour() + "   " + clear);
    }

}
