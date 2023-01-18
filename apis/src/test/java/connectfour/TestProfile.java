package connectfour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.connectfour.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.Profile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProfile {
  @Test
  @DisplayName("Create a Profile with an invalid name")
  public void testConstructorBadCases() {
    // null
    assertThrows(IllegalArgumentException.class,
        () -> new Profile(null));
    // empty string
    assertThrows(IllegalNameException.class,
        () -> new Profile(""));
    // blanks
    assertThrows(IllegalNameException.class,
        () -> new Profile("   "));
  }

  @Test
  @DisplayName("Create a Profile with a valid name")
  public void testConstructorGoodCases() throws IllegalNameException {
    // valid name: "user1"
    Profile u1 = new Profile("user1");
    assertEquals(u1.getName(), "user1");
    // valid name with blank: "User 2"
    Profile u2 = new Profile("User 2");
    assertEquals(u2.getName(), "User 2");
    // valid name with blanks to be stripped: "  Alice_03  " -> "Alice_03"
    Profile u3 = new Profile("  Alice_03  ");
    assertEquals(u3.getName(), "Alice_03");
  }

  @Test
  @DisplayName("Set the name of a profile to an invalid name")
  public void testSetNameBadCases() throws IllegalNameException {
    Profile u1 = new Profile("User 1");
    // null
    assertThrows(IllegalArgumentException.class,
        () -> u1.setName(null));
    Profile u2 = new Profile("User 2");
    // empty string
    assertThrows(IllegalNameException.class,
        () -> u2.setName(""));
    Profile u3 = new Profile("User 3");
    assertThrows(IllegalNameException.class,
        () -> u3.setName("   "));
  }

  @Test
  @DisplayName("Set the name of a profile to a valid name")
  public void testSetNameGoodCases() throws IllegalNameException {
    Profile u1 = new Profile("User 1");
    u1.setName("Bob");
    assertEquals(u1.getName(), "Bob");
  }
}
