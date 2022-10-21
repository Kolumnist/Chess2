package connectfour;

import de.hhn.it.devtools.apis.connectfour.ConnectFour;
import de.hhn.it.devtools.apis.connectfour.Difficulty;
import de.hhn.it.devtools.apis.connectfour.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.Mode;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerServiceUsageDemo;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo will be accessible in the components-module.
 */


public class ConnectFourUsageDemo {

  public static void main(String[] args)
      throws IllegalParameterException, InterruptedException, IllegalOperationException {
    ConnectFour connectFour = null;

    //create three profiles
    Profile user1 = connectFour.createProfile("User1");
    Profile user2 = connectFour.createProfile("User2");
    Profile user3 = connectFour.createProfile("User3");

    //browse profiles
    List<Profile> profiles = connectFour.getProfiles();
    Profile profile0 = profiles.get(0);
    Profile profile1 = profiles.get(1);

    //change name of User1
    connectFour.setProfileName(profile0.getId(), "Player1");

    //delete User2
    connectFour.deleteProfile(profile1.getId());

    //set mode to singleplayer
    connectFour.setMode(Mode.SINGLEPLAYER);

    //set difficulty
    connectFour.setDifficulty(Difficulty.HARD);

    //start game
    connectFour.startGame();

    //place disc at row 3
    connectFour.placeDiscAt(3);

    //open help dialogue
    connectFour.help();

    //eng game
    connectFour.quitGame();
  }
}
