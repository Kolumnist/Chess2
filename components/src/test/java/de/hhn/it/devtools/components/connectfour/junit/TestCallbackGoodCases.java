package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.components.connectfour.junit.DummyCallback;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test callback: good cases.")
public class TestCallbackGoodCases {

  @Test
  @DisplayName("Test setCallback with valid listener")
  void testNullListener() {
    ConnectFourInterface game = new ConnectFour();
    DummyCallback dummyCallback = new DummyCallback();
    try {
      game.setCallback(dummyCallback);
      // Success.
    } catch (IllegalArgumentException e) {
      // Fail.
      Assertions.fail();
    }
  }
}
