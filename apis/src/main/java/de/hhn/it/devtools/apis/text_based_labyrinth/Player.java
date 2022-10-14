package de.hhn.it.devtools.apis.text_based_labyrinth;


import de.hhn.it.devtools.apis.text_based_labyrinth.exceptions.NoSuchItemFoundException;

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

    public void removeItem(String itemName) throws NoSuchItemFoundException {
        if (itemName.isEmpty() || itemName.isBlank()) {
            throw new NoSuchItemFoundException("Name of the item cannot be zero or blank.");
        }
        if (!inventory.containsKey(itemName)) {
            throw new NoSuchItemFoundException();
        }
        inventory.remove(itemName);
    }

    public Item getItem(String itemName) throws NoSuchItemFoundException {
        if (itemName.isEmpty() || itemName.isBlank()) {
            throw new NoSuchItemFoundException("Name of the item cannot be zero or blank.");
        }
        if (!inventory.containsKey(itemName)) {
            throw new NoSuchItemFoundException();
        }
        return inventory.get(itemName);
    }

    public String getName() {
        return name;
    }
}
