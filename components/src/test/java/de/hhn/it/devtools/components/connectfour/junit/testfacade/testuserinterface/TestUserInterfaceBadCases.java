package de.hhn.it.devtools.components.connectfour.junit.testfacade.testuserinterface;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import java.util.NoSuchElementException;
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
  }

  @Test
  @DisplayName("Test bad creation with null reference as profile name")
  void createProfileWithNullReference() {
    assertThrows(IllegalArgumentException.class,
        () -> game.createProfile(null));
  }

  @Test
  @DisplayName("Test bad creation with empty string as profile name")
  void createProfileWithEmptyString() {
    assertThrows(IllegalArgumentException.class,
        () -> game.createProfile(""));
  }

  @Test
  @DisplayName("Test bad creation with blanks as profile name")
  void createProfileWithBlanks() {
    assertThrows(IllegalArgumentException.class,
        () -> game.createProfile("   "));
  }

  @Test
  @DisplayName("Test bad change of user name to null reference")
  void changeProfileNameToNull() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfileName(alice.getId(), null));
  }

  @Test
  @DisplayName("Test bad change of user name to empty string")
  void changeProfileNameToEmptyString() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfileName(alice.getId(), ""));
  }

  @Test
  @DisplayName("Test bad change of user name to blanks")
  void changeProfileNameToBlanks() {
    assertThrows(IllegalArgumentException.class,
        () -> game.setProfileName(alice.getId(), "   "));
  }

  @Test
  @DisplayName("Test bad change of user name with invalid ID")
  void changeProfileNameWithInvalidID() throws IllegalArgumentException {
    Profile ghost = new Profile("Ghost");
    assertThrows(NoSuchElementException.class,
        () -> game.setProfileName(ghost.getId(), "User_1"));
  }

  @Test
  @DisplayName("Test bad deletion of user with invalid ID")
  void deleteProfileWithInvalidID() throws IllegalArgumentException {
    Profile ghost = new Profile("Ghost");
    assertThrows(NoSuchElementException.class,
        () -> game.deleteProfile(ghost.getId()));
  }
}
