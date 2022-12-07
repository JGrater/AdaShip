package main.java;

import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    private int health;
    private String type;
    private boolean destroyed;
    private ArrayList<int[]> coords;

    public Ship(String type, int health) {
        this.type = type;
        this.health = health;
        this.destroyed = false;
        this.coords = new ArrayList<>();
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public ArrayList<int[]> getCoords() {
        return this.coords;
    }

    public void addCoords(int[] coord) {
        this.coords.add(coord);
    }

    public void displayCoords() {
        for(int i = 0; i < this.coords.size(); i++) {
            System.out.println("Row: "+this.coords.get(i)[0]+ "       Col: " +this.coords.get(i)[1]);
        }
    }

    public boolean checkHit(int[] coords) {
        boolean hit = false;
        for(int i = 0; i < this.coords.size(); i++) {
            if (Arrays.equals(coords, this.coords.get(i))) {
                this.health -= 1;
                hit = true;
            }
        }
        return hit;
    }

    public boolean checkDestroyed() {
        if (this.health == 0) {
            this.destroyed = true;
        }
        return this.destroyed;
    }

}
