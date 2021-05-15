package at.aau.risiko;

public class StateStrenghten {

    Continent continent = new Continent();
    Country country = new Country();

    boolean bonus = continent.isBonus();
    Country[] countries = continent.getCountries();

    private int maxArmys(boolean bonus, Country[] cs)
    {
        int numOfCountries = cs.length;
        int armys = numOfCountries / 3;

        if(bonus ==true)
        {
            armys = armys + 3;
        }
        return armys;
    }

    

}
