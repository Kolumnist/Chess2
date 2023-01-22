package de.hhn.it.devtools.components.connectfour.junit.testfacade.testcallback;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test callback: bad cases.")
public class TestCallbackBadCases {

  @Test
  @DisplayName("Test setCallback with null reference as listener")
  void testNullListener() {
    ConnectFourInterface game = new ConnectFour();
    assertThrows(IllegalArgumentException.class,
        () -> game.setCallback(null));
  }
}
