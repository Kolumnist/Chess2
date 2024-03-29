package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import java.util.ArrayList;


public class LayoutGenerator {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(LayoutGenerator.class);

    private ArrayList<Room> allRooms;
    private Map map;
    private Seed seed;
    private ArrayList<Layout> allLayouts;
    private Room startRoom;
    private boolean isPrepared;
    private int maxRoomCount = 13;

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
        layout.setStartRoom();
    }

    public int getMaxRoomCount() {
        return maxRoomCount;
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public Map getMap() {
        return map;
    }

    public Seed getSeed() {
        return seed;
    }

    public ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    public boolean isPrepared() { return isPrepared; }

    /**
     *
     */
    private void reset() {
        allRooms.clear();
        String exampleDescription = "A dark, cold room.";
        int j = 0;
        while (allRooms.size() < maxRoomCount) {
            Room newRoom = new Room(j, exampleDescription);
            this.allRooms.add(newRoom);
            j++;
        }
        this.startRoom = allRooms.get(0);
        this.allRooms.get(12).setExit();
        isPrepared = true;
    }



    public void generateLayout() throws RoomFailedException {
        if (!isPrepared) {
            reset();
        }



        //This part attaches rooms to other rooms, creating a layout based on the selected map.
        if (map.equals(Map.Grave_of_the_Mad_King)) {
            startRoom.setNextDoorRoom(allRooms.get(1), Direction.WEST);
            allRooms.get(1).setNextDoorRoom(allRooms.get(3), Direction.WEST);
            allRooms.get(1).setNextDoorRoom(allRooms.get(2), Direction.NORTH);
            startRoom.setNextDoorRoom(allRooms.get(4), Direction.NORTH);
            allRooms.get(4).setNextDoorRoom(allRooms.get(5), Direction.NORTH);
            allRooms.get(5).setNextDoorRoom(allRooms.get(6), Direction.NORTH);
            allRooms.get(6).setNextDoorRoom(allRooms.get(7), Direction.EAST);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8), Direction.SOUTH);
            startRoom.setNextDoorRoom(allRooms.get(9), Direction.EAST);
            allRooms.get(9).setNextDoorRoom(allRooms.get(10), Direction.EAST);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11), Direction.EAST);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12), Direction.NORTH);
            logger.info(map.toString() + "created.");

        } else if (map.equals(Map.Ancient_Dungeon)) {
            startRoom.setNextDoorRoom(allRooms.get(1), Direction.WEST);
            allRooms.get(1).setNextDoorRoom(allRooms.get(4), Direction.WEST);
            allRooms.get(4).setNextDoorRoom(allRooms.get(5), Direction.NORTH);
            allRooms.get(5).setNextDoorRoom(allRooms.get(7), Direction.NORTH);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8), Direction.EAST);
            allRooms.get(8).setNextDoorRoom(allRooms.get(6), Direction.EAST);
            startRoom.setNextDoorRoom(allRooms.get(2), Direction.NORTH);
            allRooms.get(2).setNextDoorRoom(allRooms.get(6), Direction.NORTH);
            allRooms.get(6).setNextDoorRoom(allRooms.get(9), Direction.EAST);
            startRoom.setNextDoorRoom(allRooms.get(3), Direction.EAST);
            allRooms.get(3).setNextDoorRoom(allRooms.get(10), Direction.EAST);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11), Direction.NORTH);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12), Direction.EAST);
            logger.info(map.toString() + "created.");

        } else if (map.equals(Map.Unknown_Sewers)) {
            startRoom.setNextDoorRoom(allRooms.get(1), Direction.NORTH);
            allRooms.get(1).setNextDoorRoom(allRooms.get(2), Direction.NORTH);
            allRooms.get(2).setNextDoorRoom(allRooms.get(3), Direction.EAST);
            allRooms.get(2).setNextDoorRoom(allRooms.get(4), Direction.WEST);
            allRooms.get(2).setNextDoorRoom(allRooms.get(5), Direction.NORTH);
            allRooms.get(5).setNextDoorRoom(allRooms.get(6), Direction.NORTH);
            allRooms.get(6).setNextDoorRoom(allRooms.get(7), Direction.NORTH);
            allRooms.get(7).setNextDoorRoom(allRooms.get(8), Direction.WEST);
            allRooms.get(7).setNextDoorRoom(allRooms.get(9), Direction.EAST);
            allRooms.get(7).setNextDoorRoom(allRooms.get(10), Direction.NORTH);
            allRooms.get(10).setNextDoorRoom(allRooms.get(11), Direction.NORTH);
            allRooms.get(11).setNextDoorRoom(allRooms.get(12), Direction.NORTH);
            logger.info(map.toString() + "created.");
        }



        //This sets all the doors.
        for (Room room : allRooms) {
            room.setDoors();
        }


        //This part uses the seed to create puzzles and treasure.
        if (map.equals(Map.Grave_of_the_Mad_King)) {
            int amountOfPuzzles = 1;
            if (seed.getSeed().get(0) < 5) {
                amountOfPuzzles = 2;
            }

            Item key1 = new Item(1, "ExitKey", "A big, old metal key.");
            allRooms.get(7).getDoor(Direction.SOUTH).setPuzzle(key1);
            if (seed.getSeed().get(1) < 5) {
                allRooms.get(3).addItem(key1);
            } else {
                allRooms.get(2).addItem(key1);
            }

            if (amountOfPuzzles == 2) {
                Item key2 = new Item(2, "Small rusty key", "A small, rusty key.");
                allRooms.get(4).getDoor(Direction.SOUTH).setPuzzle(key2);
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

            allRooms.get(7).addItem(new Treasure(treasureId, "Treasure", "Treasure for Demo."));
            logger.info(map.toString() + "finished.");

        } else if (map.equals(Map.Ancient_Dungeon)) {
            int amountOfPuzzles = 1;
            if (seed.getSeed().get(0) > 4) {
                amountOfPuzzles = 2;
            }
            if (seed.getSeed().get(0) > 8) {
                amountOfPuzzles = 3;
            }

            Item key1 = new Item(1, "ExitKey", "A metal key. It fits well into your hand.");
            allRooms.get(11).getDoor(Direction.SOUTH).setPuzzle(key1);
            if (seed.getSeed().get(1) < 5) {
                allRooms.get(4).addItem(key1);
            } else {
                allRooms.get(9).addItem(key1);
            }

            if (amountOfPuzzles == 2) {
                Item key2 = new Item(2, "Small blue key", "Though clearly metal, this key has a blue tint");
                allRooms.get(1).getDoor(Direction.WEST).setPuzzle(key2);
                if (seed.getSeed().get(1) == 0) {
                    allRooms.get(0).addItem(key2);
                } else if (seed.getSeed().get(1) < 5) {
                    allRooms.get(10).addItem(key2);
                } else {
                    allRooms.get(9).addItem(key2);
                }
            }

            if (amountOfPuzzles == 3) {
                Item key3 = new Item(3, "Metal key",
                        "On closer inspection, this key has a 10 itched into its metal.");
                allRooms.get(10).getDoor(Direction.EAST).setPuzzle(key3);
                if (seed.getSeed().get(1) < 5) {
                    allRooms.get(7).addItem(key3);
                } else {
                    allRooms.get(9).addItem(key3);
                }
            }

            int treasureId = 100;
            int amountOfTreasure = 1;
            allRooms.get(7).addItem(new Treasure(treasureId, "Treasure", "Treasure for Demo."));
            logger.info(map.toString() + "finished.");

        } else if (map.equals(Map.Unknown_Sewers)) {
            int amountOfPuzzles = 1;
            if (seed.getSeed().get(0) < 5) {
                amountOfPuzzles = 2;
            }

            Item key1 = new Item(1, "ExitKey", "A big, old metal key. It feels wet.");
            allRooms.get(7).getDoor(Direction.NORTH).setPuzzle(key1);
            if (seed.getSeed().get(1) < 5) {
                allRooms.get(3).addItem(key1);
            } else {
                allRooms.get(5).addItem(key1);
            }

            if (amountOfPuzzles == 2) {
                Item key2 = new Item(2, "Small rusty key", "A small, rusty key.");
                allRooms.get(6).getDoor(Direction.NORTH).setPuzzle(key2);
                if (seed.getSeed().get(1) < 5) {
                    allRooms.get(4).addItem(key2);
                } else {
                    allRooms.get(3).addItem(key2);
                }
            }

            int treasureId = 100;
            int amountOfTreasure = 1;
            allRooms.get(7).addItem(new Item(treasureId, "Treasure", "Treasure for Demo."));
            logger.info(map.toString() + "finished.");
        }
    }
}
