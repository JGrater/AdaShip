package main.java;

import java.util.ArrayList;
import java.util.Arrays;

// Ship object, makes up the fleet array list
public class Ship {
    private int health;
    private String type, direction;
    private boolean destroyed;
    private ArrayList<int[]> coords;

    public Ship(String type, int health) {
        this.type = type;
        this.health = health;
        this.destroyed = false;
        this.coords = new ArrayList<>();
    }

    // Getters and Setters
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

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    // Adds coords to an array of length equal to ships size/health
    public void addCoords(int[] coord) {
        this.coords.add(coord);
    }

    // Returns true if the given coordinates lie on the ships position/tile, and lowers the ships health by one
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

    // Returns true if the ship is at 0 health, and sets destroyed to true
    public boolean checkDestroyed() {
        if (this.health == 0) {
            this.destroyed = true;
        }
        return this.destroyed;
    }

}
