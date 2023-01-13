package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

import java.util.ArrayList;


public class LayoutGenerator {

    private ArrayList<Room> allRooms;
    private Map map;
    private Seed seed;
    private ArrayList<Layout> allLayouts;
    private Room startRoom;
    private boolean isPrepared;
    public int maxRoomCount = 13;

    public LayoutGenerator(Map map, Seed seed) {
        this.map = map;
        this.seed = seed;
        this.allRooms = new ArrayList<>();
        this.isPrepared = false;


        String exampleDescription = "A dark, cold room.";
        reset();
    }


    public void setLayout(Layout layout) {
        layout.setAllRooms(allRooms);
    }

    private void reset() {
        allRooms.clear();
        String exampleDescription = "A dark, cold room.";
        int j = 0;
        while (allRooms.size() <= maxRoomCount) {
            Room newRoom = new Room(j, exampleDescription);
            this.allRooms.add(newRoom);
            j++;
        }
        this.startRoom = allRooms.get(0);
        isPrepared = true;
    }


    public void generateLayout() {
        if (!isPrepared) {
            reset();
        }


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
        } else if (map.equals(Map.Ancient_Dungeon)) {
            startRoom.setNextDoorRoom(allRooms.get(1), false, true, false);
            allRooms.get(1).setNextDoorRoom(allRooms.get(4), false, true, false);
            allRooms.get(4).setNextDoorRoom(allRooms.get(5), false, false, true);
            allRooms.get(5).setNextDoorRoom(allRooms.get(7), false, false, true);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8), true, false, false);
            allRooms.get(8).setNextDoorRoom(allRooms.get(6), true, false, false);
            startRoom.setNextDoorRoom(allRooms.get(2), false, false, true);
            allRooms.get(2).setNextDoorRoom(allRooms.get(6), false, false, true);
            allRooms.get(6).setNextDoorRoom(allRooms.get(9), true, false, false);
            startRoom.setNextDoorRoom(allRooms.get(3), true, false, false);
            allRooms.get(3).setNextDoorRoom(allRooms.get(10), true, false, false);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11), false, false, true);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12), true, false, false);

        } else if (map.equals(Map.Unknown_Sewers)) {
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
        String exampleDescription = "A dark, cold room.";


        for (Room room : allRooms) {
            room.setDoors();
        }

        if (map.equals(Map.Grave_of_the_Mad_King)) {
            int amountOfPuzzles = 1;
            if (seed.getSeed().get(0) < 5) {
                amountOfPuzzles = 2;
            }

            Item key1 = new Item(1, "ExitKey", "A big, old metal key.");
            allRooms.get(7).getSouthDoor().setPuzzle(key1);
            if (seed.getSeed().get(1) < 5) {
                allRooms.get(3).addItem(key1);
            } else {
                allRooms.get(2).addItem(key1);
            }

            if (amountOfPuzzles >= 2) {
                Item key2 = new Item(2, "Small rusty key", "A small, rusty key.");
                allRooms.get(4).getSouthDoor().setPuzzle(key2);
                if (seed.getSeed().get(1) < 3) {
                    allRooms.get(9).addItem(key2);
                } else if (seed.getSeed().get(1) < 7 && seed.getSeed().get(1) > 3) {
                    allRooms.get(2).addItem(key2);
                } else {
                    allRooms.get(12).addItem(key2);
                }
            }

            int treasureId = 100;
            int amountOfTreasure = 1;

            allRooms.get(7).addItem(new Item(treasureId, "Treasure", "Treasure for Demo."));

        } else if (map.equals(Map.Ancient_Dungeon)) {
            int amountOfPuzzles = 1;
            if (seed.getSeed().get(0) > 4) {
                amountOfPuzzles = 2;
            }
            if (seed.getSeed().get(0) > 8) {
                amountOfPuzzles = 3;
            }

            Item key1 = new Item(1, "ExitKey", "A metal key. It fits into your hand well.");
            allRooms.get(11).getWestDoor().setPuzzle(key1);
            if (seed.getSeed().get(1) < 5) {
                allRooms.get(4).addItem(key1);
            } else {
                allRooms.get(9).addItem(key1);
            }

            if (amountOfPuzzles >= 2) {
                Item key2 = new Item(2, "Small blue key", "Though clearly metal, this key has a blue tint");
                allRooms.get(1).getWestDoor().setPuzzle(key2);
                if (seed.getSeed().get(1) == 0) {
                    allRooms.get(0).addItem(key2);
                } else if (seed.getSeed().get(1) < 5) {
                    allRooms.get(10).addItem(key2);
                } else {
                    allRooms.get(9).addItem(key2);
                }
            }

            if (amountOfPuzzles >= 3) {
                Item key3 = new Item(3, "Metal key",
                        "On closer inspection, this key has a 10 itched into its metal.");
                allRooms.get(10).getEastDoor().setPuzzle(key3);
                if (seed.getSeed().get(1) < 5) {
                    allRooms.get(7).addItem(key3);
                } else {
                    allRooms.get(9).addItem(key3);
                }

            }

            int treasureId = 100;
            int amountOfTreasure = 1;

            allRooms.get(7).addItem(new Item(treasureId, "Treasure", "Treasure for Demo."));


        } else if (map.equals(Map.Unknown_Sewers)) {
            int amountOfPuzzles = 1;
            if (seed.getSeed().get(0) < 5) {
                amountOfPuzzles = 2;
            }

            Item key1 = new Item(1, "ExitKey", "A big, old metal key. It feels wet.");
            allRooms.get(7).getNorthDoor().setPuzzle(key1);
            if (seed.getSeed().get(1) < 5) {
                allRooms.get(3).addItem(key1);
            } else {
                allRooms.get(5).addItem(key1);
            }

            if (amountOfPuzzles >= 2) {
                Item key2 = new Item(2, "Small rusty key", "A small, rusty key.");
                allRooms.get(7).getSouthDoor().setPuzzle(key2);
                if (seed.getSeed().get(1) < 5) {
                    allRooms.get(4).addItem(key2);
                } else {
                    allRooms.get(3).addItem(key2);
                }
            }

            int treasureId = 100;
            int amountOfTreasure = 1;

            allRooms.get(7).addItem(new Item(treasureId, "Treasure", "Treasure for Demo."));
        }
    }

}
