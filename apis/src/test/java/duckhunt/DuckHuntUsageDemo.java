package duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckData;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntService;
import de.hhn.it.devtools.apis.duckhunt.DucksInfo;
import de.hhn.it.devtools.apis.duckhunt.GameInfo;
import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckIdException;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo is accessible in the components module.
 */
public class DuckHuntUsageDemo {
  /**
   * This usage demo is not runnable because in this module there is no possibility to access the
   * implementation. The runnable demo is accessible in the components module.
   *
   * @param args no args / cant run
   */
  public static void main(String[] args) {
    DuckHuntService duckHuntService = null;
    GameSettingsDescriptor gameSettingsDescriptor = new GameSettingsDescriptor();
    DuckHuntListener duckHuntListener = null;
    DuckData[] duckDatas = null;

    try {
      // adding Callback in service
      duckHuntService.addCallback(duckHuntListener);

      // changing game settings
      duckHuntService.changeGameSettings(gameSettingsDescriptor);

      // starting game
      duckHuntService.startGame();

      // is called when there is an update of the game (every game tick)
      duckHuntListener.newState(new GameInfo(100, 3, 5));
      duckHuntListener.newDuckPosition(new DucksInfo(duckDatas));
      // is called when a duck was hit
      duckHuntListener.duckHit(1);

      // is called when shoot mouse button is clicked in the game
      duckHuntService.shoot(1, 1);
      // is called when an obstacle is hit
      duckHuntService.shootObstacle();

      // pause and continue game
      duckHuntService.pauseGame();
      duckHuntService.continueGame();

      // is called when reload mouse button is clicked in the game
      duckHuntService.reload();

      // stopping game
      duckHuntService.stopGame();

      // adding and removing Callback in service
      duckHuntService.removeCallback(duckHuntListener);

    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    } catch (IllegalGameInfoException e) {
      throw new RuntimeException(e);
    } catch (IllegalDuckIdException e) {
      throw new RuntimeException(e);
    } catch (IllegalDuckPositionException e) {
      throw new RuntimeException(e);
    }

  }
}
