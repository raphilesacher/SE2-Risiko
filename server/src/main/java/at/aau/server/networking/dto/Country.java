package at.aau.server.networking.dto;


import java.util.ArrayList;
import java.util.List;

public class Country {

    private String name;

    public void setColor(int color) {
        this.color = color;
    }

    private int color;
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

    public int getArmies() {
        return armies;
    }

    public List<Country> getNeighbors() {
        return neighbors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public void addNeighbor(Country neighbor) {
        this.neighbors.add(neighbor);
    }
}
