package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Layout {

    public List<Room> allRooms;
    public int maxRoomCount = 7;
    public List<Layout> allLayouts;
    public Room startRoom;

    public Layout(){

        int roomCount = 1;
        // Random unique number list
        ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
        for (int i = 1; i < 3; i++) randomNumbers.add(i);
        Collections.shuffle(randomNumbers);

        // create a List for all the Rooms generated
        this.allRooms = new ArrayList<Room>();

        // create as many rooms as maxRoomCount allows
        int j = 0;
        while(allRooms.size() <= maxRoomCount){
            Room newRoom = new Room(j);
            this.allRooms.add(newRoom);
            j++;
        }

        // take the first room generated as the starting room
        this.startRoom = allRooms.get(0);
        // place player inside
        startRoom.setPlayerInside(true);

        // West side

        startRoom.setNextDoorRoom(allRooms.get(roomCount), false, true, false);
        roomCount++;

        if(randomNumbers.get(0) > 1){

            allRooms.get(roomCount).setNextDoorRoom(allRooms.get(roomCount), false, true, false);
            roomCount++;

            if(randomNumbers.get(0) > 2){

                allRooms.get(roomCount).setNextDoorRoom(allRooms.get(roomCount), false, true, false);
                roomCount++;
            }
        }

        // East side

        startRoom.setNextDoorRoom(allRooms.get(roomCount), true, false, false);
        roomCount++;

        if (randomNumbers.get(1) > 1){
            allRooms.get(roomCount).setNextDoorRoom(allRooms.get(roomCount), true, false, false);
            roomCount++;

            if (randomNumbers.get(1) > 2){
                allRooms.get(roomCount).setNextDoorRoom(allRooms.get(roomCount), true, false, false);
                roomCount++;
            }
        }

        // North side

        startRoom.setNextDoorRoom(allRooms.get(roomCount), false, false, true);
        roomCount++;

        if (randomNumbers.get(2) > 1){
            allRooms.get(roomCount).setNextDoorRoom(allRooms.get(roomCount), false, false, true);
            roomCount++;

            if (randomNumbers.get(2) > 2){
                allRooms.get(roomCount).setNextDoorRoom(allRooms.get(roomCount), false, false, true);
                roomCount++;
            }
        }

    }

    public int getRoomWithPlayer(){
        int result = 0;
       for(int i = 0; allRooms.size() >= i; i++)
           if (allRooms.get(i).getPlayerInside().equals(true)) result = allRooms.get(i).getRoomId();
       return result;
    }
}
