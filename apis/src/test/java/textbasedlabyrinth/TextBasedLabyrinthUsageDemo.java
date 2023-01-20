package textbasedlabyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import java.util.ArrayList;
import java.util.List;

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
    OutputListener notifier = null;
    // Game creates a Layout according selected map
    GameService gameService = null;
    try {
      gameService.setCurrentLayout(Map.Grave_of_the_Mad_King, seed);
    } catch (InvalidSeedException e) {
      e.printStackTrace();
    }
    // we can check now where we can go
    gameService.inspect(Direction.WEST);
    // West seems fine, so lets go this way
    gameService.move(Direction.WEST);
    // Lets check the surroundings
    gameService.inspect(Direction.SOUTH);
    gameService.inspect(Direction.NORTH);
    gameService.inspect(Direction.WEST);
    // We can go either West or North it seems
    gameService.move(Direction.WEST);
    gameService.inspect(Direction.WEST);
    gameService.inspect(Direction.SOUTH);
    gameService.inspect(Direction.NORTH);
    gameService.searchRoom();

    List<Item> items = gameService.searchRoom();

    int i = 1;
    for(Item item : items){
      System.out.println(i + ". :" + item.getName() + " " + item.getItemId());
      i++;
    }

    try {
      gameService.pickUpItem(1);
    } catch (NoSuchItemFoundException e) {
      throw new RuntimeException(e);
    }

    // seems to be an dead-end, lets go back
    gameService.move(Direction.EAST);
    gameService.move(Direction.NORTH);
    gameService.inspect(Direction.WEST);
    gameService.inspect(Direction.EAST);
    gameService.inspect(Direction.NORTH);
    // an dead-end again!! lets search the room before we go
    gameService.searchRoom();
    gameService.move(Direction.SOUTH);
    gameService.move(Direction.EAST);
    // lets go to the exit now
    gameService.move(Direction.NORTH);
    gameService.move(Direction.NORTH);
    gameService.move(Direction.NORTH);
    gameService.move(Direction.EAST);
    //there should be a locked door now to stop us from moving south...
    gameService.inspect(Direction.SOUTH);
    gameService.interaction(Direction.SOUTH, items.get(0));
    gameService.move(Direction.SOUTH);

  }
}