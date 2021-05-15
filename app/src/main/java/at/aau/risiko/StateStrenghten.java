package at.aau.risiko;

public class StateStrenghten {

    Continent continent = new Continent();
    Country country = new Country();

    boolean bonus = continent.isBonus();
    Country[] countries = continent.getCountries();

    int maxArmys = maxArmys(bonus, countries);

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

    private int strenghtenCountry(int choosen)
    {
        System.out.println(maxArmys);
        
        int actualArmys = country.getArmys();
        int newArmys = actualArmys + choosen;
        country.setArmys(newArmys);
        
        maxArmys = maxArmys - choosen;
        return maxArmys;
    }

}
