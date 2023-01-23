package connectfour;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This usage demo is not runnable because in this module there is no possibility to access the
 * implementation. The runnable demo will be accessible in the components-module.
 */
public class ConnectFourUsageDemo {

  public static void main(String[] args) {
    ConnectFourInterface connectFour = null;

    // Create three profiles.
    try {
      Profile user1 = connectFour.createProfile("User1");
      Profile user2 = connectFour.createProfile("User2");
      Profile user3 = connectFour.createProfile("User3");
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }

    // Browse profiles.
    List<Profile> profiles = connectFour.getProfiles().values().stream().toList();
    Profile profile0 = profiles.get(0);
    Profile profile1 = profiles.get(1);
    Profile profile2 = profiles.get(2);

    // Change name of User1.
    try {
      connectFour.setProfileName(profile0.getId(), "Player1");
    } catch (IllegalArgumentException | NoSuchElementException e) {
      e.printStackTrace();
    }

    // Delete User2.
    try {
      connectFour.deleteProfile(profile1.getId());
    } catch (NoSuchElementException e) {
      e.printStackTrace();
    }

    // Play singleplayer mode.
    connectFour.playSingleplayerGame(profile0, true);

    // profile0: place disc at column 3.
    try {
      connectFour.placeDiscInColumn(3);
    } catch (IllegalOperationException e) {
      e.printStackTrace();
    }

    connectFour.playMultiplayerGame(profile2, profile0, true);

    // profile2: place disc at column 1.
    try {
      connectFour.placeDiscInColumn(1);
    } catch (IllegalOperationException e) {
      e.printStackTrace();
    }

    // profile0: place disc at column 2.
    try {
      connectFour.placeDiscInColumn(2);
    } catch (IllegalOperationException e) {
      e.printStackTrace();
    }
  }
}
