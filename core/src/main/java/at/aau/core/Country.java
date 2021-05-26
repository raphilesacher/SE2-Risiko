package at.aau.core;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private final List<Country> neighbors;
    private String name;
    private Integer color;
    private int armies;
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

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public List<Country> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Country neighbor) {
        this.neighbors.add(neighbor);
    }
}
