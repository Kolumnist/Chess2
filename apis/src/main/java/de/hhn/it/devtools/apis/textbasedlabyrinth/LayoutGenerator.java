package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;


public class LayoutGenerator {

    private ArrayList<Room> allRooms;
    private Map map;
    private ArrayList<Layout> allLayouts;
    private Room startRoom;
    public int maxRoomCount = 13;

    public LayoutGenerator(Map map) {
        this.map = map;
        this.allRooms = new ArrayList<>();

        String exampleDescription = "A dark, cold room.";
        int j = 0;
        while (allRooms.size() <= maxRoomCount) {
            Room newRoom = new Room(j, exampleDescription);
            this.allRooms.add(newRoom);
            j++;
        }
        this.startRoom = allRooms.get(0);

    }

    public ArrayList<Room> setMapLayout() {
        if (map.equals(Map.Grave_of_the_Mad_King)) {
            startRoom.setNextDoorRoom(allRooms.get(1), false, true, false);
            allRooms.get(1).setNextDoorRoom(allRooms.get(2), false, true, false);
            allRooms.get(1).setNextDoorRoom(allRooms.get(3), false, false, true);
            startRoom.setNextDoorRoom(allRooms.get(4), false, false, true);
            allRooms.get(4).setNextDoorRoom(allRooms.get(5), false, false, true);
            allRooms.get(5).setNextDoorRoom(allRooms.get(6), false, false, true);
            allRooms.get(6).setNextDoorRoom(allRooms.get(7), true, false, false);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8), false, false, false);
            startRoom.setNextDoorRoom(allRooms.get(9), true, false, false);
            allRooms.get(9).setNextDoorRoom(allRooms.get(10), true, false, false);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11), true, false, false);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12), false, false, true);
        }
        else if (map.equals(Map.Ancient_Dungeon)) {
            startRoom.setNextDoorRoom(allRooms.get(1), false, true, false);
            allRooms.get(1).setNextDoorRoom(allRooms.get(4),false, true, false);
            allRooms.get(4).setNextDoorRoom(allRooms.get(5),false, false, true);
            allRooms.get(5).setNextDoorRoom(allRooms.get(7),false, false, true);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8),true, false, false);
            allRooms.get(8).setNextDoorRoom(allRooms.get(6),true, false, false);
            startRoom.setNextDoorRoom(allRooms.get(2), false, false, true);
            allRooms.get(2).setNextDoorRoom(allRooms.get(6),false, false, true);
            allRooms.get(6).setNextDoorRoom(allRooms.get(9),true, false, false);
            startRoom.setNextDoorRoom(allRooms.get(3),true, false, false);
            allRooms.get(3).setNextDoorRoom(allRooms.get(10),true, false, false);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11),false, false, true);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12),true, false, false);

        }
        else if (map.equals(Map.Unknown_Sewers)) {
            startRoom.setNextDoorRoom(allRooms.get(1), false, false, true);
            allRooms.get(1).setNextDoorRoom(allRooms.get(2), false, false, true);
            allRooms.get(2).setNextDoorRoom(allRooms.get(3), true, false, false);
            allRooms.get(2).setNextDoorRoom(allRooms.get(4), false, true, false);
            allRooms.get(2).setNextDoorRoom(allRooms.get(5), false, false, true);
            allRooms.get(5).setNextDoorRoom(allRooms.get(6), false, false, true);
            allRooms.get(6).setNextDoorRoom(allRooms.get(7), false, false, true);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8), false, true, false);
            allRooms.get(7).setNextDoorRoom(allRooms.get(9), true, false, false);
            allRooms.get(7).setNextDoorRoom(allRooms.get(10), false, false, true);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11), false, false, true);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12), false, false, true);
        }
        return allRooms;
    }
}
