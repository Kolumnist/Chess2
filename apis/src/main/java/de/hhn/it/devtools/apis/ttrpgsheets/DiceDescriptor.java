package de.hhn.it.devtools.apis.ttrpgsheets;

public class DiceDescriptor {
    private DiceType diceType;
    private int result;

    public DiceDescriptor(DiceType diceType, int result) {
        this.diceType = diceType;
        this.result = result;
    }

    public DiceType getDiceType() {
        return diceType;
    }

    public void setDiceType(DiceType diceType) {
        this.diceType = diceType;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Dice type = " + diceType +
                ", Result = " + result;
    }
}
