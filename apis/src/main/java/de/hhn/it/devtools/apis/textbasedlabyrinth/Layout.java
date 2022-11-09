package de.hhn.it.devtools.apis.textbasedlabyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
  public Seed seed;

  /**
   * Constructor of layout class.
   *
   * @param player player of the game
   */
  @SuppressWarnings("checkstyle:WhitespaceAround")
  public Layout(Player player, Seed seed) {

    int roomCount = 1;
    String exampleDescription = "A dark, cold room.";
    this.player = player;

    // create a List for all the Rooms generated
    this.allRooms = new ArrayList<>();

    // create as many rooms as maxRoomCount allows
    int j = 0;
    while (allRooms.size() <= maxRoomCount) {
      Room newRoom = new Room(j, exampleDescription);
      this.allRooms.add(newRoom);
      j++;
    }

    // take the first room generated as the starting room
    this.startRoom = allRooms.get(0);
    // place player inside
    player.setCurrentRoomOfPlayer(startRoom);

    // West
    startRoom.setNextDoorRoom(allRooms.get(roomCount), false, true, false);
    roomCount++;
    if (seed.getFloorLength().get(0) > 1) {
      allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
                false, seed.floorDir.get(0), seed.floorDir.get(1));
      roomCount++;
      if (seed.getFloorLength().get(0) > 2) {
        allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
                seed.floorDir.get(1), seed.floorDir.get(2), false);
        roomCount++;
      }
    }
    // North
    startRoom.setNextDoorRoom(allRooms.get(roomCount), false, false, true);
    roomCount++;
    if (seed.getFloorLength().get(1) > 1) {
      allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
              seed.floorDir.get(0), false, seed.floorDir.get(1));
      roomCount++;
      if (seed.getFloorLength().get(1) > 2) {
        allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
                        seed.floorDir.get(0), false, seed.floorDir.get(1));
        roomCount++;
      }
    }
    // East
    startRoom.setNextDoorRoom(allRooms.get(roomCount), true, false, false);
    roomCount++;
    if (seed.getFloorLength().get(2) > 1) {
      allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
           seed.floorDir.get(0), false, seed.floorDir.get(1));
      roomCount++;
      if (seed.getFloorLength().get(2) > 2) {
        allRooms.get(roomCount - 1).setNextDoorRoom(allRooms.get(roomCount),
                        seed.floorDir.get(1), seed.floorDir.get(2), false);
        roomCount++;

      }
    }

    for (Room room : allRooms) {
      room.setDoors();
    }

    //Now we create the puzzles.
    int numberOfPuzzles = 1;
    int a = maxRoomCount / 5;
    if (a > 1) {
      numberOfPuzzles = a;
    }

    a = 0;
    int b = 5;
    String standardKeyDescription = "A metal key. Looks old.";

    while (a < numberOfPuzzles) {
      Random random = new Random();
      Item key = new Item(a, "Key " + a, standardKeyDescription);

      //We need this to make sure that the key is generated in a room before the puzzle.
      int puzzleInteger = random.nextInt(0, b);
      int keyInteger = 0;
      if (puzzleInteger == 0) {
        keyInteger = 0;
      } else {
        keyInteger = random.nextInt(0, puzzleInteger - 1);
      }

      Room puzzleRoom = allRooms.get(puzzleInteger);
      int c = puzzleRoom.getNumberOfDoors();
      int d = random.nextInt(1, c);
      if (d == 1) {
        puzzleRoom.getSouthDoor().setPuzzle(key);
      }
      if (d == 2) {
        puzzleRoom.getEastDoor().setPuzzle(key);
      }
      if (d == 3) {
        puzzleRoom.getWestDoor().setPuzzle(key);
      }
      if (d == 4) {
        puzzleRoom.getNorthDoor().setPuzzle(key);
      }

      allRooms.get(keyInteger).addItem(key);

      a++;
      b = b + 5;
    }
  }

  /**
   * Assigns a room on a random direction to a room depending on what is not already assigned.
   *
   * @param room Room that gets a new Room assigned.
   * @param roomToBeAssigned room to be assigned
   */
  public void assignRandomNextDoorRoom(Room room, Room roomToBeAssigned) {

    ArrayList<Boolean> randomBoolean = new ArrayList<>();
    randomBoolean.add(true);
    randomBoolean.add(false);
    randomBoolean.add(false);
    Collections.shuffle(randomBoolean);

    if (room.isNorthAssigned.equals(true)) {
      room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), randomBoolean.get(1), false);
    } else if (room.isEastAssigned.equals(true)) {
      room.setNextDoorRoom(roomToBeAssigned, false, randomBoolean.get(0), randomBoolean.get(1));
    } else if (room.isSouthAssigned.equals(true)) {
      room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), randomBoolean.get(1),
              randomBoolean.get(2));
    } else {
      room.setNextDoorRoom(roomToBeAssigned, randomBoolean.get(0), false, randomBoolean.get(1));
    }
  }
}
