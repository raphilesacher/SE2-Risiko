package at.aau.risiko;

import java.util.Random;

public class Dice {
    /**
     * int eyeNumber is needed to know which side of the dice should be shown
     * String type = attacker || defender
     * this variable is needed to know on what color the dice should have
     */
    private int eyeNumber;
    private String type;

    public Dice(String type) {
        this.type = type;
    }

    public int diceRoll() {
        Random rand = new Random();
        eyeNumber = rand.nextInt(5)+1;
        return eyeNumber;
    }
    public void setEyeNumber(int eyeNumber) {
        this.eyeNumber = eyeNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEyeNumber() {
        return eyeNumber;
    }

    public String getType() {
        return type;
    }


}
