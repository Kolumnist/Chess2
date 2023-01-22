package de.hhn.it.devtools.components.connectfour.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test user interface: good cases.")
public class TestUserInterfaceGoodCases {

  ConnectFour connectFour;

  @BeforeEach
  void setup() {
    connectFour = new ConnectFour();
  }

  @Test
  @DisplayName("Add one new profile with name results in one entry.")
  public void testAddNewProfile() {
    int profileCount = connectFour.getProfiles().size();
    connectFour.createProfile("Max");
    List<Profile> profiles = connectFour.getProfiles().values().stream().toList();
    assertEquals(profileCount + 1, profiles.size(),
        "There should be exactly one additional profile in the list.");
  }

  @Test
  @DisplayName("Delete one profile and check the result.")
  public void testRemoveOneProfile() {
    int profileCount = connectFour.getProfiles().size();
    connectFour.createProfile("Alice");
    Profile bob = connectFour.createProfile("Bob");
    connectFour.createProfile("Carl");
    connectFour.deleteProfile(bob.getId());
    List<Profile> profiles = connectFour.getProfiles().values().stream().toList();
    assertEquals(profileCount + 2, profiles.size());
  }

  @Test
  @DisplayName("Change a profile name.")
  public void testChangeProfileName() {
    Profile alice = connectFour.createProfile("Alice");
    connectFour.setProfileName(alice.getId(), "Alicia");
    Assertions.assertEquals(connectFour.getProfile(alice.getId()).getName(), "Alicia");
  }
}
