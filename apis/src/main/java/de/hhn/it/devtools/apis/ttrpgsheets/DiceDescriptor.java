package de.hhn.it.devtools.apis.ttrpgsheets;

public class DiceDescriptor {
    private int size;
    private int result;

    public DiceDescriptor(int size, int result) {
        this.size = size;
        this.result = result;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
