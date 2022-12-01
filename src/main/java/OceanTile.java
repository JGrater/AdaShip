package main.java;

public class OceanTile extends Tile {
    
    private String colour;
    
    public OceanTile() {
        this.colour = "\u001B[44m";
    } 

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
}