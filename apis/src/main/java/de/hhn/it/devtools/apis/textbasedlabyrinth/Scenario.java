package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.HashMap;

public class Scenario {


    private final Seed seed;    //The seed currently in use.
    private HashMap<Integer, Room> rooms; //All rooms in the layout.


    public Scenario(Seed seed) {
        this.seed = seed;
    }


    public void generate() {
        //Based on the seed, it will generate different amount of rooms.
    }

    public Seed getSeed() {
        return seed;
    }


}
