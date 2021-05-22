package at.aau.risiko.core;

import android.graphics.Color;

import java.util.HashMap;

public class Player {

    private String name;
    private Color color;

    public void setAvailable(int available) {
        this.available = available;
    }

    private int available;

    public HashMap<Integer, Country> getOccupied() {
        return occupied;
    }

    HashMap<Integer, Country> occupied;

    private Card[] cards;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.available = 0;
        this.occupied = new HashMap<Integer, Country>();
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

}
