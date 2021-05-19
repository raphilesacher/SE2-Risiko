package at.aau.risiko.core;

import android.view.View;

import at.aau.risiko.Continent;
import at.aau.risiko.Country;

public class DraftState extends State {

    at.aau.risiko.Continent continent = new Continent();
    at.aau.risiko.Country country = new at.aau.risiko.Country();
    at.aau.risiko.Country[] countries = continent.getCountries();


    int bonus = continent.getBonus();
    int maxArmys = maxArmys(bonus, countries);

    public DraftState(Game game, int maxArmys) {
        super(game);
        this.maxArmys = maxArmys;
    }

    @Override
    public void handleInput(View view) {

        // TODO Implement method Calls for Frontend
    }

    public int getMaxArmys() {
        return maxArmys;
    }

    private int maxArmys(int bonus, Country[] cs) {
        int numOfCountries = cs.length;
        int armys = numOfCountries / 3;

        if (bonus > 0) {
            armys = armys + bonus;
        }
        return armys;
    }

    private int strenghtenCountry(int choosen) {
        int actualArmys = country.getArmys();
        int newArmys = actualArmys + choosen;
        country.setArmys(newArmys);

        maxArmys = maxArmys - choosen;
        return maxArmys;
    }

    @Override
    public void changeState() {
        // TODO Implement swith to Attack

    }
}
