package de.hhn.it.devtools.apis.text_based_labyrinth;

public class KeyItem extends Item {

    private final String name;


    public KeyItem(int id, String name) {
        super(id);
        this.name = name;

    }


    public String getName() {
        return name;
    }
}
