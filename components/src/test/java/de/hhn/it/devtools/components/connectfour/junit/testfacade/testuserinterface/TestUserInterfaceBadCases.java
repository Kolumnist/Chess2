package de.hhn.it.devtools.components.connectfour.junit.testfacade.testuserinterface;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.components.connectfour.junit.helper.DummyCallback;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test user interface: bad cases.")
public class TestUserInterfaceBadCases {

  ConnectFourInterface game;
  Profile alice;

  @BeforeEach
  void setup() throws IllegalArgumentException {
    game = new ConnectFour();
    alice = game.createProfile("Alice");
    alice = game.createProfile("Bob");
  }

  @Test
  @DisplayName("Test bad creation with null reference as profile name.")
  void createProfileWithNullReference() {
    assertThrows(IllegalArgumentException.class,
        () -> game.createProfile(null));
  }

  @Test
  @DisplayName("Test bad creation with empty string as profile name.")
  void createProfileWithEmptyString() {
    assertThrows(IllegalArgumentException.class,
        () -> game.createProfile(""));
  }

  @Test
  @DisplayName("Test bad creation with blanks as profile name.")
  void createProfileWithBlanks() {
    assertThrows(IllegalArgumentException.class,
        () -> game.createProfile("   "));
  }

  @Test
  @DisplayName("Test bad change of user name to null reference.")
  void changeProfileNameToNull() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfileName(alice.getId(), null));
  }

  @Test
  @DisplayName("Test bad change of user name to empty string.")
  void changeProfileNameToEmptyString() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfileName(alice.getId(), ""));
  }

  @Test
  @DisplayName("Test bad change of user name to blanks.")
  void changeProfileNameToBlanks() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfileName(alice.getId(), "   "));
  }

  @Test
  @DisplayName("Test bad change of user name with invalid ID.")
  void changeProfileNameWithInvalidID() {
    Profile ghost = new Profile("Ghost");
    assertThrows(NoSuchElementException.class,
        () -> game.setProfileName(ghost.getId(), "User_1"));
  }

  @Test
  @DisplayName("Test bad deletion of user with invalid ID.")
  void deleteProfileWithInvalidID() {
    Profile ghost = new Profile("Ghost");
    assertThrows(NoSuchElementException.class,
        () -> game.deleteProfile(ghost.getId()));
  }

  @Test
  @DisplayName("Test get user profile with invalid ID.")
  void getProfileWithInvalidID() {
    Profile ghost = new Profile("Ghost");
    assertThrows(NoSuchElementException.class,
        () -> game.getProfile(ghost.getId()));
  }

  @Test
  @DisplayName("Test set user profiles with null reference.")
  void setProfilesToNull() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfiles(null));
  }

  @Test
  @DisplayName("Play singleplayer game with null reference as player.")
  void playSingleplayerGameWithNullPlayer() {
    assertThrows(IllegalArgumentException.class,
        () -> game.playSingleplayerGame(null, true));
  }

  @Test
  @DisplayName("Play multiplayer game with null references as players")
  void playMultiplayerGameWithNullPlayers() {
    assertThrows(IllegalArgumentException.class,
        () -> game.playMultiplayerGame(null, null, true));
  }

  @Test
  @DisplayName("Place disc in column with invalid index.")
  void placeDiscInColumnWithInvalidIndex() {
    game.playSingleplayerGame(alice, true);
    assertThrows(IllegalArgumentException.class,
        () -> game.placeDiscInColumn(9));
  }

  @Test
  @DisplayName("Place disc in full column.")
  void placeDiscInFullColumn() {
    game.playSingleplayerGame(alice, true);
    game.setCallback(new DummyCallback());
    game.start();
    try {
      for (int i = 0; i < 6; i++) {
        game.placeDiscInColumn(2);
      }
    } catch (IllegalOperationException e) {
      // Column should spill over yet.
      Assertions.fail();
    }
    // Disc cannot be placed in full column.
    assertThrows(IllegalOperationException.class,
        () -> game.placeDiscInColumn(2));
  }
}
