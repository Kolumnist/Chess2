package de.hhn.it.devtools.apis.ttrpgsheets;

public class StatDescriptor {
    private StatType statType;
    private int value;

    public StatDescriptor(StatType statType, int value) {
        this.statType = statType;
        this.value = value;
    }

    public StatType getStatType() {
        return statType;
    }

    public void setStatType(StatType statType) {
        this.statType = statType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
