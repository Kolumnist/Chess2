package text_based_labyrinth;

import de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions.RoomFailedException;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Game;
import de.hhn.it.devtools.apis.textbasedlabyrinth.GameService;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Seed;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Direction;


/**
 * Usage Demo for TextBasedLabyrinth.
 */
public class TextBasedLabyrinthUsageDemo {

  public static void main(String[] args) throws RoomFailedException {

    Seed seedOne = new Seed(1);
    Seed seedTwo = new Seed(2);
    Seed seedTree = new Seed(3);
    GameService gameService = new Game(seedOne);
    // Game creates a Layout according to the input from seedOne with a little algorithm
    // Now there should be on the west side a room that leads nowhere else(Dead-End)
    gameService.moveWest();
    // Now we should only be able to move back towards the east,
    // but lets try the other directions regardless
    // to see if there is anything wrong
    gameService.moveSouth();
    gameService.moveWest();
    gameService.moveNorth();
    gameService.moveEast();
    // Now we should be back at the beginning
    // Inspect the room maybe you can find something and then go up
    gameService.inspect(Direction.NORTH);
    gameService.moveNorth();
    // now we should be able to go right according to the algorithm,
    // but lets test everything else as well
    gameService.moveNorth();
    gameService.moveWest();
    gameService.moveEast();
    // now this should be a dead end as well, lets check
    gameService.moveNorth();
    gameService.moveEast();
    gameService.moveSouth();
    // lets go back to the start
    gameService.moveWest();
    gameService.moveSouth();
    // now the East side should have 3 Rooms and an exit at the end
    gameService.moveEast();
    // we should only be able to move to East here again but lets try our other options
    gameService.moveNorth();
    gameService.moveSouth();
    gameService.moveEast();
    // Now we can only continue to South from here, but we try other directions for tests
    gameService.moveNorth();
    gameService.moveEast();
    gameService.moveSouth();
    // Inspect the room for items. you find one interact with it.
    // now there should be an Exit on the south!
    gameService.inspect(Direction.SOUTH);
    gameService.moveSouth();

  }
}
