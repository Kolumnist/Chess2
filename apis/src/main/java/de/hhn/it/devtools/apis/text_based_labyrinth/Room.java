package de.hhn.it.devtools.apis.text_based_labyrinth;


import java.util.HashMap;
import java.util.Set;

public class Room {


    private HashMap<Integer, Item> items;
    private int roomId;



    public Room(int id) {
        this.roomId = id;

    }


    public void addItem(Item item) {
        items.put(item.getItemId(), item);

    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public int getRoomId() {
        return roomId;
    }



}
