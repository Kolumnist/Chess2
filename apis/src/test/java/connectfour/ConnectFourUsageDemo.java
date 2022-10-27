package connectfour;

import de.hhn.it.devtools.apis.connectfour.ConnectFour;
import de.hhn.it.devtools.apis.connectfour.Difficulty;
import de.hhn.it.devtools.apis.connectfour.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.Mode;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.connectfour.ProfileNotFoundException;
import de.hhn.it.devtools.apis.connectfour.ProfileNotSelectedException;
import java.util.List;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo will be accessible in the components-module.
 */


public class ConnectFourUsageDemo {


  public static void main(String[] args) {
    ConnectFour connectFour = null;

    //create three profiles
    Profile user1 = connectFour.createProfile("User1");
    Profile user2 = connectFour.createProfile("User2");
    Profile user3 = connectFour.createProfile("User3");

    //browse profiles
    List<Profile> profiles = connectFour.getProfiles();
    Profile profile0 = profiles.get(0);
    Profile profile1 = profiles.get(1);
    Profile profile2 = profiles.get(2);

    //change name of User1
    try {
      connectFour.setProfileName(profile0.getId(), "Player1");
    } catch (ProfileNotFoundException | IllegalNameException e) {
      e.printStackTrace();
    }

    //delete User2
    try {
      connectFour.deleteProfile(profile1.getId());
    } catch (ProfileNotFoundException e) {
      e.printStackTrace();
    }

    //set mode to singleplayer
    connectFour.setMode(Mode.SINGLEPLAYER);

    //set difficulty
    connectFour.setDifficulty(Difficulty.HARD);

    //choose profile for singleplayer mode
    connectFour.chooseProfileA(profile0);

    //start game
    try {
      connectFour.startGame();
    } catch (ProfileNotSelectedException e) {
      e.printStackTrace();
    }

    //profile0: place disc at column 3
    try {
      connectFour.placeDiscAt(3);
    } catch (IllegalOperationException e) {
      e.printStackTrace();
    }

    //eng game, go back to main menu
    connectFour.quitGame();

    connectFour.setMode(Mode.LOCAL_PVP);
    connectFour.chooseProfileA(profile2);
    connectFour.chooseProfileB(profile0);

    //start game
    try {
      connectFour.startGame();
    } catch (ProfileNotSelectedException e) {
      e.printStackTrace();
    }

    //profile2: place disc at column 1
    try {
      connectFour.placeDiscAt(1);
    } catch (IllegalOperationException e) {
      e.printStackTrace();
    }

    //profile0: place disc at column 2
    try {
      connectFour.placeDiscAt(2);
    } catch (IllegalOperationException e) {
      e.printStackTrace();
    }

    //eng game, go back to main menu
    connectFour.quitGame();
  }
}

