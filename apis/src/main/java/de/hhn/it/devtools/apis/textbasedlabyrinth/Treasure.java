package de.hhn.it.devtools.apis.textbasedlabyrinth;

public class Treasure extends Item {

    private int scorePoint;

    public Treasure(int id, String name, String info) {
        super(id, name, info);
        this.scorePoint = 1;
        super.isTreasure = true;
    }
}
