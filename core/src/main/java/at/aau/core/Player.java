package at.aau.core;

import java.util.HashMap;

public class Player {

    private final String name;
    private final Integer color;
    private final HashMap<Integer, Country> occupied;
    private int available;
    private HandDeck handDeck;

    //Constructor

    public Player(String name, Integer color) {
        this.name = name;
        this.color = color;
        this.available = 0;
        this.occupied = new HashMap<>();
    }

    // Getters & Setters:

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public HashMap<Integer, Country> getOccupied() {
        return occupied;
    }

}
