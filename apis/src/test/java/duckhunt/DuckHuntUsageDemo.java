package duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntMenuListener;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntMenuService;
import de.hhn.it.devtools.apis.duckhunt.DuckHuntService;
import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import de.hhn.it.devtools.apis.duckhunt.GameState;
import de.hhn.it.devtools.apis.duckhunt.GunButton;
import de.hhn.it.devtools.apis.duckhunt.IllegalButtonId;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 *
 */
public class DuckHuntUsageDemo {
  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    DuckHuntMenuService duckHuntMenuService = null;
    DuckHuntService duckHuntService = null;
    GameSettingsDescriptor gameSettingsDescriptor = null;
    DuckHuntMenuListener listener = null;
    DuckHuntListener duckHuntListener = null;

    try {
      // change game settings with gameSettingsDescriptor
      duckHuntMenuService.changeGameSettings(gameSettingsDescriptor);

      // is called when mouse button is clicked in the menu
      duckHuntMenuService.onButtonClicked(1);

      // adding and removing Callback in menu
      duckHuntMenuService.addCallback(listener);
      duckHuntMenuService.removeCallback(listener);

      // is called when there was an update to the menu
      listener.newState(new GameSettingsDescriptor());


      // is called when mouse button is clicked in the game
      duckHuntService.onClicked(1, 1, GunButton.LEFT); // shoot
      duckHuntService.onClicked(1, 1, GunButton.MIDDLE); // no function implemented yet
      duckHuntService.onClicked(1, 1, GunButton.RIGHT); // reload

      // adding and removing Callback in service
      duckHuntService.addCallback(duckHuntListener);
      duckHuntService.removeCallback(duckHuntListener);

      // is called when there is an update of the game (every game tick)
      duckHuntListener.newState(new GameState(100, 3, 5));

    } catch (IllegalButtonId e) {
      throw new RuntimeException(e);
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }

  }
}
