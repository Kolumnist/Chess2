package de.hhn.it.devtools.components.connectfour.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test user interface with good cases.")
public class TestUserInterfaceGoodCases {

  ConnectFour connectFour;

  @BeforeEach
  void setup() {
    connectFour = new ConnectFour();
  }

  @Test
  @DisplayName("adding one new profile with name results in one entry.")
  public void addNewProfile() throws IllegalArgumentException {
    int profileCount = connectFour.getProfiles().size();
    connectFour.createProfile("Max");
    List<Profile> profiles = connectFour.getProfiles().values().stream().toList();
    assertEquals(profileCount + 1, profiles.size(),
        "There should be exactly one additional profile in the list.");
  }

  @Test
  @DisplayName("delete one profile and check the result.")
  public void removeOneProfile()
      throws IllegalArgumentException, NoSuchElementException {
    int profileCount = connectFour.getProfiles().size();
    connectFour.createProfile("Alice");
    Profile bob = connectFour.createProfile("Bob");
    connectFour.createProfile("Carl");
    connectFour.deleteProfile(bob.getId());
    List<Profile> profiles = connectFour.getProfiles().values().stream().toList();
    assertEquals(profileCount + 2, profiles.size());
  }
}
