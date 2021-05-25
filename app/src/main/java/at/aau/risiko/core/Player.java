package at.aau.risiko.core;

import android.graphics.Color;

import java.util.HashMap;

public class Player {

    private String name;
    private Color color;
    private int available;
    HashMap<Integer, Country> occupied;

    private HandDeck handDeck;

    //Constructor

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.available = 0;
        this.occupied = new HashMap<>();
    }

    // Getters & Setters:

    public String getName() {
        return name;
    }

    public Color getColor() {
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
