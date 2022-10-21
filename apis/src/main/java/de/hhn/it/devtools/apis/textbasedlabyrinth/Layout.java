package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Layout defines the dungeon layout of the game and is always random,
 * however there are restricted possibilities of different layouts.
 */
public class Layout {

    public List<Room> allRooms;
    public int maxRoomCount = 7;
    public List<Layout> allLayouts;
    public Room startRoom;
    public Player player;

    /**
     * Constructor of layout class
     * @param player player of the game
     */
    public Layout(Player player) {

        String exampleDescription = "A dark, cold room.";
        this.player = player;
        int roomCount = 1;
        // Random unique number list
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        for (int i = 1; i < 3; i++) randomNumbers.add(i);
        Collections.shuffle(randomNumbers);

        // a Random List with 2 false values and 1 true value
        ArrayList<Boolean> randomBoolean = new ArrayList<>();
        randomBoolean.add(true);
        randomBoolean.add(false);
        randomBoolean.add(false);
        Collections.shuffle(randomBoolean);

        // create a List for all the Rooms generated
        this.allRooms = new ArrayList<>();

        // create as many rooms as maxRoomCount allows
        int j = 0;
        while(allRooms.size() <= maxRoomCount) {
            Room newRoom = new Room(j, exampleDescription);
            this.allRooms.add(newRoom);
            j++;
        }

        // take the first room generated as the starting room
        this.startRoom = allRooms.get(0);
        // place player inside
        player.setCurrentRoomOfPlayer(startRoom);

        // West side
        startRoom.setNextDoorRoom(allRooms.get(roomCount), false, true, false);
        roomCount++;
        if(randomNumbers.get(0) > 1) {
            assignRandomNextDoorRoom(allRooms.get(roomCount), allRooms.get(roomCount + 1));
            roomCount++;
            if(randomNumbers.get(0) > 2) {
                assignRandomNextDoorRoom(allRooms.get(roomCount), allRooms.get(roomCount + 1));
                roomCount++;
            }
        }

        // East side
        startRoom.setNextDoorRoom(allRooms.get(roomCount), true, false, false);
        roomCount++;
        if (randomNumbers.get(1) > 1) {
            assignRandomNextDoorRoom(allRooms.get(roomCount), allRooms.get(roomCount + 1));
            roomCount++;
            if (randomNumbers.get(1) > 2) {
                assignRandomNextDoorRoom(allRooms.get(roomCount), allRooms.get(roomCount + 1));
                roomCount++;
            }
        }

        // North side
        startRoom.setNextDoorRoom(allRooms.get(roomCount), false, false, true);
        roomCount++;
        if (randomNumbers.get(2) > 1) {
            assignRandomNextDoorRoom(allRooms.get(roomCount), allRooms.get(roomCount + 1));
            roomCount++;
            if (randomNumbers.get(2) > 2) {
                assignRandomNextDoorRoom(allRooms.get(roomCount), allRooms.get(roomCount + 1));
                //roomCount++;
            }
        }
    }

    /**
     * Assigns a room on a random direction to a room depending on what is not already assigned
     * @param room Room that gets a new Room assigned
     * @param roomToBeAssigned room to be assigned
     */
    public void assignRandomNextDoorRoom(Room room, Room roomToBeAssigned){

        ArrayList<Boolean> randomBoolean = new ArrayList<>();
        randomBoolean.add(true);
        randomBoolean.add(false);
        randomBoolean.add(false);
        Collections.shuffle(randomBoolean);

        if(room.isNorthAssigned.equals(true)) {
            room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), randomBoolean.get(1), false);
        }
        else if(room.isEastAssigned.equals(true)) {
            room.setNextDoorRoom(roomToBeAssigned, false, randomBoolean.get(0), randomBoolean.get(1));
        }
        else if(room.isSouthAssigned.equals(true)) {
            room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), randomBoolean.get(1), randomBoolean.get(2));
        }
        else {
            room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), false, randomBoolean.get(1));
        }
    }
}
