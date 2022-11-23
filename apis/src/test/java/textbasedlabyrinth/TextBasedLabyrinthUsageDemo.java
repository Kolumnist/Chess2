package textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.InvalidSeedException;
import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;

import java.util.ArrayList;

/**
 * Usage Demo for TextBasedLabyrinth.
 */
public class TextBasedLabyrinthUsageDemo {

  public static void main(String[] args) throws RoomFailedException {


    //A seed is created.
    ArrayList<Integer> list = new ArrayList<>();
    list.add(5);
    list.add(1);
    Seed seed = null;
    try {
      seed = new Seed(list);
    } catch (InvalidSeedException e) {
      throw new RuntimeException(e);
    }
    // Game creates a Layout according selected map
    GameService gameService = new Game(Map.Ancient_Dungeon, seed);
    // we can check now where we can go
    gameService.inspect(Direction.WEST);
    // West seems fine, so lets go this way
    gameService.moveWest();
    // Lets check the surroundings
    gameService.inspect(Direction.SOUTH);
    gameService.inspect(Direction.NORTH);
    gameService.inspect(Direction.WEST);
    // We can go either West or North it seems
    gameService.moveWest();
    gameService.inspect(Direction.WEST);
    gameService.inspect(Direction.SOUTH);
    gameService.inspect(Direction.NORTH);
    // seems to be an dead-end, lets go back
    gameService.moveEast();
    gameService.moveNorth();
    gameService.inspect(Direction.WEST);
    gameService.inspect(Direction.EAST);
    gameService.inspect(Direction.NORTH);
    // an dead-end again!! lets search the room before we go
    gameService.searchRoom();
    gameService.moveSouth();
    gameService.moveEast();
    // lets go to the exit now

    gameService.moveEast();
    gameService.moveEast();
    gameService.moveEast();
    gameService.moveNorth();





  }
}
