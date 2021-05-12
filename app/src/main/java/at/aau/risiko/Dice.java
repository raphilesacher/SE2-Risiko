package at.aau.risiko;

public class Dice {
    private int eyeNumber;
    private String type;

    public Dice(int eyeNumber, String type) {
        this.eyeNumber = eyeNumber;
        this.type = type;
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
