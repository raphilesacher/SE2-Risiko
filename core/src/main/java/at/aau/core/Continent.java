package at.aau.core;

public class Continent {

    private String name;
    private int countries;
    private int bonus;

    public Continent(String name) {
        this.name = name;
        this.countries = 0;
        this.bonus = 0;
    }


    // Getters & Setters:

    public String getName() {
        return name;
    }

    public int getCountries() {
        return countries;
    }

    public int getBonus() {
        return bonus;
    }

}
