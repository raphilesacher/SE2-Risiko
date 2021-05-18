package at.aau.risiko;

import android.graphics.Color;

public class Country {

    Country[] neighbors;
    String name;
    Color color;
    int armys;
    Continent continent;


    public Country[] getNeighbors() {
        return neighbors;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getArmys() {
        return armys;
    }

    public void setArmys(int armys) {
        this.armys = armys;
    }

    public Continent getContinent() {
        return continent;
    }
}
