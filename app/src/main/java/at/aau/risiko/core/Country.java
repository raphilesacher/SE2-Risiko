package at.aau.risiko.core;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private String name;
    private Color color;
    private int armies;
    private List<Country> neighbors;
    // private Continent continent;

    public Country(String name) {
        this.name = name;
        this.armies = 0;
        this.neighbors = new ArrayList<Country>();
    }


    // Getters & Setters:

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getArmies() {
        return armies;
    }

    public List<Country> getNeighbors() {
        return neighbors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public void addNeighbor(Country neighbor) {
        this.neighbors.add(neighbor);
    }
}
