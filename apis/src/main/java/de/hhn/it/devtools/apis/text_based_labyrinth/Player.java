package de.hhn.it.devtools.apis.text_based_labyrinth;


import java.util.HashMap;

/**
 * The player does things.
 */
public class Player {


    private final String name;
    private HashMap<String, KeyItem> inventory;


    public Player(String name){
        this.name = name;
        inventory = new HashMap<>();
    }


    public void addItem(KeyItem item) {
        inventory.put(item.getName(), item);
    }

    public void removeItem(String itemName) {
        inventory.remove(itemName);
    }

    public Item getItem(String itemName) {
        return inventory.get(itemName);
    }

    public String getName() {
        return name;
    }
}
