package de.hhn.it.devtools.components.connectfour.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hhn.it.devtools.apis.connectfour.exceptions.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.apis.connectfour.exceptions.ProfileNotFoundException;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import java.util.List;
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
  public void addNewProfile() throws IllegalNameException, IllegalArgumentException {
    int profileCount = connectFour.getProfiles().size();
    connectFour.createProfile("Max");
    List<Profile> profiles = connectFour.getProfiles();
    assertEquals(profileCount + 1, profiles.size(),
        "There should be exactly one additional profile in the list.");
  }

  @Test
  @DisplayName("delete one profile and check the result.")
  public void removeOneProfile()
      throws ProfileNotFoundException, IllegalArgumentException, IllegalNameException {
    int profileCount = connectFour.getProfiles().size();
    connectFour.createProfile("Alice");
    Profile bob = connectFour.createProfile("Bob");
    connectFour.createProfile("Carl");
    connectFour.deleteProfile(bob.getId());
    List<Profile> profiles = connectFour.getProfiles();
    assertEquals(profileCount + 2, profiles.size());
  }
}
