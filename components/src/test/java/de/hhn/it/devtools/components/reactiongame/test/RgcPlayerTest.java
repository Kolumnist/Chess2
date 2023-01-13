package de.hhn.it.devtools.components.reactiongame.test;

import de.hhn.it.devtools.components.reactiongame.provider.RgcPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RgcPlayerTest {

  private RgcPlayer player1;
  private RgcPlayer player2;

  @BeforeEach
  public void setup() {
    player1 = new RgcPlayer("Tobi");
    player2 = new RgcPlayer("Max");
  }

  @Test
  public void testConstructorAndGetters() {
    assertEquals("Tobi", player1.getName());
    assertEquals(4, player2.getCurrentLife());
  }

  @Test
  public void testSetters() {
    player1.setName("Jonas");
    assertEquals("Jonas", player1.getName());

    player2.setCurrentLife(1);
    assertEquals(1, player2.getCurrentLife());
  }
}
