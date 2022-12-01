package main.java;

public class Ship {
    private int health;
    private String type;
    private boolean destroyed;

    public Ship(String type, int health) {
        this.type = type;
        this.health = health;
        this.destroyed = false;
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

}
