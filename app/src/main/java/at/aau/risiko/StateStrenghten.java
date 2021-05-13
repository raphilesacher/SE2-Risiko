package at.aau.risiko;

public class StateStrenghten {

    private int calculateNumberOfStrenght(int bonus, Country[] cs)
    {
        int numberOfCountries = cs.length;

        int numberOfStrength = numberOfCountries / 3;

        return numberOfStrength;

    }

}
