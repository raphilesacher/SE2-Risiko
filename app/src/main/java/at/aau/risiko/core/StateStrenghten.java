/*
package at.aau.risiko.core;

import at.aau.risiko.core.Continent;
import at.aau.risiko.core.Country;

public class StateStrenghten {

    Continent continent = new Continent();
    Country country = new Country();

    int bonus = continent.getBonus();
    Country[] countries = continent.getCountries();

    public int getMaxArmys() {
        return maxArmys;
    }

    int maxArmys = maxArmys(bonus, countries);

    private int maxArmys(int bonus, Country[] cs)
    {
        int numOfCountries = cs.length;
        int armys = numOfCountries / 3;

        if(bonus > 0)
        {
            armys = armys + bonus;
        }
        return armys;
    }

    private int strenghtenCountry(int choosen)
    {
        int actualArmys = country.getArmys();
        int newArmys = actualArmys + choosen;
        country.setArmys(newArmys);
        
        maxArmys = maxArmys - choosen;
        return maxArmys;
    }

}
*/