package de.hhn.it.devtools.components.connectfour.junit;

import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourInterface;
import de.hhn.it.devtools.apis.connectfour.interfaces.ConnectFourListenerInterface;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test callback.")
public class TestCallbackGoodCases {

  @Test
  @DisplayName("Test setCallback with valid listener")
  void testNullListener() {
    ConnectFourInterface game = new ConnectFour();
    try {
      game.setCallback(new ConnectFourListenerInterface() {
        @Override
        public void updateDescription(String description) {

        }

        @Override
        public void updateTile(int column, int row, String color) {

        }

        @Override
        public void lock() {

        }

        @Override
        public void unlock() {

        }

        @Override
        public void save() {

        }
      });
    } catch (IllegalArgumentException e) {
      Assertions.fail();
    }
  }
}
