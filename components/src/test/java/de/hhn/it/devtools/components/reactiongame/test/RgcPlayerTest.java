package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.components.reactiongame.provider.RgcPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the methods of RgcPlayer
 */
class RgcPlayerTest {

  private RgcPlayer player1;
  private RgcPlayer player2;

  /**
   * Sets up RgcPlayer objects to test with
   */
  @BeforeEach
  public void setup() {
    player1 = new RgcPlayer("Tobi");
    player2 = new RgcPlayer("Max");
  }

  /**
   * Tests constructor and getters
   */
  @Test
  public void testConstructorAndGetters() {
    assertEquals("Tobi", player1.getName());
    assertEquals(3, player2.getCurrentLife());
  }

  /**
   * Tests setters
   */
  @Test
  public void testSetters() {
    player1.setName("Jonas");
    assertEquals("Jonas", player1.getName());

    player2.setCurrentLife(1);
    assertEquals(1, player2.getCurrentLife());
  }
}
